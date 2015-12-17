package com.absolutezero.gpslocator.map;

import com.google.android.gms.maps.model.LatLng;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Model representation of map location object
 */
public class MapLocation {
    private LatLng mLatLng;
    private DateTime dateTime;
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Setup map location of the user
     * @param latitude Latitude of the location
     * @param longitude Longitude of the location
     * @param dateTime  Datetime which location was recorded
     */
    public MapLocation(double latitude, double longitude, String dateTime) {
        this.mLatLng = new LatLng(latitude, longitude);
        this.dateTime = formatter.parseDateTime(dateTime);
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getDateTimeString() {

        return this.dateTime.toString(formatter);
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }
}
