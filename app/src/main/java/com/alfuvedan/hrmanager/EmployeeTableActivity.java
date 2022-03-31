package com.alfuvedan.hrmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import com.alfuvedan.hrmanager.data.Employee;
import com.alfuvedan.hrmanager.data.Employees;
import com.alfuvedan.hrmanager.session.SessionInfo;

import java.util.Locale;

public class EmployeeTableActivity extends AppCompatActivity {

    private TableLayout employeeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_table);

        SessionInfo.loadSessionData(this);
        Employees.getEmployeesFromFile(this);

        if(!SessionInfo.isLoggedIn())
            this.goToMainActivity();

        this.employeeTable = findViewById(R.id.employeeTable);

        for(Employee employee : Employees.getAllEmployees()) {
            this.insertEmployeeTableRow(employee);
        }
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void insertEmployeeTableRow(@NonNull Employee employee) {
        TableRow tableRow = new TableRow(this);
        TextView idView, nameView, emailView, jobView, salaryView;

        idView = new TextView(this);

        idView.setText(String.valueOf(employee.getID()));
        idView.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 1.0f));

        nameView = new TextView(this);

        nameView.setText(employee.getName());
        nameView.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 2.0f));

        emailView = new TextView(this);

        emailView.setText(employee.getEmail());
        emailView.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 3.0f));

        jobView = new TextView(this);

        jobView.setText(employee.getJobString());
        jobView.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 3.0f));

        salaryView = new TextView(this);

        salaryView.setText(String.format(Locale.ROOT, "%.2f", employee.getSalary()));
        salaryView.setLayoutParams(new TableRow.LayoutParams(0,
                TableRow.LayoutParams.WRAP_CONTENT, 2.0f));

        tableRow.addView(idView);
        tableRow.addView(nameView);
        tableRow.addView(emailView);
        tableRow.addView(jobView);
        tableRow.addView(salaryView);

        Button editButton = new Button(this);

        editButton.setText(R.string.edit_label);
        editButton.setLayoutParams(new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1.0f));

        tableRow.addView(editButton);

        this.employeeTable.addView(tableRow);
    }


}