package com.absolutezero.gpslocator.map;

import android.graphics.Color;

import com.absolutezero.gpslocator.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapGPSHelper {
    /**
     *
     * @param googleMap The google map reference that will be edited
     * @param mapLocations List of locations to be plotted on the map
     * @return The marker created on the endpoint on adding new line on the map
     */
    public static Marker addLocation(GoogleMap googleMap, ArrayList<MapLocation> mapLocations
    ) {
        int lineWidth = 5;
        MapLocation oldMapLocation = null;
        Marker marker = null;

        for (int i=0;i<mapLocations.size();i++) {
            if (oldMapLocation != null) {
                googleMap.addPolyline(
                    new PolylineOptions().add(
                            oldMapLocation.getLatLng(),
                            mapLocations.get(i).getLatLng()).width(lineWidth).color(Color.RED));
            }

            /* Set marker here once a line has been made */
            if (i == mapLocations.size() - 1)
                marker = googleMap.addMarker(
                    new MarkerOptions().position(mapLocations.get(i).getLatLng()).title(mapLocations.get(i).getDateTime()));
            else
                marker = googleMap.addMarker(
                        new MarkerOptions()
                                .position(mapLocations.get(i).getLatLng())
                                .title(mapLocations.get(i).getDateTime())
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mm_20_blue)));

            oldMapLocation = mapLocations.get(i);
        }
        return marker;
    }
}
