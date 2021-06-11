package com.example.foundtain;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.foundtain.databinding.ActivityMapsBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        InputStream fileGet = getResources().openRawResource(R.raw.fontaines);
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(fileGet));
            String line;
            while ((line = br.readLine()) !=null) {
                String[] token = line.split(";");
                double lat = Double.parseDouble(token[3]);
                double lon = Double.parseDouble(token[4]);
                LatLng Lat = new LatLng(lat, lon);
                googleMap.addMarker(new MarkerOptions().position(Lat).title(token[2]));
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lat));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Lat, 11F));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}