package com.example.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity {

    Button button;
    //  Button smsButton;
    int REQUEST_CODE = 1;
    FusedLocationProviderClient fusedLocationProviderClient;
    
    LocationCallback locationCallback = new LocationCallback(){
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Log.e("on", "on location result");
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
        getLocation();

           Button   smsButton = findViewById(R.id.smsButton);
        smsButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, DrawDotsActivity.class);
            startActivity(intent);
            /**if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.SEND_SMS,
                }, REQUEST_CODE);
            }
            SmsManager.getDefault().sendTextMessage("+2347013128342", null, "Gideon, software engineer", null, null );
        **/
             });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

 @Override
    protected void onPause() {
        super.onPause();
        stopLocationUpdates();
    }

    void getLocation(){
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if(checkLocationPermission() != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, REQUEST_CODE);
        }

        if(!checkLocationProvider()){
            Toast.makeText(this, "open settings to enable Location", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(intent);
        }
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(100);

        Log.e("req", "request location");
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    int checkLocationPermission(){
        return  ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
    }

    boolean checkLocationProvider(){
         LocationManager locationManager =  (LocationManager) getSystemService(Context.LOCATION_SERVICE);
         return  locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER );
     }

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

}

class DrawCircle extends View{
    public DrawCircle(Context context) {
        super(context);
    }
    Paint paint = new Paint();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(50, 50, 20, paint);
    }
}