package com.example.fitpeak;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.example.fitpeak.databinding.RunningMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Running extends FragmentActivity implements OnMapReadyCallback, gpslistener {

        private GoogleMap mMap;
        private RunningMapsBinding binding;
        TextView loc;
        Button bloc, stopbut;
        TextView dis;
        double lat=1, longi, lat2, longi2;
        LatLng point;
        double tdis = 0;
        int count = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            binding = RunningMapsBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());


            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            //loc = findViewById(R.id.locview);
            bloc = findViewById(R.id.startButton);

            bloc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if
                    (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission("android.permission.ACCESS_FINE_LOCATION")
                            != PackageManager.PERMISSION_GRANTED){
                        requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1000);
                    }else{
                        showLocation();
                    }

                }
            });

            stopbut = findViewById(R.id.stopButton);

            stopbut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tdis = 0;
                    count = 0;
                    stio();

                }
            });
        }

        private void showLocation(){
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
                //loc.setText("loading..");
                if
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission("android.permission.ACCESS_FINE_LOCATION")
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1000);
                }else{

                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3, 1, this);

            }
        }
        void stio(){
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(locationManager.isProviderEnabled(locationManager.GPS_PROVIDER)){
                locationManager.removeUpdates(this);

            }
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
            if
            (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission("android.permission.ACCESS_FINE_LOCATION")
                    != PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 1000);
            }else{
            }
            // Add a marker in Sydney and move the camera
            mMap.setMyLocationEnabled(true);
            UiSettings settings = googleMap.getUiSettings();

            settings.setZoomControlsEnabled(true);
            settings.setCompassEnabled(true);
            settings.setIndoorLevelPickerEnabled(true);
            settings.setMapToolbarEnabled(true);
        }


        @Override
        public void onLocationChanged(Location location) {
            //loc.setText("lat: " + location.getLatitude() + "lng: " + location.getLongitude());
            if (count == 0) {
                lat = location.getLatitude();
                longi = location.getLongitude();
                count = count + 1;
            }
            else{
                lat2= lat;
                longi2 = longi;
                lat = location.getLatitude();
                longi = location.getLongitude();
                double s = distanceBetweenTwoPoint(lat2, longi2, lat, longi);
                dis = findViewById(R.id.distanceText);
                tdis= tdis+s;
                dis.setText("current distance is: "+ tdis);
                LatLng d = new LatLng(lat, longi);
//            mMap.addMarker(new MarkerOptions().position(d).title("Marker in Sydney"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(d));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(d, 14));
                Polyline l = mMap.addPolyline(new PolylineOptions().add(new LatLng(lat, longi) , new LatLng(lat2, longi2)));
            }

        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onGpsStatusChanged(int event) {

        }

        double distanceBetweenTwoPoint(double la2, double ln2, double la, double ln) {
            double radius = 3958.75;
            double dLat = Math.toRadians(la - la2);
            double dLng = Math.toRadians(ln - ln2);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(la2))
                    * Math.cos(Math.toRadians(la)) * Math.sin(dLng / 2)
                    * Math.sin(dLng / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            double dist = radius * c;

            double met = 1609;

            return (dist * met);
        }
    }