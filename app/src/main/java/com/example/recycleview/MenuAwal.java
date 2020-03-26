package com.example.recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import static android.media.CamcorderProfile.get;

public class MenuAwal extends AppCompatActivity {
    public static final String TAG_ID = "id";
    public static final String TAG_USERNAME = "username";
    private SharedPreferences mPreferences;
    SharedPreferences sharedpreferences;
    private String sharedPrefFile = "com.example.recycleview";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_awal2);
        sharedpreferences = getSharedPreferences(LoginActivity.my_shared_preferences, Context.MODE_PRIVATE);
        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        ImageView showMobil2 = (ImageView) findViewById(R.id.imageView5);
        ImageView showMobil3 = (ImageView) findViewById(R.id.imageView7);
        ImageView showMobil4 = (ImageView) findViewById(R.id.imageView8);
        ImageView showMobil1 = (ImageView) findViewById(R.id.imageView4);
        ImageView showMobil5 = (ImageView) findViewById(R.id.imageView10);
        showMobil1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAwal.this, DasboardActivity.class);
                startActivity(intent);
            }

    });
        showMobil2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAwal.this, DasboardActivity.class);
                startActivity(intent);
            }

        });
        showMobil3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAwal.this, DasboardActivity.class);
                startActivity(intent);
            }

        });
        showMobil4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuAwal.this, DasboardActivity.class);
                startActivity(intent);
            }

        });
        showMobil5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(TAG_ID, null);
                editor.putString(TAG_USERNAME, null);
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.clear().apply();
                Toast.makeText(MenuAwal.this, "Anda Berhasil LogOut", Toast.LENGTH_LONG).show();
                editor.commit();

                Intent intent = new Intent(MenuAwal.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }
}

