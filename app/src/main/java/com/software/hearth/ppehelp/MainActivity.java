package com.software.hearth.ppehelp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button wifiCheckButton;

    Button constraintActivityButton;

    EditText loginEditText;
    EditText passwordEditText;
    Button connectButton;
    Button clearSavedDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitiateLayouts();
        InitiateBehaviors();
    }

    private void InitiateLayouts(){
        wifiCheckButton = findViewById(R.id.wifiCheckButton);

        constraintActivityButton = findViewById(R.id.constraintActivityButton);

        loginEditText = findViewById(R.id.loginEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        connectButton = findViewById(R.id.connectButton);
        clearSavedDataButton = findViewById(R.id.clearSavedDataButton);

        checkIfLoginPasswordIsSaved();
    }

    private void InitiateBehaviors(){
        wifiCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isWifiCheck();
            }
        });

        constraintActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToConstraintActivity();
            }
        });

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDataInPhone();
            }
        });

        clearSavedDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearDataInPhone();
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

    private void navigateToConstraintActivity(){
        Intent constraintActivityIntent = new Intent(this, ConstraintActivity.class);
        startActivity(constraintActivityIntent);
    }

    private void saveDataInPhone(){
        if (loginEditText.getText().length() != 0 && passwordEditText.getText().length() != 0 ) {
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginPassword", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Login",loginEditText.getText().toString());
            editor.putString("Password", passwordEditText.getText().toString());
            editor.commit();

            checkIfLoginPasswordIsSaved();
        }
    }

    private void clearDataInPhone(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginPassword", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Log.d("JLE", sharedPreferences.getString("Login",""));
        Log.d("JLE", sharedPreferences.getString("Password",""));

        editor.putString("Login","");
        editor.putString("Password", "");
        editor.commit();

        checkIfLoginPasswordIsSaved();
    }

    private void checkIfLoginPasswordIsSaved() {
        if (getApplicationContext().getSharedPreferences("LoginPassword", Context.MODE_PRIVATE).getString("Login", "") != "") {
            loginEditText.setVisibility(View.GONE);
            passwordEditText.setVisibility(View.GONE);
            clearSavedDataButton.setVisibility(View.VISIBLE);
        } else {
            loginEditText.setVisibility(View.VISIBLE);
            passwordEditText.setVisibility(View.VISIBLE);
            clearSavedDataButton.setVisibility(View.GONE);
        }
    }
}
