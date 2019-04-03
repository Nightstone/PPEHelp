package com.software.hearth.ppehelp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.software.hearth.ppehelp.databinding.ActivityMapsBinding;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.googleMap = googleMap;
        LatLng sydney = new LatLng(48, 2);

        this.googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Paris"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void viewNormalButtonClicked(View view) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }

    public void viewSatelliteButtonClicked(View view) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
    }

    public void viewHybridButtonClicked(View view) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }
}
