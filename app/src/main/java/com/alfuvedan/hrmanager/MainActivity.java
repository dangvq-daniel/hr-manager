package com.alfuvedan.hrmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import com.alfuvedan.hrmanager.data.*;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
//    public static final String REGISTER_ACTIVITY = "com.alfuvedan.hrmanager.RegisterUserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        LoginInfoSaver.loginAdd(new LoginInfo(AccessTiers.TIER_1, "timhortons@icecap.com","timbits"));
        LoginInfoSaver.loginAdd(new LoginInfo(AccessTiers.TIER_2, "burgerking@bestburger.com","justjkitsucks"));

        Employees.getEmployeesFromFile(this);

        for(Employee employee : Employees.getAllEmployees()) {
            System.out.println(employee);
        }
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

        if (!LoginInfoSaver.loginVerify(new LoginInfo(AccessTiers.LAST_TIER, email,password))) {
            Toast.makeText(this,"Incorrect email or password",Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegisterActivity(View view) {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        Button button = findViewById(R.id.register);
        startActivity(intent);
    }
}