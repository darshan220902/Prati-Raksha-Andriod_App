package com.darkness.sparkwomen;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class LocationActivity extends AppCompatActivity
 {
     SmsManager manager = SmsManager.getDefault();
     String myLocation;
     FusedLocationProviderClient fusedLocationClient;
     RecyclerView recyclerView;
     HashMap<String,String> contacts;
     ArrayList<String> send;
     LocationAdapter adapter;
     MyOnClickListener onClickListener;
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(LocationActivity.this,MainActivity.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);



        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Logic to handle location object
                            location.getAltitude();
                            location.getLongitude();
                            myLocation = "http://maps.google.com/maps?q=loc:"+location.getLatitude()+","+location.getLongitude();
                        }else {
                            myLocation = "Unable to Find Location :(";
                        }
                    }
                });



        contacts = new HashMap<>();
        send = new ArrayList<>();

        adapter = new LocationAdapter(this, send, new MyOnClickListener() {
            @Override
            public void onItemClicked(int position) {
                String tosend=send.get(position);
                manager.sendTextMessage(tosend,null,"I'm out!\nSending My Location :\n"+myLocation,null,null);
                Toast.makeText(getApplicationContext(), "Location Sent",
                        Toast.LENGTH_LONG).show();

            }
        });

        recyclerView = findViewById(R.id.loccontacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getData();

    }


        private void getData() {
        send.clear();
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        Set<String> oldNumbers = sharedPreferences.getStringSet("enumbers", new LinkedHashSet<>());
        send.addAll(oldNumbers);
        adapter.notifyDataSetChanged();
    }
}


