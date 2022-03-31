package com.alfuvedan.hrmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.alfuvedan.hrmanager.data.AccessTiers;
import com.alfuvedan.hrmanager.data.LoginInfo;
import com.alfuvedan.hrmanager.data.LoginInfoSaver;

public class RegisterUserActivity extends AppCompatActivity
{

    private EditText accessTier, emailAddr, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        LoginInfoSaver.getLoginInfoFromFile(this);

        this.accessTier = findViewById(R.id.accessTier);
        this.emailAddr = findViewById(R.id.email);
        this.passwd = findViewById(R.id.password);
    }

    public void onRegisterUser(View view) {
        String email = this.emailAddr.getText().toString();
        String passwd = this.passwd.getText().toString();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            this.emailAddr.setError("Please enter a valid email address");
            this.emailAddr.requestFocus();
            return;
        }

        if(LoginInfoSaver.emailExists(email)) {
            this.emailAddr.setError("This email already exists");
            this.emailAddr.requestFocus();
            return;
        }

        if(passwd.isEmpty()) {
            this.passwd.setError("Please enter a valid password");
            this.passwd.requestFocus();
            return;
        }

        try {
            int accessTier = Integer.parseInt(this.accessTier.getText().toString());

            LoginInfo info = new LoginInfo(accessTier, email, passwd);
            LoginInfoSaver.loginAdd(info);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        catch(IllegalArgumentException e) {
            this.accessTier.setError("Access tier must be a number from 1 to " + AccessTiers.LAST_TIER);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        LoginInfoSaver.saveLoginInfo(this);
    }
}
