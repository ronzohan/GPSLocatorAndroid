package com.absolutezero.gpslocator.map;

import com.google.android.gms.maps.model.LatLng;

public class MapLocation {
    private LatLng mLatLng;
    private String dateTime;

    public MapLocation(double latitude, double longitude, String dateTime) {
        this.mLatLng = new LatLng(latitude, longitude);
        this.dateTime = dateTime;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
