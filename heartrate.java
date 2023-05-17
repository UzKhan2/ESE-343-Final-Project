package com.example.fitpeak;


import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.Wearable;

public class heartrate extends Activity implements MessageApi.MessageListener, GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient apiclient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        inith();

    }

    private void inith() {
        apiclient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks( this )
                .build();

        if( apiclient != null && !( apiclient.isConnected() || apiclient.isConnecting() ) )
            apiclient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if( apiclient != null && !( apiclient.isConnected() || apiclient.isConnecting() ) )
            apiclient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onMessageReceived( final MessageEvent mevent ) {
        runOnUiThread( new Runnable() {
            @Override
            public void run() {
                if( mevent.getPath().equalsIgnoreCase( "/message" ) ) {
                    TextView hvi = findViewById(R.id.hrate);
                    String s = new String(mevent.getData());
                    hvi.setText(s);
                }
            }
        });
    }

    @Override
    public void onConnected(Bundle bundle) {
        Wearable.MessageApi.addListener( apiclient, this );
    }

    @Override
    protected void onStop() {
        if ( apiclient != null ) {
            Wearable.MessageApi.removeListener( apiclient, this );
            if ( apiclient.isConnected() ) {
                apiclient.disconnect();
            }
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if( apiclient != null )
            apiclient.unregisterConnectionCallbacks( this );
        super.onDestroy();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}