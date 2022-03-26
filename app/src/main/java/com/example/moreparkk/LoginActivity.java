package com.example.moreparkk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
ImageView got_reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        got_reg=findViewById(R.id.goto_reg);
        got_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);

            }
        });
    }
}