package com.example.mapsactivity;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.mapsactivity.databinding.ActivityMapsBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    LocationManager locationManager;
    double lat, longi, lat2, longi2;
    double distance = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        displayDistance();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        UiSettings settings = googleMap.getUiSettings();
        settings.setZoomControlsEnabled(true);
        settings.setCompassEnabled(true);
        settings.setIndoorLevelPickerEnabled(true);
        settings.setMapToolbarEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }
        mMap.setMyLocationEnabled(true);


        PlaceSelectionListener();
        ;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

//        mMap.setOnMapClickListener(this);
//        mMap.setOnMapLongClickListener(this);
//        mMap.setOnCameraIdleListener(this);
//    }
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {


            @Override
            public void onMapClick(LatLng point) {
                TextView mTapTextView = findViewById(R.id.mTapTextView);
                mTapTextView.setText("tapped, point=" + point);
                lat2=lat;
                longi2=longi;
                lat = point.latitude;
                longi = point.longitude;
                double di=0;
                if (lat2 != 0) {
                    di = distanceBetweenTwoPoint(lat2, longi2,lat, longi);
                }
                TextView mdistance = findViewById(R.id.mDistanceView);
                distance = distance + di;
                mdistance.setText(String.valueOf(distance));
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {


            @Override
            public void onMapLongClick(LatLng point) {
                TextView mTapTextView = findViewById(R.id.mTapTextView);
                mTapTextView.setText("long pressed, point=" + point);
                lat2=lat;
                longi2=longi;
                lat = point.latitude;
                longi = point.longitude;
                double di=0;
                if (lat2 != 0) {
                    di = distanceBetweenTwoPoint(lat2, longi2,lat, longi);
                }
                TextView mdistance = findViewById(R.id.mDistanceView);
                distance = distance + di;
                mdistance.setText(String.valueOf(distance));
            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {


            @Override
            public void onCameraIdle() {
                TextView mCameraTextView = findViewById(R.id.mCameraTextView);
                mCameraTextView.setText(mMap.getCameraPosition().toString());
            }

        });

        PlaceSelectionListener();

    }

    public void onMapClick(LatLng point) {
        TextView mTapTextView = findViewById(R.id.mTapTextView);
        mTapTextView.setText("tapped, point=" + point);

    }

    public void onMapLongClick(LatLng point) {
        TextView mTapTextView = findViewById(R.id.mTapTextView);
        mTapTextView.setText("long pressed, point=" + point);

    }

    public void onCameraIdle() {
        TextView mCameraTextView = findViewById(R.id.mCameraTextView);
        mCameraTextView.setText(mMap.getCameraPosition().toString());

    }


    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        return false;
    }

    void PlaceSelectionListener() {
        Places.initialize(getApplicationContext(), "AIzaSyDO4YD35y5i5vptSpUyOXLJnFpacNBnaO4");
        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.afrag);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                // TODO: Get info about the selected place.
                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
            }

            @Override
            public void onError(@NonNull Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }
    private void OnGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new  DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void displayDistance() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }

//        LocationManager nManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
//            OnGPS();
//        } else {
//        }
//
//        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

//        if (locationGPS != null) {
//            lat2=lat;
//            longi2=longi;
//            lat = locationGPS.getLatitude();
//            longi = locationGPS.getLongitude();
//        }
//        double di = distanceBetweenTwoPoint(lat2, longi2,lat, longi);
//
//        final Handler handler = new Handler();
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//
//                if(di != 0){
//                    distance = distance + di;
//                }
//                TextView mdistance = findViewById(R.id.mDistanceView);
//                mdistance.setText(String.valueOf(distance));
//                handler.postDelayed(this, 1000);
//            }
//        });
//

    }

    double distanceBetweenTwoPoint(double la2, double ln2, double la, double ln) {
        double earthRadius = 3958.75;
        double dLat = Math.toRadians(la - la2);
        double dLng = Math.toRadians(ln - ln2);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(la2))
                * Math.cos(Math.toRadians(la)) * Math.sin(dLng / 2)
                * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;

        double meterConversion = 1609;

        return (dist * meterConversion);
    }

}