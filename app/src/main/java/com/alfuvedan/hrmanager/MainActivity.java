package com.alfuvedan.hrmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import com.alfuvedan.hrmanager.data.*;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        LoginInfoSaver.loginAdd(new LoginInfo("timhortons@icecap.com","timbits"));
        LoginInfoSaver.loginAdd(new LoginInfo("burgerking@bestburger.com","justjkitsucks"));

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

        if (!LoginInfoSaver.loginVerify(new LoginInfo(email,password))) {
            Toast.makeText(this,"Incorrect email or password",Toast.LENGTH_SHORT).show();
        }
    }

    public void goToRegisterActivity(View view) {

    }
}