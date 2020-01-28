package com.example.adiligencia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.Task;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    private DrawerLayout drawer;
    private GoogleMap mMap;

    //declara variaveis para permissão location
    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int INITIAL_REQUEST = 1337;
    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 3;
    private FusedLocationProviderClient mFusedLocationProviderClient;

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

        // Construct a FusedLocationProviderClient.
       mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-29.7549941, -51.150283);
        LatLng esteio = new LatLng(-29.8524632, -51.1845758);

        mMap.addMarker(new MarkerOptions().position(esteio).title("51981384415").icon(
                BitmapDescriptorFactory.fromResource(R.drawable.pin)));


        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker em São Leopoldo").icon(
                BitmapDescriptorFactory.fromResource(R.drawable.pin)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        //setar o zoom do mapa
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));
        //mostra controles de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //verifica se é permitido ao aplicativo pegar a localização atual do dispositivo
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);

            // Task locationResult = mFusedLocationProviderClient.getLastLocation();
        } else {
            //caso ainda não tenha sido dada a permissão, solicitar a permissão
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
            }
        }

        //adiciona rota
        mMap.addPolyline(new PolylineOptions()
                .add(sydney, esteio)
                .width(10)
                .color(Color.GREEN));

        mMap.addCircle(
                new CircleOptions()
                        .center(esteio)
                        .radius(1580.0)
                        .strokeWidth(3f)
                        .strokeColor(Color.RED)
                        .fillColor(Color.argb(70, 150, 50, 50))

        );


    }

    //@Override
    public void onNavigationDrawerItemSelected(int position) {
        // depending on the position in your drawer list change this
        switch (position) {
            case 0: {
                Intent intent = new Intent(this, MapsActivity.class);
                startActivity(intent);
                break;
            }
            case 1: {
                Intent intent = new Intent(this, home.class);
                startActivity(intent);
                break;
            }
            case 2: {

                break;
            }
            default:
                break;

        }
    }
}
