package com.alfuvedan.hrmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alfuvedan.hrmanager.data.Employee;
import com.alfuvedan.hrmanager.data.JobInfo;
import com.alfuvedan.hrmanager.data.LoginInfo;
import com.alfuvedan.hrmanager.data.LoginInfoSaver;
import com.alfuvedan.hrmanager.data.PersonalInfo;
import com.alfuvedan.hrmanager.data.save.DataSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        LoginInfoSaver.loginAdd(new LoginInfo("timhortons@icecap.com","timbits"));
        LoginInfoSaver.loginAdd(new LoginInfo("burgerking@bestburger.com","justjkitsucks"));
    }

    public void testStuff(View view) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(new PersonalInfo("Tim", "Horton", "timhortons@testdomain.com"),
                new JobInfo("Marketing", "Product Marketing Manager", 51234.56)));

        employees.add(new Employee(new PersonalInfo("Mike", "Donaldson", "mcdonalds@testdomain.com"),
                new JobInfo("Health", "Doctor", 123456.78)));

        employees.add(new Employee(new PersonalInfo("Wendy", "TheRestaurant", "wendys@testdomain.com"),
                new JobInfo("Finance", "Financial Analyst", 71717.17)));

        employees.add(new Employee(new PersonalInfo("Burger", "King", "burgerking@testdomain.com"),
                new JobInfo("Janitor", "Janitor", 33333.33)));

        try {
            DataSaver.saveData(new File(this.getDataDir(), "employees.csv"), employees);
        }
        catch (IOException exc) {
            Toast.makeText(this, exc.getMessage(), Toast.LENGTH_SHORT).show();
            exc.printStackTrace();

            return;
        }

        Toast.makeText(this, "Nice", Toast.LENGTH_SHORT).show();
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

        if (!Patterns.EMAIL_ADDRESS.equals(email)) { //if user provides wrong type of email address ask again
            editTextEmail.setError("Please provide valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (!LoginInfoSaver.loginVerify(new LoginInfo(email,password))){
            Toast.makeText(this,"Not Nice",Toast.LENGTH_SHORT).show();
        }
    }
}