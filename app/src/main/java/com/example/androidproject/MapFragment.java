package com.example.androidproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment {
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //initialise view
View view = inflater.inflate(R.layout.fragment_map, container,false);

//initialise map
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.google_map);

        //async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //when map is loaded
                mMap = googleMap;

                // Add a marker in Sydney and move the camera
                LatLng conestogaGamingHub = new LatLng(43.48032152787736, -80.51852910599011);
                mMap.addMarker(new MarkerOptions().position(conestogaGamingHub).title("Marker in Conestoga Gaming Hub"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(conestogaGamingHub));
            }
        });
//return view
        return view;
    }
}