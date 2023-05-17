package com.example.fitpeak;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;


import android.content.Intent;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import androidx.annotation.NonNull;

public interface gpslistener extends LocationListener, GpsStatus.Listener {

    public void onLocationChanged(Location location);
    public void onProviderDisabled(String provider);
    public void onProviderEnabled(String provider);
    public void onStatusChanged(String provider, int status, Bundle extras);

    public void onGpsStatusChanged(int event);


}
