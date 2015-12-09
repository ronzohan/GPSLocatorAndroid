package com.absolutezero.gpslocator.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.absolutezero.gpslocator.R;
import com.absolutezero.gpslocator.utils.ApiMethod;
import com.absolutezero.gpslocator.volley.VolleyHelper;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapGPSFragment extends MapFragment implements OnMapReadyCallback {
    private GoogleMap mGoogleMap;
    private Marker oldMarker = null;

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
    public void onMapReady(final GoogleMap googleMap) {
        /* Once map is ready, assign it to private variable mGoogleMap so others can access it */
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        /* Once map is ready then subscribe to the channel */
        /* Instantiate PubNub */
        Pubnub pubnub = new Pubnub(
                getString(R.string.pub_nub_publisher_key),
                getString(R.string.pub_nub_subscriber_key)
        );

        VolleyHelper volleyHelper = new VolleyHelper(getActivity());
        volleyHelper.get(ApiMethod.get_locations, null, getLocationResponseListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        /* Subscribe to the demo_tutorial channel */
        try {
            pubnub.subscribe("Channel-n6vdjcnlr", new Callback() {
                public void successCallback(String channel, Object message) {
                    /* Make a get request since there is new location from database */
                    VolleyHelper volleyHelper = new VolleyHelper(getActivity());
                    volleyHelper.get(ApiMethod.get_locations, null, getLocationResponseListener,
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {

                                }
                            });
                }
                public void errorCallback(String channel, PubnubError error) {
                    System.out.println(error.getErrorString());
                }
            });
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }

    private Response.Listener<JSONObject> getLocationResponseListener
            = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONArray locations = response.getJSONArray("locations");
                Double longitude, latitude;
                String dateTime;
                ArrayList<MapLocation> latLngs = new ArrayList<>();

                /* if old marker is set and a new location is found,
                change the icon of the old marker */
                if (oldMarker != null) {
                    oldMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.mm_20_blue));
                }

                for (int i=0; i<locations.length(); i++) {
                    longitude = Double.valueOf(locations.getJSONObject(i).getString("longitude"));
                    latitude = Double.valueOf(locations.getJSONObject(i).getString("latitude"));
                    dateTime = String.valueOf(locations.getJSONObject(i).getString("upload_time"));

                    latLngs.add(new MapLocation(latitude, longitude, dateTime));
                }

                oldMarker = MapGPSHelper.addLocation(mGoogleMap, latLngs);

                float cameraZoom = 17.0f;
                mGoogleMap.animateCamera(
                        CameraUpdateFactory.newLatLngZoom(latLngs.get(latLngs.size() - 1 ).getLatLng(),
                                cameraZoom)
                );
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


}
