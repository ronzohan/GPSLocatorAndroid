package com.absolutezero.gpslocator.models;

import java.util.Date;

public class UserLocation {
    private double latitude;
    private double longitude;
    private Date timeUploaded;

    public UserLocation(double latitude, double longitude, Date timeUploaded) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.timeUploaded = timeUploaded;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public Date getTimeUploaded() {
        return timeUploaded;
    }
}
