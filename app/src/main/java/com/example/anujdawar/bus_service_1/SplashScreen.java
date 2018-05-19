package com.example.anujdawar.bus_service_1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreen extends AppCompatActivity {

    static Database_All_States db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = new Database_All_States(this);

        Thread completeSplashWorkThread = new Thread(new Runnable() {
            @Override
            public void run() {
                fillDatabase_All_States();
            }
        });

        try {
            completeSplashWorkThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        completeSplashWorkThread.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent mainActivity = new Intent(SplashScreen.this, main_activity.class);
                startActivity(mainActivity);
                finish();
            }
        }, 0);
    }

    private void fillDatabase_All_States()
    {
        Cursor res = db.viewAllData();

        if(res.getCount() == 2)
            return;

        db.insertData("PB03CM1702", "delhi;chandigarh;patiala;punjab;chitkara;zirakpur;ludhiana;jammu;kashmir;kerela");
        db.insertData("bus number 2", "chandigarh;patiala;national;highway;lol;delhi;kerela;thats;all");
    }
}