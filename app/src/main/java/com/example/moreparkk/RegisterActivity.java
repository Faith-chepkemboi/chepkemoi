package com.example.moreparkk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        myDatabase = new password_db(this);


        editTextConPassword = findViewById(R.id.conpass);
        editTextEmail = findViewById(R.id.email);
        editTextPhone = findViewById(R.id.pnumber);
        editTextNumber = findViewById(R.id.idno);
        editTextPersonName = findViewById(R.id.fnames);
        editTextPassword = findViewById(R.id.password);
        register_user = findViewById(R.id.register);

        Register_user();

        back_to_login = findViewById(R.id.back_to_login);
        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }

    private void Register_user() {
        register_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String conpass = editTextConPassword.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String username = editTextPersonName.getText().toString();
                String id_number = editTextNumber.getText().toString();
                String phone_number = editTextNumber.getText().toString();
                if (!conpass.equals(password)) {
                    Toast.makeText((Context) RegisterActivity.this, (CharSequence) "Password inserted is not the same", (int) 0).show();
                } else if (!isValidMail(email)) {
                    Toast.makeText((Context) RegisterActivity.this, (CharSequence) "Not valid email", (int) 0).show();
                }else if(!isIdValid(id_number)){
                    Toast.makeText(RegisterActivity.this, "Invalid National Identity Number ", Toast.LENGTH_SHORT).show();
                }else if(!validateTwoNames(username)){
                    Toast.makeText(RegisterActivity.this, "Full names required", Toast.LENGTH_SHORT).show();
                }
              else if(validatephone(phone_number)){
                   Toast.makeText(RegisterActivity.this, "Phone Number is not Valid", Toast.LENGTH_SHORT).show();
               }
                else if (!isPasswordValid(password)) {
                    Toast.makeText((Context) RegisterActivity.this, (CharSequence) "Too small password length", (int) 0).show();
                } else if (email.isEmpty()) {
                    Toast.makeText((Context) RegisterActivity.this, (CharSequence) "email field is empty", (int) 0).show();
                } else if (password.isEmpty()) {
                    Toast.makeText((Context) RegisterActivity.this, (CharSequence) "Password field required", (int) 0).show();
                } else if (myDatabase.checkIfExists(email)) {
                    Toast.makeText(RegisterActivity.this, "Email Already Exists", Toast.LENGTH_SHORT).show();
                } else if (myDatabase.checkid(id_number)) {
                    Toast.makeText(RegisterActivity.this, "National id already exists", Toast.LENGTH_SHORT).show();

                } else {
                    if (myDatabase.insertData(email, password, username, id_number, phone_number)) {
                        new SweetAlertDialog(RegisterActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                .setTitleText("message")
                                .setContentText("You are registered")
                                .setConfirmText("OK")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(i);

                                    }
                                })
                                .show();
                    }
                }

            }
        });
    }

//    private boolean isEmailValid(String email) {
//        return email.contains("@");
//    }
private boolean isValidMail(String email) {

    String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    return Pattern.compile(EMAIL_STRING).matcher(email).matches();

}

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

    boolean validatephone(String phone_number) {
        Pattern p = Pattern.compile("[0-9]{10}");
        Matcher m = p.matcher(phone_number);
        return m.matches();
    }
     private boolean isIdValid(String id_number){
        return id_number.length() ==8;
     }
    private static boolean validateTwoNames(String username) {

        String regx = "([A-Z][a-z]{3,} )([A-Z][a-z]{3,} )?([A-Z][a-z]{3,})";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(username);
        return matcher.find();

    }


    }





