package com.alfuvedan.hrmanager.data;

import android.content.Context;

import androidx.annotation.NonNull;

import com.alfuvedan.hrmanager.data.save.DataSaver;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LoginInfoSaver
{
    private static final ArrayList<LoginInfo> loginList = new ArrayList <>();
    private static final String SAVE_FILE_NAME = "login.csv";

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

    private static File getSaveFile(@NonNull Context context) {
        return new File(context.getDataDir(), SAVE_FILE_NAME);
    }

    private static void saveLoginInfo(@NonNull Context context) {
        try {
            DataSaver.saveData(getSaveFile(context), loginList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void getLoginInfoFromFile(@NonNull Context context) {
        try {
            loginList.clear();
            Collection<LoginInfo> savedLoginInfo = DataSaver.readData(getSaveFile(context), LoginInfo::new);

            for(LoginInfo loginInfo : savedLoginInfo) {
                loginAdd(loginInfo);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}

