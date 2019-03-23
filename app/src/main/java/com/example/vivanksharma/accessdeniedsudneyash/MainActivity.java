package com.example.vivanksharma.accessdeniedsudneyash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    Button getlatlan;
    TextView tv,tv2;
    Button som;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getlatlan = findViewById(R.id.getl);
        tv = findViewById(R.id.textView);
        tv2 = findViewById(R.id.textView2);
        som = findViewById(R.id.button);
        final Intent i = new Intent(this , MapsActivity.class);
        getlatlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference lat = database.getReference("lattitude");
                DatabaseReference log = database.getReference("longitude");



                // Read from the database
                lat.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("lat", "Value is: " + value);
                        tv.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("lat", "Failed to read value.", error.toException());
                    }
                });

                log.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Log.d("lat", "Value is: " + value);
                        tv2.setText(value);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w("lat", "Failed to read value.", error.toException());
                    }
                });
            }
        });

        som.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                Double lattitude = Double.parseDouble(tv.getText().toString());
                Double longitude = Double.parseDouble(tv2.getText().toString());

                Bundle bundle = new Bundle();

                //Add your data to bundle
                bundle.putString("lattitude", lattitude.toString());
                bundle.putString("longitude",longitude.toString());
                //Add the bundle to the intent
                i.putExtras(bundle);


                //Fire that second activity
                startActivity(i);

            }
        });

    }
}
