package com.absolutezero.gpslocator.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class MapGPSFragment extends MapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getMapAsync(this);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /* Once the google map is ready, setup in here */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Marker marker = MapGPSHelper.addLocation(
                googleMap, new LatLng(32, 12), new LatLng(12, 32), "In here"
        );
    }
}
