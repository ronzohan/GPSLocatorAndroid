package com.absolutezero.gpslocator.volley;

import org.json.JSONObject;

import android.content.Context;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class VolleyHelper {

    private final Context mContext;
    private final RequestQueue mRequestQueue;
    private final String mBaseUrl;

    public VolleyHelper(Context c, String baseUrl) {
        mContext = c;
        mRequestQueue = Volley.newRequestQueue(mContext);
        mBaseUrl = baseUrl;
    }

    private String constructUrl(String method) {
        return mBaseUrl + "/" + method;
    }

    public void get(String method, JSONObject jsonRequest,
                    Listener<JSONObject> listener, ErrorListener errorListener) {

        JsonObjectRequest objRequest = new JsonObjectRequest(
                Method.GET, constructUrl(method), jsonRequest, listener, errorListener
        );

        mRequestQueue.add(objRequest);
    }

    public void put(String method, JSONObject jsonRequest,
                    Listener<JSONObject> listener, ErrorListener errorListener) {

        JsonObjectRequest objRequest = new JsonObjectRequest(
                Method.PUT, constructUrl(method), jsonRequest, listener, errorListener
        );

        mRequestQueue.add(objRequest);
    }

    public void post(String method, JSONObject jsonRequest,
                     Listener<JSONObject> listener, ErrorListener errorListener) {

        JsonObjectRequest objRequest = new JsonObjectRequest(
                Method.POST, constructUrl(method), jsonRequest, listener, errorListener
        );

        mRequestQueue.add(objRequest);
    }

    public void delete(String method, JSONObject jsonRequest,
                       Listener<JSONObject> listener, ErrorListener errorListener) {

        JsonObjectRequest objRequest = new JsonObjectRequest(
                Method.DELETE, constructUrl(method), jsonRequest, listener, errorListener
        );

        mRequestQueue.add(objRequest);
    }
}