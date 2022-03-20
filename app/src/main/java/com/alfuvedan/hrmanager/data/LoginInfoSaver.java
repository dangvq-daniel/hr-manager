package com.alfuvedan.hrmanager.data;

import androidx.annotation.NonNull;

import com.alfuvedan.hrmanager.data.save.DataSaver;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoginInfoSaver
{
    private static final ArrayList<LoginInfo> loginList = new ArrayList <>();

    public static void loginAdd(@NonNull LoginInfo l){
        loginList.add(l);
    }

    public static boolean loginVerify(@NonNull LoginInfo l){
        for(LoginInfo login: loginList){
            if(l.equals(login)){
                return true;
            }
        }
        return false;
    }

}

