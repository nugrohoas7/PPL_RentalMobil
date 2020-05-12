package com.rental_apps.android.rental_apps.admin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rental_apps.android.rental_apps.R;
import com.rental_apps.android.rental_apps.user.UserMain;

public class SplashAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_admin);
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashAdminActivity.this, AdminMain.class));
                    finish();
                }
            }
        };
        thread.start();
    }
}
