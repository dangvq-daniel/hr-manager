package com.alfuvedan.hrmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.alfuvedan.hrmanager.data.*;
import com.alfuvedan.hrmanager.session.SessionInfo;

import java.util.*;

public class EmployeeTableActivity extends AppCompatActivity {

    public static final String EMPLOYEE_ID_INTENT = "com.alfuvedan.hrmanager.employee_id";

    private TableLayout employeeTable;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_table);

        SessionInfo.loadSessionData(this);
        Employees.getEmployeesFromFile(this);

        this.employeeTable = findViewById(R.id.employeeTable);
        this.populateTable();

        this.spinner = findViewById(R.id.sortMode);

        ArrayList<String> options = new ArrayList<>(Arrays.asList(EmployeeSortModes.SORT_MODE_TEXTS));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        SessionInfo.loadSessionData(this);
        if(!SessionInfo.isLoggedIn())
            this.finish();

        this.populateTable();
    }

    private void populateTable() {
        this.employeeTable.removeAllViews();

        ArrayList<Employee> employees = Employees.getAllEmployees();
        Collections.sort(employees);

        for(Employee employee : employees) {
            this.insertEmployeeTableRow(employee);
        }
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

        salaryView.setText(String.format(Locale.ROOT, "$%.2f", employee.getSalary()));
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

        editButton.setOnClickListener(btn -> {
            if(SessionInfo.getLoginInfo() != null && SessionInfo.getLoginInfo().getAcessTier() < AccessTiers.TIER_2) {
                Toast.makeText(this, "Only Tier " + AccessTiers.TIER_2 + " and above users can edit employee data", Toast.LENGTH_SHORT).show();
                return;
            }

            goToEmployeeEditActivity(employee);
        });

        tableRow.addView(editButton);

        this.employeeTable.addView(tableRow);
    }

    public void onLogout(View view) {
        SessionInfo.logout(this);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onSwitchSortMode(View view) {
        int pos = this.spinner.getSelectedItemPosition();

        Employee.setSortMode(pos, EmployeeSortModes.ASCENDING);
        this.populateTable();
    }

    public void onAddEmployee(View view) {
        if(SessionInfo.getLoginInfo() != null && SessionInfo.getLoginInfo().getAcessTier() < AccessTiers.TIER_2) {
            Toast.makeText(this, "Only Tier " + AccessTiers.TIER_2 + " and above users can edit employee data", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, EmployeeEditActivity.class);
        startActivity(intent);
    }

    public void goToEmployeeEditActivity(@Nullable Employee employee) {
        Intent intent = new Intent(this, EmployeeEditActivity.class);

        if(employee != null) {
            intent.putExtra(EMPLOYEE_ID_INTENT, employee.getID());
        }

        startActivity(intent);
    }

    public void goToEmployeeSearchActivity(View view) {
        Intent intent = new Intent(this, EmployeeSearchActivity.class);
        startActivity(intent);
    }
}