package com.example.anujdawar.bus_service_1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import static com.example.anujdawar.bus_service_1.SplashScreen.db;

public class main_activity extends AppCompatActivity
{
    EditText sourceTextBox, destinationTextBox;
    Button submitButton;
    String resultBusNumber = "0";
    String[] routeArray;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        database = FirebaseDatabase.getInstance();

        init();
    }

    private void init()
    {
        sourceTextBox = (EditText) findViewById(R.id.sourceEditText);
        destinationTextBox = (EditText) findViewById(R.id.destinationEditText);
        submitButton = (Button) findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                findBusNumber(sourceTextBox.getText().toString(), destinationTextBox.getText().toString());

                Intent ListOfBusesIntent = new Intent(main_activity.this, ListOfBusesActivity.class);
                startActivity(ListOfBusesIntent);
            }
        });
    }

    public void findBusNumber(String source, String destination)
    {
        Cursor cursor = db.viewAllData();

        int sourceIndex = -1, destinationIndex = -1;

        while(cursor.moveToNext())
        {
            String StringFromDatabase = cursor.getString(1);

            routeArray = StringFromDatabase.split(";");

            for(int i = 0; i < routeArray.length; i++)
            {
                if(routeArray[i].equals(source))
                    sourceIndex = i;

                if(routeArray[i].equals(destination))
                    destinationIndex = i;
            }

            if (sourceIndex < destinationIndex && sourceIndex != -1 && destinationIndex != -1)
            {
                resultBusNumber = cursor.getString(0);
                Toast.makeText(main_activity.this, "Bus Number : " + resultBusNumber, Toast.LENGTH_SHORT).show();

                myRef = database.getReferenceFromUrl("https://busservice1-f184f.firebaseio.com/bus_details/" + resultBusNumber + "/");
                myRef.addValueEventListener(new ValueEventListener()
                {
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        String data_time = (String) dataSnapshot.child("date_time").getValue().toString();
                        Toast.makeText(main_activity.this, data_time , Toast.LENGTH_SHORT).show();
                        String lat = (String) dataSnapshot.child("lat").getValue().toString();
                        Toast.makeText(main_activity.this, lat , Toast.LENGTH_SHORT).show();
                        String lon = (String) dataSnapshot.child("long").getValue().toString();
                        Toast.makeText(main_activity.this, lon , Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
            sourceIndex = destinationIndex = -1;
        }
        cursor.close();
    }
}

// there may be more than one bus in the resultBusNumber.. so on the next intent, make a list of resultant buses
// with their original source and destinations and the last-stop and next-stop maybe
// when user selects a bus, then pop the intent to show its location (real-time) on google maps :D