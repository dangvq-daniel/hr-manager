package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * This class contains information about an employee's job. It was made to reduce the amount of
 * parameters in the <code>Employee</code> constructor
 */
public class JobInfo {
    private String department;

    private String title;
    private double salary;

    public JobInfo(String department, String title, double salary) {
        this.department = department;
        this.title = title;
        this.salary = salary;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary < 0.0)
            throw new IllegalArgumentException("Salary must be non-negative");

        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobInfo jobInfo = (JobInfo) o;
        return Double.compare(jobInfo.salary, salary) == 0 && Objects.equals(department, jobInfo.department) && Objects.equals(title, jobInfo.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(department, title, salary);
    }

    @NonNull
    @Override
    public String toString() {
        return "JobInfo{" +
                "department='" + department + '\'' +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                '}';
    }
}
