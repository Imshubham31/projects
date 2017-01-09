package com.vivacollege.medikart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by shubham on 08/01/17.
 */

public class Register extends AppCompatActivity {
    private EditText age, name, username, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        age = (EditText) findViewById(R.id.age);
        name = (EditText) findViewById(R.id.name);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Button bRegisterLink = (Button) findViewById(R.id.register);
        Button bCancelLink = (Button) findViewById(R.id.cancel);

        bRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageValue = age.getText().toString();
                String nameValue = name.getText().toString();
                String userNameValue = username.getText().toString();
                String passwordValue = password.getText().toString();

                if (ageValue.equals("") || nameValue.equals("") || userNameValue.equals("") || passwordValue.equals(""))
                    Toast.makeText(Register.this, "Enter mandatory details!", Toast.LENGTH_SHORT).show();
                else if (Integer.parseInt(ageValue) < 10 && Integer.parseInt(ageValue) > 100)
                    Toast.makeText(Register.this, "Enter valid age below 100", Toast.LENGTH_SHORT).show();
                else if (passwordValue.length() < 8)
                    Toast.makeText(Register.this, "password length must be above 8 digit", Toast.LENGTH_SHORT).show();
                else if (!isAlphaNumeric(passwordValue))
                    Toast.makeText(Register.this, "password must be combination of letter, number, specail character.", Toast.LENGTH_SHORT).show();
                else {
                    SharedPreferences preference = getSharedPreferences("myPreference", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putString("userName", userNameValue);
                    editor.putString("password", passwordValue);
                    editor.commit();

                    Intent RegisterIntent = new Intent(Register.this, Login.class);
                    RegisterIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    RegisterIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Register.this.startActivity(RegisterIntent);
                }
            }
        });

        bCancelLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register.super.onBackPressed();
            }
        });
    }

    public boolean isAlphaNumeric(String s) {
        String pattern = "^([a-zA-Z+]+[&@!#+]+[0-9+]+)$";
        return s.matches(pattern);
    }
}
