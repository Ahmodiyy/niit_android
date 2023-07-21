package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button button;
    /**  Button smsButton;
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
     **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       button = findViewById(R.id.button);
        button.setOnClickListener((View v) -> {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
            //IntentFilter intentFilter = new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED);
            //registerReceiver(new MyReceiver(), intentFilter);
       });

        /**        smsButton = findViewById(R.id.smsButton);
        smsButton.setOnClickListener((view) -> {
            if(ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                        Manifest.permission.SEND_SMS,
                }, REQUEST_CODE);
            }
            SmsManager.getDefault().sendTextMessage("+2349012851760", null, "Gideon, software engineer", null, null );
        }); **/


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
/**
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

    private void stopLocationUpdates() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }
 **/
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