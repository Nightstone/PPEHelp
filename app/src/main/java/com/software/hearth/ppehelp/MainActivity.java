package com.software.hearth.ppehelp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements DateDialogFragment.DateInterface, TimeDialogFragment.TimeInterface{

    Button wifiCheckButton;

    Button constraintActivityButton;

    EditText loginEditText;
    EditText passwordEditText;
    Button connectButton;
    Button clearSavedDataButton;

    Button datePickerButton;
    Button timePickerButton;
    TextView datePickerTextView;
    TextView timePickerTextView;

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

        datePickerButton = findViewById(R.id.datePickerButton);
        timePickerButton = findViewById(R.id.timePickerButton);
        datePickerTextView = findViewById(R.id.datePickerTextView);
        timePickerTextView = findViewById(R.id.timePickerTextView);

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


    public void datePickerClicked(View v) {
        DateDialogFragment dialogFragment = new DateDialogFragment();
        dialogFragment.setDateInterface(this);
        dialogFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void timePickerClicked(View timePickerButton) {
        TimeDialogFragment dialogFragment = new TimeDialogFragment();
        dialogFragment.setTimeInterface(this);
        dialogFragment.show(getSupportFragmentManager(), "timePicker");
    }

    //Voir le AndroidManifest pour les permissions
    private void isWifiCheck(){
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager.isWifiEnabled()) {
            Toast toast =  Toast.makeText(this,"Le Wifi est activé", Toast.LENGTH_LONG);
            toast.show();
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
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginPassword",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Login",loginEditText.getText().toString());
            editor.putString("Password", passwordEditText.getText().toString());
            editor.commit();

            checkIfLoginPasswordIsSaved();
        }
    }

    private void clearDataInPhone(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("LoginPassword",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Log.d("JLE", sharedPreferences.getString("Login",""));
        Log.d("JLE", sharedPreferences.getString("Password",""));

        editor.putString("Login","");
        editor.putString("Password", "");
        editor.commit();

        checkIfLoginPasswordIsSaved();
    }

    private void checkIfLoginPasswordIsSaved() {
        if (getApplicationContext().getSharedPreferences("LoginPassword", Context.MODE_PRIVATE)
                .getString("Login", "") != "") {
            loginEditText.setVisibility(View.GONE);
            passwordEditText.setVisibility(View.GONE);
            clearSavedDataButton.setVisibility(View.VISIBLE);
        } else {
            loginEditText.setVisibility(View.VISIBLE);
            passwordEditText.setVisibility(View.VISIBLE);
            clearSavedDataButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void getDateFromDateFragment(int year, int month, int day) {
        datePickerTextView.setText(String.valueOf(day) + "/" + String.valueOf(month) + "/" + String.valueOf(year));
        datePickerTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void getTimeFromTimeFragment(int hour, int minute) {
        timePickerTextView.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
        timePickerTextView.setVisibility(View.VISIBLE);
    }
}
