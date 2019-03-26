package com.software.hearth.ppehelp;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button wifiCheckButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitiateLayouts();
        InitiateBehaviors();
    }

    private void InitiateLayouts(){
        wifiCheckButton = findViewById(R.id.wifiCheckButton);
    }

    private void InitiateBehaviors(){
        wifiCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWifiCheck();
            }
        });
    }

    //Voir le AndroidManifest pour les permissions
    private void isWifiCheck(){
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            Toast.makeText(this,"Le Wifi est activé", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this,"Le Wifi est désactivé", Toast.LENGTH_LONG).show();
        }
    }
}
