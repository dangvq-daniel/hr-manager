package com.alfuvedan.hrmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alfuvedan.hrmanager.data.Employee;
import com.alfuvedan.hrmanager.data.JobInfo;
import com.alfuvedan.hrmanager.data.PersonalInfo;
import com.alfuvedan.hrmanager.data.save.DataSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}