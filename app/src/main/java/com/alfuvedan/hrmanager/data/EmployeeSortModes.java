package com.alfuvedan.hrmanager.data;

public class EmployeeSortModes {
    public static final int BY_ID = 0;
    public static final int BY_FIRST_NAME = 1;
    public static final int BY_LAST_NAME = 2;
    public static final int BY_DEPARTMENT = 3;
    public static final int BY_JOB_TITLE = 4;
    public static final int BY_SALARY = 5;
    public static final int LAST_SORT_MODE = BY_SALARY;

    public static final int ASCENDING = 1;
    public static final int DESCENDING = -1;

    /**
     * This constructor is used to prevent the instantiation of this class
     */
    private EmployeeSortModes() {
        throw new RuntimeException("This class should not be instantiated");
    }
}
