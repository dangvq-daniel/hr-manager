package com.alfuvedan.hrmanager.data;

public class Employee implements Comparable<Employee> {
    private static long lastID = 1;
    private static int sortMode = 0;

    private final long id;

    private final PersonalInfo personalInfo;
    private final JobInfo jobInfo;

    public Employee(PersonalInfo personalInfo, JobInfo jobInfo) {
        this.id = lastID++;
        this.personalInfo = personalInfo;
        this.jobInfo = jobInfo;
    }


    @Override
    public int compareTo(Employee employee) {
        return 0;
    }
}
