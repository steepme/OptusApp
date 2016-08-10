package com.optus.optusapp.Parser;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.optus.optusapp.Presenter.ServerCallback;
import com.optus.optusapp.model.DestinationDetails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Anto Stephen on 8/7/2016.
 */

public class HttpCommunicator extends Application {

    private static final String  JSON_URL="http://express-it.optusnet.com.au/sample.json";
    public final static int GETRequest = 1;
    public final static int POSTRequest = 2;
    int requestmethod;
    HashMap<String, String> params=null;

    public void getDataFromURL(final ServerCallback serverCommunicationCallback){

        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response,serverCommunicationCallback);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        // RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json,ServerCallback serverCommunicationCallback){
        System.out.println("response json>>"+json);
        JsonParser jp = new JsonParser();
        serverCommunicationCallback.onServerResponse(jp.parseJsonResponse(json));
    }

    public ArrayList<DestinationDetails> getDataFromHttpURL(final ServerCallback serverCommunicationCallback){
        URL url;
        String response = "";


        try {
            url = new URL(JSON_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15001);
            conn.setConnectTimeout(15001);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            if (requestmethod == POSTRequest) {
                conn.setRequestMethod("POST");
                Log.i("HttpComm","post req");
            } else if (requestmethod == GETRequest) {
                conn.setRequestMethod("GETRequest");
                Log.i("httpComm","get req");
            }

            Log.i("HttpComm","params non null");
            OutputStream ostream = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(ostream, "UTF-8"));
            StringBuilder requestresult = new StringBuilder();

            if (params != null) {


                boolean first = true;
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if (first) {
                        first = false;
                        Log.i("HttpComm", "first true");
                    } else {
                        requestresult.append("&");
                        Log.i("HttpComm", "not first appeneded and");
                    }
                    requestresult.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                    requestresult.append("=");
                    requestresult.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
                }
            }
            writer.write(requestresult.toString());

            writer.flush();
            writer.close();
            ostream.close();

            int reqresponseCode = conn.getResponseCode();

            Log.i("HttpComm","reqresponseCode>>"+reqresponseCode);

            if (reqresponseCode == HttpsURLConnection.HTTP_OK) {
                Log.i("HttpComm","HTTP_OK");
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                    Log.i("HttpComm","response in loop>>"+response);
                }
            } else {
                Log.i("HttpComm","HTTP_NOT_OK");
                response = "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("HttpComm","response final>>"+response);

        JsonParser jp = new JsonParser();
        return (jp.parseJsonResponse(response));

    }
}

