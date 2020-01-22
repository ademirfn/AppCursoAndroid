package com.example.adiligencia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DrawerLayout drawer;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

       Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
       setSupportActionBar(myToolbar);
       getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
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

        //adiciona rota
        mMap.addPolyline(new PolylineOptions()
                .add(sydney, esteio)
                .width(10)
                .color(Color.GREEN));



    }
}
