package com.alfuvedan.hrmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import com.alfuvedan.hrmanager.data.*;
import com.alfuvedan.hrmanager.session.SessionInfo;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
//    public static final String REGISTER_ACTIVITY = "com.alfuvedan.hrmanager.RegisterUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);

        LoginInfoSaver.getLoginInfoFromFile(this);
        SessionInfo.loadSessionData(this);

        if(SessionInfo.isLoggedIn())
            this.goToEmployeeTableActivity();
    }

    public void userLogin(View view) {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (email.isEmpty()) {
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { //if user provides wrong type of email address ask again
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        LoginInfo info = new LoginInfo(AccessTiers.LAST_TIER, email,password);

        if (!LoginInfoSaver.loginVerify(info)) {
            Toast.makeText(this,"Incorrect email or password",Toast.LENGTH_SHORT).show();
            return;
        }

        SessionInfo.login(this, new LoginInfo(LoginInfoSaver.getAccessTier(info), info.getEmail(), info.getPassword()));
        this.goToEmployeeTableActivity();
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

    private void goToEmployeeTableActivity() {
        Intent intent = new Intent(this, EmployeeTableActivity.class);
        startActivity(intent);

        this.finish();
    }
}