package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import java.util.Locale;

public class EmployeeGenerator {

    private static final String[] FIRST_NAMES = {

    };

    private static final String[] LAST_NAMES = {

    };

    private static final String EMAIL_DOMAIN = "alfuvedan.com"; //Feel free to change this

    /**
     * This constructor is used to prevent the instantiation of this class
     */
    private EmployeeGenerator() {
        throw new RuntimeException("This class should not be instantiated");
    }

    private static String getEmail(@NonNull String firstName, @NonNull String lastName) {
        return String.format(Locale.ROOT,"%s%s@%s",
                firstName.toLowerCase(Locale.ROOT), lastName.toLowerCase(Locale.ROOT), EMAIL_DOMAIN);
    }
}
