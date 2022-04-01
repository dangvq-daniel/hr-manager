package com.alfuvedan.hrmanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.alfuvedan.hrmanager.data.AccessTiers;
import com.alfuvedan.hrmanager.data.Employee;
import com.alfuvedan.hrmanager.data.Employees;
import com.alfuvedan.hrmanager.session.SessionInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class EmployeeSearchActivity extends AppCompatActivity {

    private EditText search;
    private TableLayout table;
    public static final String EMPLOYEE_ID_INTENT = "com.alfuvedan.hrmanager.employee_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_search);

        this.search = findViewById(R.id.searchEditText);
        this.table = findViewById(R.id.searchTable);

        SessionInfo.loadSessionData(this);
        Employees.getEmployeesFromFile(this);
    }

    public void onSearch(View view){
        String searchContent = this.search.getText().toString();
        this.table.removeAllViews();
        if(searchContent.isEmpty())
            return;

        List<Employee> emps = Employees.getAllEmployees();
        List<Employee> filteredEmps = new ArrayList<>();

        // Only searches for full keywords (no partial searches)
        for(Employee e: emps){
            if(searchContent.equalsIgnoreCase(e.getFirstName())){
                filteredEmps.add(e);
            } else if (searchContent.equalsIgnoreCase(e.getLastName())) {
                filteredEmps.add(e);
            } else if (searchContent.equalsIgnoreCase(e.getEmail())) {
                filteredEmps.add(e);
            } else if (searchContent.equals(e.getID() + "")) {
                filteredEmps.add(e);
            } else if (searchContent.equalsIgnoreCase(e.getDepartment())) {
                filteredEmps.add(e);
            } else if (searchContent.equalsIgnoreCase(e.getJobString())) {
                filteredEmps.add(e);
            } else if (searchContent.equalsIgnoreCase(e.getJobTitle())) {
                filteredEmps.add(e);
            } else if (searchContent.equals(e.getSalary() + "")) {
                filteredEmps.add(e);
            }
        }

        Collections.sort(filteredEmps);

        for(Employee employee : filteredEmps) {
            this.insertEmployeeTableRow(employee);
        }

        if(filteredEmps.size() == 0 ){
            Context context = getApplicationContext();
            CharSequence text = "No results found!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
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

        this.table.addView(tableRow);
    }

    public void goToEmployeeEditActivity(@Nullable Employee employee) {
        Intent intent = new Intent(this, EmployeeEditActivity.class);

        if(employee != null) {
            intent.putExtra(EMPLOYEE_ID_INTENT, employee.getID());
        }

        startActivity(intent);
    }

    public void finish(View view) {
        this.finish();
    }
}