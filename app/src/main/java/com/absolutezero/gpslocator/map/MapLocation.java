package com.absolutezero.gpslocator.map;

import android.util.Log;

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

    private static final String TAG = "MapLocation";

    public MapLocation(double latitude, double longitude, String dateTime) {
        this.mLatLng = new LatLng(latitude, longitude);

        /* Try to format the given date */
        try {
            this.dateTime = formatter.parseDateTime(dateTime);
        }
        catch (IllegalArgumentException e) {
            Log.e(TAG, "MapLocation: Error parsing date");
        }

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
