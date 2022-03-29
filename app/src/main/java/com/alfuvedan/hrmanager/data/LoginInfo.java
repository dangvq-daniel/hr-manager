package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import com.alfuvedan.hrmanager.data.save.ISavableData;

import java.util.Locale;
import java.util.Objects;

public class LoginInfo implements ISavableData
{
    private String email;
    private String password;
    private int accessTier;

    public LoginInfo(int accessTier, @NonNull String email, @NonNull String password){
        this.email = email;
        this.password = password;
        this.setAccessTier(accessTier);
    }

    public LoginInfo(@NonNull String[] csvRow) {
        if(csvRow.length != 3)
            throw new IllegalArgumentException(String.format(Locale.ROOT,
                    "There may only be %d entries in this row (Found %d)",
                    this.getSaveData().length, csvRow.length));

        this.email = csvRow[0];
        this.password = csvRow[1];
        this.setAccessTier(Integer.parseInt(csvRow[2]));
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

    public int getAcessTier() {
        return this.accessTier;
    }

    public void setAccessTier(int accessTier) {
        if(accessTier <= 0 || accessTier > AccessTiers.LAST_TIER)
            throw new IllegalArgumentException("Invalid access tier");

        this.accessTier = accessTier;
    }

    @NonNull
    @Override
    public String[] getSaveData()
    {
        return new String[] {
                this.getEmail(),
                this.getPassword(),
                String.valueOf(this.getAcessTier())
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginInfo loginInfo = (LoginInfo) o;
        return email.equals(loginInfo.email) && password.equals(loginInfo.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
