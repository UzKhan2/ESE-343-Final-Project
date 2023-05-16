package com.example.fitpeak;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class AttendanceActivity extends AppCompatActivity {

    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private LocationRequest locationRequest;
    private Location mCurrentLocation;
    private double longitude, latitude;
    private long ts;
    private boolean requestingLocationUpdates = true;

    String gymAddress;
    int attendanceCount;
    LocalDate localDate, lastDateOfAttendance;

    TextView days, todaysAttendance;
    protected void createLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(AttendanceActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        Geocoder geocoder;

        geocoder = new Geocoder(this , Locale.getDefault());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        todaysAttendance = findViewById(R.id.aT);
        days = findViewById(R.id.dC);

        fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        createLocationRequest();
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {

                        }
                    }
                });

        gymAddress = getGymAddress();
        attendanceCount = getGymAttendance();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            localDate = LocalDate.now() ;
        }
        lastDateOfAttendance = getLastAttendanceDate();

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    mCurrentLocation = location;
                    latitude = mCurrentLocation.getLatitude();
                    longitude = mCurrentLocation.getLongitude();

                    List<Address> addresses;
                    String address = null;

                    try {
                        addresses= geocoder.getFromLocation(location.getLatitude(), location.getLongitude(),1);
                        address = addresses.get(0).getAddressLine(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if(address.compareTo(gymAddress) == 0){
                        if(lastDateOfAttendance != localDate){
                            todaysAttendance.setText("You went to the gym today. Good job!");
                            attendanceCount++;
                            days.setText(attendanceCount);
                            saveCount();
                            saveDate();
                        }
                    }
                    else{
                        if(lastDateOfAttendance != localDate){
                            todaysAttendance.setText("You Have Not Gone to the Gym Today");
                            days.setText(attendanceCount);
                        }
                    }
                }
            };
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (requestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    private void startLocationUpdates() {
// Create a location request.........
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.requestLocationUpdates(locationRequest,
                locationCallback,
                Looper.getMainLooper());
    }

    public String getGymAddress(){
        //return the address saved to the database
        return "";
    }

    public int getGymAttendance(){
        //return the attendance record saved to the database
        return 0;
    }

    public void saveDate(){
        //save localDate to the database as the last date of attendance
    }

    public void saveCount(){
        //store the attendance count back in the database
    }

    public LocalDate getLastAttendanceDate(){
        //return the date the gym was last attended
        return localDate; //change this to the retrieved date
    }

    public void returnToMain(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}