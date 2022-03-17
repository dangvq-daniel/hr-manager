package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import java.util.Objects;

/**
 * This class contains an employee's personal information. It was made to reduce the amount of
 * parameters in the <code>Employee</code> constructor
 */
public class PersonalInfo {

    private String firstName, lastName, email;

    public PersonalInfo(@NonNull String firstName, @NonNull String lastName, @NonNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return this.getFirstName() + " " + this.getLastName();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalInfo that = (PersonalInfo) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }

    @NonNull
    @Override
    public String toString() {
        return "PersonalInfo{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
