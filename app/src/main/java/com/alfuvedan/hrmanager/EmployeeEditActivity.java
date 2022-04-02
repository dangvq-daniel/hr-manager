package com.alfuvedan.hrmanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alfuvedan.hrmanager.data.*;

public class EmployeeEditActivity extends AppCompatActivity {

    private EditText firstName, lastName, emailAddr, department, jobTitle, salary;
    private @Nullable Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_edit);

        Employees.getEmployeesFromFile(this);

        Intent intent = this.getIntent();
        long id = intent.getLongExtra(EmployeeTableActivity.EMPLOYEE_ID_INTENT, -1);

        this.firstName = findViewById(R.id.firstName);
        this.lastName = findViewById(R.id.lastName);
        this.emailAddr = findViewById(R.id.emailAddr);
        this.department = findViewById(R.id.department);
        this.jobTitle = findViewById(R.id.jobTitle);
        this.salary = findViewById(R.id.salary);

        int btnStr;

        if(id != -1) {
            this.employee = Employees.getByID(id);

            if(employee != null) {
                this.firstName.setText(employee.getFirstName());
                this.lastName.setText(employee.getLastName());
                this.emailAddr.setText(employee.getEmail());
                this.department.setText(employee.getDepartment());
                this.jobTitle.setText(employee.getJobTitle());
                this.salary.setText(String.valueOf(employee.getSalary()));
            }

            btnStr = R.string.edit_employee_btn;
            findViewById(R.id.removeEmployee).setVisibility(View.VISIBLE);
        }
        else {
            btnStr = R.string.create_employee_btn;
            findViewById(R.id.removeEmployee).setVisibility(View.GONE);
        }

        Button btn = findViewById(R.id.addEmployee);
        btn.setText(btnStr);
    }

    public void onAddOrEditEmployee(View view) {
        String firstNameText = this.firstName.getText().toString(),
                lastNameText = this.lastName.getText().toString(),
                emailText = this.emailAddr.getText().toString(),
                departmentText = this.department.getText().toString(),
                jobTitleText = this.jobTitle.getText().toString(),
                salaryText = this.salary.getText().toString();

        double salary;

        if(firstNameText.isEmpty()) {
            this.firstName.setError("Cannot be empty");
            return;
        }

        if(lastNameText.isEmpty()) {
            this.lastName.setError("Cannot be empty");
            return;
        }

        if(emailText.isEmpty()) {
            this.emailAddr.setError("Cannot be empty");
            return;
        }

        if(departmentText.isEmpty()) {
            this.department.setError("Cannot be empty");
            return;
        }

        if(jobTitleText.isEmpty()) {
            this.jobTitle.setError("Cannot be empty");
            return;
        }

        try {
            salary = Double.parseDouble(salaryText);
        }
        catch(NumberFormatException e) {
            this.salary.setError("Must be a number");
            return;
        }

        if(this.employee != null) {
            this.employee.setFirstName(firstNameText);
            this.employee.setLastName(lastNameText);
            this.employee.setEmail(emailText);
            this.employee.setDepartment(departmentText);
            this.employee.setJobTitle(jobTitleText);
            this.employee.setSalary(salary);
        }
        else {
            this.employee = new Employee(new PersonalInfo(firstNameText, lastNameText, emailText),
                    new JobInfo(departmentText, jobTitleText, salary));

            Employees.addEmployee(this.employee);
        }

        Employees.saveEmployees(this);

        Intent intent = new Intent(this, EmployeeTableActivity.class);
        startActivity(intent);

        this.finish();
    }

    public void onRemoveEmployee(View view) {
        if(this.employee != null) {
            Employees.removeEmployee(this.employee.getID());
            Employees.saveEmployees(this);

            Intent intent = new Intent(this, EmployeeTableActivity.class);
            startActivity(intent);

            this.finish();
        }
    }
}