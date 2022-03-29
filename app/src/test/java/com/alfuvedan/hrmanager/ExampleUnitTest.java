package com.alfuvedan.hrmanager;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

import com.alfuvedan.hrmanager.data.*;

import java.util.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private List<Employee> exampleList() {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(new PersonalInfo("Tim", "Horton", "timhortons@testdomain.com"),
                new JobInfo("Marketing", "Product Marketing Manager", 51234.56)));

        employees.add(new Employee(new PersonalInfo("Mike", "Donaldson", "mcdonalds@testdomain.com"),
                new JobInfo("Health", "Doctor", 123456.78)));

        employees.add(new Employee(new PersonalInfo("Wendy", "TheRestaurant", "wendys@testdomain.com"),
                new JobInfo("Finance", "Financial Analyst", 71717.17)));

        employees.add(new Employee(new PersonalInfo("Burger", "King", "burgerking@testdomain.com"),
                new JobInfo("Janitor", "Janitor", 33333.33)));

        return employees;
    }

    @Test
    public void employee_init_test() {
        Employee employee = new Employee(new PersonalInfo("Test", "Name", "someemail@alfuvedan.com"),
                new JobInfo("Finance", "Financial Analyst", 71717.17));

        assertEquals(employee.getName(), "Test Name");
    }

    @Test
    public void employee_sort_test_0() {
        List<Employee> employees = this.exampleList();

        Employee.setSortMode(EmployeeSortModes.BY_ID, EmployeeSortModes.ASCENDING);
        Collections.sort(employees);

        String[] expected0 = { "Tim", "Mike", "Wendy", "Burger" };

        for(int i = 0; i < expected0.length; i++) {
            assertEquals(expected0[i], employees.get(i).getFirstName());
        }

        Employee.setSortMode(EmployeeSortModes.BY_ID, EmployeeSortModes.DESCENDING);
        Collections.sort(employees);

        String[] expected1 = { "Burger", "Wendy", "Mike", "Tim" };

        for(int i = 0; i < expected1.length; i++) {
            assertEquals(expected1[i], employees.get(i).getFirstName());
        }
    }

    @Test
    public void employee_sort_test_1() {
        List<Employee> employees = this.exampleList();

        Employee.setSortMode(EmployeeSortModes.BY_FIRST_NAME, EmployeeSortModes.ASCENDING);
        Collections.sort(employees);

        String[] expected = { "Burger", "Mike", "Tim", "Wendy" };

        for(int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], employees.get(i).getFirstName());
        }
    }
}