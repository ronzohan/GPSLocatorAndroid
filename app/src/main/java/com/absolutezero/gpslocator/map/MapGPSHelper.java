package com.absolutezero.gpslocator.map;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapGPSHelper {
    /**
     *
     * @param googleMap The google map reference that will be edited
     * @param oldLatLng The old latitude and longitude coordinates recorded
     * @param newLatLng The new latitude and longitude coordinates recorded
     * @param content Content to be displayed on the marker
     * @return The marker created on the endpoint on adding new line on the map
     */
    public static Marker addLocation(GoogleMap googleMap, LatLng oldLatLng, LatLng newLatLng, String content
    ) {
        int lineWidth = 5;
        if (oldLatLng != null) {
            googleMap.addPolyline(
                    new PolylineOptions().add(oldLatLng, newLatLng).width(lineWidth).color(Color.RED)
            );
        }

        return googleMap.addMarker(
                new MarkerOptions().position(newLatLng).title(content)
        );
    }
}
