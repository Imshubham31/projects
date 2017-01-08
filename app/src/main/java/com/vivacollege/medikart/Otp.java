package com.vivacollege.medikart;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gauravbhoyar on 08/01/17.
 */

public class Otp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);
        final EditText etOTP =(EditText) findViewById(R.id.otp);
        Button bVerifyLink =(Button) findViewById(R.id.verify);
        Button resendOtp = (Button) findViewById(R.id.resend);
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(Otp.this);
                        Notification notification = builder.setContentTitle("VK-MEDIKART")
                                .setContentText("Your otp number is : "+randomNumber)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setSound(alarmSound)
                                .setContentIntent(contentIntent).build();
                        NotificationManager notificationManager = (NotificationManager) Otp.this.getSystemService(Context.NOTIFICATION_SERVICE);
                        notificationManager.notify(0, notification);
                    }
                }, 3000);
            }
        });

        bVerifyLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otpNumber = etOTP.getText().toString();

                SharedPreferences preference = getSharedPreferences("myPreference", MODE_PRIVATE);
                String storedNumber = preference.getString("randomNumber", "0000");
                if(otpNumber.equals(storedNumber)) {
                    Intent VerifyIntent = new Intent(Otp.this, ChangePassword.class);
                    startActivity(VerifyIntent);
                }else
                    Toast.makeText(Otp.this, "Enter valid OTP number.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
