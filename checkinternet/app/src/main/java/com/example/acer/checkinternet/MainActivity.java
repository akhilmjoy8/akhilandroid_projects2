package com.example.acer.checkinternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import static com.example.acer.checkinternet.NetworkStateChangeReceiver.IS_NETWORK_AVAILABLE;

public class MainActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                boolean isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false);
                String networkStatus = isNetworkAvailable ? "connected" : "disconnected";

                Toast.makeText(MainActivity.this, "Network Status::::"+networkStatus, Toast.LENGTH_LONG).show();
               // Snackbar.make(findViewById(R.id.activity_main), "Network Status: " + networkStatus, Snackbar.LENGTH_LONG).show();
            }
        }, intentFilter);
    }
}