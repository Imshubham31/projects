package com.vivacollege.medikart;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gauravbhoyar on 08/01/17.
 */

public class Login extends AppCompatActivity {
    private Boolean exit = false;
    EditText userName,password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        TextView forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        SpannableString content = new SpannableString("Forgot Password?");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        forgotPassword.setText(content);
        userName =(EditText) findViewById(R.id.username);
        password =(EditText) findViewById(R.id.password);
        final Button login =(Button) findViewById(R.id.login);
        final Button signUp =(Button) findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preference = getSharedPreferences("myPreference", MODE_PRIVATE);
                String storedUsername = preference.getString("userName","");
                String storedPassword = preference.getString("password","");
                String enteredUsername = userName.getText().toString();
                String enteredPassword = password.getText().toString();
                if(enteredUsername.equals("") || enteredPassword.equals(""))
                    Toast.makeText(Login.this, "Enter mandatory details!", Toast.LENGTH_SHORT).show();
                else if(!enteredUsername.equals(storedUsername) || !enteredPassword.equals(storedPassword))
                    Toast.makeText(Login.this, "Either username or password is incorrect!", Toast.LENGTH_SHORT).show();
                else{
                    Toast.makeText(Login.this, "You've successfully logged in!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent RegisterIntent = new Intent(Login.this,Register.class);
                startActivity(RegisterIntent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Login.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.getWindow().setLayout(800, 800);
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.get_user_number);
                dialog.show();
                final EditText mobileNumber = (EditText) dialog.findViewById(R.id.mobile_number);
                Button getOTP = (Button) dialog.findViewById(R.id.getOTP);
                getOTP.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        String mobileNumberText = mobileNumber.getText().toString();
                        if(mobileNumberText.equals("") || mobileNumberText.length() <10)
                            Toast.makeText(Login.this, "Enter valid 10 digit mobile number.", Toast.LENGTH_SHORT).show();
                        else{


                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    String randomNumber = ""+((int)(Math.random()*9000)+1000);

                                    SharedPreferences preference = getSharedPreferences("myPreference", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = preference.edit();
                                    editor.putString("randomNumber", randomNumber);
                                    editor.commit();
                                    Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                    PendingIntent contentIntent = PendingIntent.getActivity(
                                            getApplicationContext(),
                                            0,
                                            new Intent(), // add this
                                            PendingIntent.FLAG_UPDATE_CURRENT);
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(Login.this);
                                    Notification notification = builder.setContentTitle("VK-MEDIKART")
                                            .setContentText("Your otp number is : "+randomNumber)
                                            .setSmallIcon(R.mipmap.ic_launcher)
                                            .setSound(alarmSound)
                                            .setContentIntent(contentIntent).build();
                                    NotificationManager notificationManager = (NotificationManager) Login.this.getSystemService(Context.NOTIFICATION_SERVICE);
                                    notificationManager.notify(0, notification);
                                }
                            }, 3000);
                            Intent ForgetPasswordIntent = new Intent(Login.this,Otp.class);
                            startActivity(ForgetPasswordIntent);


                        }
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(exit){
            finish();;
        }else{
            Toast.makeText(this, "Press Back again to exit.", Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            },3*1000);

        }
    }
}
