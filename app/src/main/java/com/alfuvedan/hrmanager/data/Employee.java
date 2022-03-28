package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import com.alfuvedan.hrmanager.data.save.ISavableData;

import java.util.Locale;

public class Employee implements Comparable<Employee>, ISavableData {
    private static long lastID = 1;

    private static int sortMode = EmployeeSortModes.BY_ID, sortOrder = EmployeeSortModes.ASCENDING;

    private final long id;

    private final PersonalInfo personalInfo;
    private final JobInfo jobInfo;

    public Employee(PersonalInfo personalInfo, JobInfo jobInfo) {
        if(personalInfo == null || jobInfo == null)
            throw new IllegalArgumentException("Arguments cannot be null");

        this.id = lastID++;
        this.personalInfo = personalInfo;
        this.jobInfo = jobInfo;
    }

    public Employee(String[] csvRow) {
        if(csvRow.length != 7)
            throw new IllegalArgumentException(String.format(Locale.ROOT,
                    "There may only be %d entries in this row (Found %d)",
                    this.getSaveData().length, csvRow.length));

        this.id = Integer.parseInt(csvRow[0]);
        lastID = Math.max(this.id + 1, lastID);

        String firstName = csvRow[1], lastName = csvRow[2], email = csvRow[3];
        this.personalInfo = new PersonalInfo(firstName, lastName, email);

        String department = csvRow[4], jobTitle = csvRow[5];
        double salary = Double.parseDouble(csvRow[6]);

        this.jobInfo = new JobInfo(department, jobTitle, salary);
    }

    public static void setSortMode(int sortMode, int sortOrder) {
        if(sortMode < 0 || sortMode > EmployeeSortModes.LAST_SORT_MODE)
            throw new IllegalArgumentException("Unknown sort mode");

        if(sortOrder != EmployeeSortModes.ASCENDING && sortOrder != EmployeeSortModes.DESCENDING)
            throw new IllegalArgumentException("Sort order must either be ascending (1) or descending (-1)");

        Employee.sortMode = sortMode;
        Employee.sortOrder = sortOrder;
    }

    public long getID() {
        return this.id;
    }

    public String getFirstName() {
        return this.personalInfo.getFirstName();
    }

    public void setFirstName(@NonNull String firstName) {
        this.personalInfo.setFirstName(firstName);
    }

    public String getLastName() {
        return this.personalInfo.getLastName();
    }

    public void setLastName(@NonNull String firstName) {
        this.personalInfo.setLastName(firstName);
    }

    public String getName() {
        return this.personalInfo.getName();
    }

    public String getEmail() {
        return this.personalInfo.getEmail();
    }

    public void setEmail(@NonNull String email) {
        this.personalInfo.setEmail(email);
    }

    public String getDepartment() {
        return this.jobInfo.getDepartment();
    }

    public void setDepartment(@NonNull String department) {
        this.jobInfo.setDepartment(department);
    }

    public String getJobTitle() {
        return this.jobInfo.getTitle();
    }

    public void setJobTitle(@NonNull String title) {
        this.jobInfo.setTitle(title);
    }

    public double getSalary() {
        return this.jobInfo.getSalary();
    }

    public void setSalary(double salary) {
        this.jobInfo.setSalary(salary);
    }

    @Override
    public int compareTo(Employee employee) {
        int sortRes = 0;

        switch(sortMode) {
            case EmployeeSortModes.BY_ID:
                sortRes = this.getID() < employee.getID() ? -1 : 1;
                break;

            case EmployeeSortModes.BY_FIRST_NAME:
                sortRes = this.getFirstName().compareToIgnoreCase(employee.getFirstName());
                break;

            case EmployeeSortModes.BY_LAST_NAME:
                sortRes = this.getLastName().compareToIgnoreCase(employee.getLastName());
                break;

            case EmployeeSortModes.BY_DEPARTMENT:
                sortRes = this.getDepartment().compareToIgnoreCase(employee.getDepartment());
                break;

            case EmployeeSortModes.BY_JOB_TITLE:
                sortRes = this.getJobTitle().compareToIgnoreCase(employee.getJobTitle());
                break;

            case EmployeeSortModes.BY_SALARY:
                sortRes = this.getSalary() < employee.getSalary() ? -1 : 1;
                break;
        }

        return sortRes * sortOrder;
    }

    @NonNull
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", personalInfo=" + personalInfo +
                ", jobInfo=" + jobInfo +
                '}';
    }

    @NonNull
    @Override
    public String[] getSaveData() {
        return new String[] {
                String.valueOf(this.getID()),
                this.getFirstName(),
                this.getLastName(),
                this.getEmail(),
                this.getDepartment(),
                this.getJobTitle(),
                String.valueOf(this.getSalary())
        };
    }
}
