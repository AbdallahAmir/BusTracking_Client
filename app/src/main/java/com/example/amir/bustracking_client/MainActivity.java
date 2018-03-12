package com.example.amir.bustracking_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    String value=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myref=database.getReference("location");
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue(String.class);
                TextView textView=(TextView)findViewById(R.id.loc);
                textView.setText(value);
                String [] spilt=value.split(",");
                String latdu=spilt[0].trim();
                String longtude=spilt[1].trim();
                double dlat=Double.parseDouble(latdu);
                double longtu=Double.parseDouble(longtude);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w( "Failed to read value.", databaseError.toException());


            }
        });
    }

    public void showMap(View view) {
        Intent intent=new Intent(MainActivity.this,MapsActivity.class);
        intent.putExtra("Loc",value);
        startActivity(intent);
    }
}
