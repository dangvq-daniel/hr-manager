package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import com.alfuvedan.hrmanager.data.save.DataSaver;
import com.alfuvedan.hrmanager.data.save.ISavableData;

import java.util.Objects;

public class LoginInfo implements ISavableData
{
    String email;
    String password;

    public LoginInfo(String email, String password){
        this.email = email;
        this.password = password;
    }



    @NonNull
    @Override
    public String[] getSaveData()
    {
        return new String[0];
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
