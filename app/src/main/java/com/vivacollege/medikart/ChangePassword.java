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
 * Created by gauravbhoyar on 08/01/17.
 */

public class ChangePassword extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        final EditText etPassword =(EditText) findViewById(R.id.password);
        final EditText etCnfPassword =(EditText) findViewById(R.id.confirm_password);
        final Button bchange =(Button) findViewById(R.id.change);
        final Button bCancel =(Button) findViewById(R.id.cancel);

        bchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString();
                String confirmPassword = etCnfPassword.getText().toString();

                if(password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(ChangePassword.this, "Enter mandatory details!", Toast.LENGTH_SHORT).show();
                else if(!password.equals(confirmPassword))
                    Toast.makeText(ChangePassword.this, "Password confirmPassword doesn't match!", Toast.LENGTH_SHORT).show();
                else if(!isAlphaNumeric(password))
                    Toast.makeText(ChangePassword.this, "password must be combination of letter, number, specail character.", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(ChangePassword.this, "Password changed successfully!", Toast.LENGTH_SHORT).show();
                    SharedPreferences preference = getSharedPreferences("myPreference", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preference.edit();
                    editor.putString("password", password);
                    editor.commit();

                    Intent intent = new Intent(ChangePassword.this,Login.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    ChangePassword.this.startActivity(intent);
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent CancelIntent = new Intent(ChangePassword.this,Login.class);
                ChangePassword.this.startActivity(CancelIntent);
            }
        });
    }
    public boolean isAlphaNumeric(String s){
        String pattern= "^([a-zA-Z+]+[&@!#+]+[0-9+]+)$";
        return s.matches(pattern);
    }
}
