package com.example.adiligencia;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-29.7549941, -51.150283);
        LatLng esteio = new LatLng(-29.8524632,  -51.1845758 );
        mMap.addMarker(new MarkerOptions().position(esteio).title("Marker em Esteio"));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker em São Leopoldo"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //setar o zoom do mapa
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
        //mostra controles de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
