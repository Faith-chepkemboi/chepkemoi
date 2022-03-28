package com.example.moreparkk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisterActivity extends AppCompatActivity {
ImageView back_to_login;
    EditText editTextConPassword;
    EditText editTextEmail;
    EditText editTextNumber;
    EditText editTextPassword;
    EditText editTextPersonName;
    EditText editTextPhone;
    Button register_user;
    password_db myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDatabase=new password_db(this);
        editTextConPassword=findViewById(R.id.conpass);
        editTextEmail=findViewById(R.id.email);
        editTextNumber=findViewById(R.id.phone);
        editTextPersonName=findViewById(R.id.fnames);
        editTextPhone=findViewById(R.id.phone);
        editTextPassword=findViewById(R.id.password);
        Button register_user=findViewById(R.id.register);
        
        Register_user();

        back_to_login=findViewById(R.id.back_to_login);
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void Register_user() {
        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String conpass=editTextConPassword.getText().toString();
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                String username=editTextPersonName.getText().toString();
                String pnumber=editTextNumber.getText().toString();
                String id_number=editTextPhone.getText().toString();

                if (RegisterActivity.this.myDatabase.checkid(id_number)) {
                    RegisterActivity.this.editTextEmail.setError((CharSequence)"Id number Exists");
                }
                else if (!conpass.equals(password)) {
                    Toast.makeText((Context)RegisterActivity.this, (CharSequence)"Password inserted is not the same", (int)0).show();
                }
                else if (!isEmailValid(email)) {
                    Toast.makeText((Context)RegisterActivity.this, (CharSequence)"Not valid email", (int)0).show();
                }
                else if (!isPasswordValid(password)) {
                    Toast.makeText((Context)RegisterActivity.this, (CharSequence)"Too small password length", (int)0).show();
                }
                else if (email.isEmpty()) {
                    Toast.makeText((Context)RegisterActivity.this, (CharSequence)"email field is empty", (int)0).show();
                }
                else if (password.isEmpty()) {
                    Toast.makeText((Context)RegisterActivity.this, (CharSequence)"Password field required", (int)0).show();
                }
                else{
                  Boolean isInserted=myDatabase.insertData(email,password,username,pnumber,id_number);
                  new SweetAlertDialog(RegisterActivity.this,SweetAlertDialog.SUCCESS_TYPE)
                  .setTitleText("message")
                  .setContentText("You are registered")
                   .setConfirmText("OK")
                   .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                       @Override
                       public void onClick(SweetAlertDialog sweetAlertDialog) {
                           Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                           startActivity(i);

                       }
                   })
                  .show();


                }

            }
        });
    }
    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }





}