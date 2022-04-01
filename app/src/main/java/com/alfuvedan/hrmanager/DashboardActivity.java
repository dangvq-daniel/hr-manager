package com.alfuvedan.hrmanager;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.alfuvedan.hrmanager.data.*;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void goToEmployeeTable(){
        Intent intent = new Intent(this, EmployeeTableActivity.class);
        startActivity(intent);
    }

    public void gotoOption(){
        if (1 == LoginInfo.getAcessTier()) {
            Intent intent = new Intent(this, OptionLevel1.class);
            startActivity(intent);
        }
        else if (LoginInfo.getAcessTier() == 2){
            Intent intent = new Intent(this, OptionLevel2.class);
            startActivity(intent);
        }
        else{
            throw new IllegalArgumentException("Invalid Access Tier");
        }
    }
}
