package com.alfuvedan.hrmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.alfuvedan.hrmanager.data.*;
import com.alfuvedan.hrmanager.session.SessionInfo;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        SessionInfo.loadSessionData(this);
    }

    public void goToEmployeeTable(View view){
        Intent intent = new Intent(this, EmployeeTableActivity.class);
        startActivity(intent);
    }

    public void gotoOption(View view){
        LoginInfo sessionInfo = SessionInfo.getLoginInfo();

        if(sessionInfo == null)
            return;

        Class<? extends Activity> intentActivityClass;

        switch(sessionInfo.getAcessTier()) {
            case AccessTiers.TIER_1:
                intentActivityClass = OptionLevel1.class;
                break;

            case AccessTiers.TIER_2:
                intentActivityClass = OptionLevel2.class;
                break;

            default:
                throw new IllegalArgumentException("Invalid access tier");
        }

        Intent intent = new Intent(this, intentActivityClass);
        startActivity(intent);
    }
}
