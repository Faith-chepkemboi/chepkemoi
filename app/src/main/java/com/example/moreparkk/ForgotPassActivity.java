package com.example.moreparkk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPassActivity extends AppCompatActivity {
    TextView forgotPass;
    EditText email;
    Button resetpass;
     password_db myDatabaseForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        forgotPass=findViewById(R.id.fogetpass);
        email=findViewById(R.id.emaill);
        resetpass=findViewById(R.id.reset);

        myDatabaseForgot=new password_db(this);

    }
}