package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import com.alfuvedan.hrmanager.data.save.ISavableData;

import java.util.Locale;
import java.util.Objects;

public class LoginInfo implements ISavableData
{
    private String email;
    private String password;

    public LoginInfo(@NonNull String email, @NonNull String password){
        this.email = email;
        this.password = password;
    }

    public LoginInfo(@NonNull String[] csvRow) {
        if(csvRow.length != this.getSaveData().length)
            throw new IllegalArgumentException(String.format(Locale.ROOT,
                    "There may only be %d entries in this row (Found %d)",
                    this.getSaveData().length, csvRow.length));

        this.email = csvRow[0];
        this.password = csvRow[1];
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
    }

    @NonNull
    @Override
    public String[] getSaveData()
    {
        return new String[] {
                this.getEmail(),
                this.getPassword()
        };
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginInfo loginInfo = (LoginInfo) o;
        return email.equals(loginInfo.email) && password.equals(loginInfo.password);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(email, password);
    }
}
