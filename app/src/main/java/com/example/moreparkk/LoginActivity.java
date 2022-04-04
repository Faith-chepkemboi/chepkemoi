package com.example.moreparkk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    ImageView got_reg;

    EditText editTextEmail, editTextPassword;
    Button button_login;
    TextView forgotpasss;

    password_db mydatabasepassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydatabasepassword = new password_db(this);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        button_login = findViewById(R.id.login);
        forgotpasss = findViewById(R.id.fogetpass);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attempt_login();

            }
        });


        got_reg = findViewById(R.id.goto_reg);
        got_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);

            }
        });
        forgotpasss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i= new Intent(LoginActivity.this,ForgotPassActivity.class);
                startActivity(i);
            }
        });
    }


    private void attempt_login() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!isEmaildValid(email)) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_SHORT).show();

        } else {

            if(mydatabasepassword.Check_email_password(email, password))
            {
                final Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
            else {
                Toast.makeText(this, "Invalid Account", Toast.LENGTH_SHORT).show();
            }

            //Cursor res = mydatabasepassword.login_user(email, password);
            //if (res.getCount() == 1) {

        }

    }





    private  boolean isEmaildValid(String email){
        return email.contains("@");
    }
    private boolean isPasswordValid(String password){
        return password.length() >5;
    }


}