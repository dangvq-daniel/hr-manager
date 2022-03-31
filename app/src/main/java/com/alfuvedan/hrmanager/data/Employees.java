package com.alfuvedan.hrmanager.data;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alfuvedan.hrmanager.data.save.DataSaver;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Employees {
    private static final HashMap<Long, Employee> es = new HashMap<>();
    private static final String SAVE_FILE_NAME = "employees.csv";

    public static void addEmployee(@NonNull Employee e){
        es.put(e.getID() , e);
    }

    private static File getSaveFile(@NonNull Context context) {
        return new File(context.getDataDir(), SAVE_FILE_NAME);
    }

    public static void removeEmployee(long id){
        if(es.containsKey(id))
            throw new IllegalArgumentException("There is no employee with id " + id);

        es.remove(id);
    }

    public static @Nullable Employee getByID(long id) {
        return es.get(id);
    }

    public static ArrayList<Employee> getAllEmployees() {
        return new ArrayList<>(es.values());
    }

    public static void saveEmployees(@NonNull Context context) {
        try {
            DataSaver.saveData(getSaveFile(context), es.values());
        }
        catch(IOException e) {
            Toast.makeText(context, "Error: Could not save employee data",
                    Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }
    }

    public static void getEmployeesFromFile(@NonNull Context context) {
        try {
            es.clear();
            Collection<Employee> employees = DataSaver.readData(getSaveFile(context), Employee::new);

            for(Employee employee : employees) {
                addEmployee(employee);
            }
        }
        catch(IOException e) {
            Toast.makeText(context, "Error: could not read employee data",
                    Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }
    }

}
