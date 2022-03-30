package com.alfuvedan.hrmanager.session;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.*;

import com.alfuvedan.hrmanager.data.LoginInfo;
import com.alfuvedan.hrmanager.data.save.DataSaver;

import java.io.File;
import java.io.IOException;

public class SessionInfo {
    private static final String SAVE_FILE = "session.csv";

    private static @Nullable LoginInfo loginInfo;

    private static File getSaveFile(@NonNull Context context) {
        return new File(context.getDataDir(), SAVE_FILE);
    }

    public static void login(@NonNull Context context, @NonNull LoginInfo loginInfo) {
        SessionInfo.loginInfo = loginInfo;
        saveSession(context);
    }

    public static void logout(@NonNull Context context) {
        SessionInfo.loginInfo = null;
        deleteSession(context);
    }

    public static @Nullable LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public static boolean isLoggedIn() {
        return getLoginInfo() != null;
    }

    private static void saveSession(@NonNull Context context) {
        if(loginInfo == null)
            throw new IllegalArgumentException("No login session to save");

        try {
            DataSaver.saveData(getSaveFile(context), loginInfo);
        }
        catch (IOException e) {
            Toast.makeText(context, "Error: could not save login session", Toast.LENGTH_SHORT).show();
        }
    }

    public static void loadSessionData(@NonNull Context context) {
        try {
            loginInfo = DataSaver.readSingleData(getSaveFile(context), LoginInfo::new);
        }
        catch(IOException e) {
            System.out.println("Could not load session data");
        }
    }

    private static void deleteSession(@NonNull Context context) {
        DataSaver.clearData(getSaveFile(context));
    }
}
