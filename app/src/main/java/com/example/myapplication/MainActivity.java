package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    TextView textView2;
    int REQUEST_CODE = 1;
    FusedLocationProviderClient fusedLocationProviderClient;
    
    LocationCallback locationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if (locationResult == null) {
                Toast.makeText(MainActivity.this, "location result is null", Toast.LENGTH_LONG).show();
            }
            assert locationResult != null;
            Location location = locationResult.getLastLocation();
            Toast.makeText(MainActivity.this, "LAT " + location.getLatitude() + " " + "LONG " + location.getLongitude(), Toast.LENGTH_LONG).show();


        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        TextView textView = findViewById(R.id.textView);
        TextView textView2 = findViewById(R.id.textView2);

        button.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);

        });
         fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if(checkLocationPermission() != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, REQUEST_CODE);
        }

        if(!checkLocationProvider()){
            Toast.makeText(this, "open settings to enable Location", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(100);


        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());

    }

    int checkLocationPermission(){
        return  ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
    }
     boolean checkLocationProvider(){
         LocationManager locationManager =  (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         return  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER );
     }
    @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
}