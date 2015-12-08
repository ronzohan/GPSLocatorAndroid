package com.absolutezero.gpslocator.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.absolutezero.gpslocator.R;
import com.absolutezero.gpslocator.volley.CustomRequest;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;

import org.json.JSONObject;

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
    public void onMapReady(final GoogleMap googleMap) {
        /* Once map is ready then subscribe to the channel */
        /* Instantiate PubNub */
        Pubnub pubnub = new Pubnub(
                getString(R.string.pub_nub_publisher_key),
                getString(R.string.pub_nub_subscriber_key)
        );

        /* Subscribe to the demo_tutorial channel */
        try {
            pubnub.subscribe("Channel-n6vdjcnlr", new Callback() {
                public void successCallback(String channel, Object message) {
                    /* Make a get request since there is new location from database */
                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    String url = "http://mapapi-pbb2.rhcloud.com/api/location";
                    CustomRequest jsObjRequest = new CustomRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    Marker marker = MapGPSHelper.addLocation(
                                            googleMap, new LatLng(32, 12), new LatLng(12, 32), "In here"
                                    );
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                    });

                    requestQueue.add(jsObjRequest);
                }


                public void errorCallback(String channel, PubnubError error) {
                    System.out.println(error.getErrorString());
                }
            });
        } catch (PubnubException e) {
            e.printStackTrace();
        }
    }


}
