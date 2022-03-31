package com.alfuvedan.hrmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

import com.alfuvedan.hrmanager.data.Employee;
import com.alfuvedan.hrmanager.data.Employees;
import com.alfuvedan.hrmanager.session.SessionInfo;

public class EmployeeTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_table);

        SessionInfo.loadSessionData(this);
        Employees.getEmployeesFromFile(this);

        if(!SessionInfo.isLoggedIn())
            this.goToMainActivity();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void insertEmployeeTableRow(@NonNull Employee employee) {
        TableRow tableRow = new TableRow(this);

        TextView idView, nameView, emailView, jobView, salaryView;
    }
}