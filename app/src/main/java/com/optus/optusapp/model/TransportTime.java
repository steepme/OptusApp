package com.optus.optusapp.model;

import android.util.Log;

/**
 * Created by Anto Stephen on 8/6/2016.
 */
public class TransportTime {

    public static final String TAG = "TransportTime";
    public static final String TRANSPORTTYPECAR = "car";
    public static final String TRANSPORTTYPETRAIN = "train";


    String transportTrainTime;
    String transportCarTime;

    public TransportTime(String transportTrainTime, String transportCarTime) {
        this.transportCarTime = transportCarTime;
        this.transportTrainTime = transportTrainTime;
        Log.d(TAG,"<<TransportTime Car = "+transportCarTime + "Train = "+transportTrainTime);
    }

    public TransportTime() {
    }

    public String getTransportTrainTime() {
        return transportTrainTime;
    }

    public void setTransportTrainTime(String transportTrainTime) {
        Log.d(TAG,"<<TransportTime Car = "+transportTrainTime);
        this.transportTrainTime = transportTrainTime;
    }

    public String getTransportCarTime() {
        return transportCarTime;
    }

    public void setTransportCarTime(String transportCarTime) {
        Log.d(TAG,"<<TransportTime Train = "+transportTrainTime);
        this.transportCarTime = transportCarTime;
    }
}

