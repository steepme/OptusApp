package com.optus.optusapp.Presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.optus.optusapp.Parser.HttpCommunicator;
import com.optus.optusapp.model.DestinationDetails;

import java.util.ArrayList;

/**
 * Created by Anto Stephen on 8/7/2016.
 */

public class TransportPresenter implements ServerCallback{

    DestinationCallback destinationListCallback=null;
    ArrayList<DestinationDetails> destinationDetails=null;

    public void getLocationData(final DestinationCallback destinationListCallback)
    {
        this.destinationListCallback=destinationListCallback;
        // hc.getDataFromURL(this);
        makeWebCall();
    }

    public void makeWebCall(){
        WebCall task = new WebCall();
        //passes values for the urls string array
        task.execute();
    }

    class WebCall extends AsyncTask
    {
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            Log.i("Sce2Presenter","onPreExecute");
        }

        @Override
        protected Object doInBackground(Object[] params) {
            Log.i("Sce2Presenter","doInBackground start");
            HttpCommunicator hc=new HttpCommunicator();
            destinationDetails=hc.getDataFromHttpURL(TransportPresenter.this);
            Log.i("Sce2Presenter","doInBackground destinationDetails>>"+destinationDetails.toArray().toString());
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Log.i("Sce2Presenter","onPostExecute destinationDetails>>"+destinationDetails.toArray().toString());
            super.onPostExecute(o);
            destinationListCallback.onDestinationListResponse(destinationDetails);
        }
    }

    @Override
    public void onServerResponse(ArrayList<DestinationDetails> destinationDetails) {
        Log.i("shd not get called>>","onServerResponse");
        destinationListCallback.onDestinationListResponse(destinationDetails);
    }
}