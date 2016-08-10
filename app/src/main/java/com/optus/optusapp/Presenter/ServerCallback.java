package com.optus.optusapp.Presenter;

import com.optus.optusapp.model.DestinationDetails;

import java.util.ArrayList;

/**
 * Created by Anto Stephen on 8/7/2016.
 */
public interface ServerCallback {

    public void onServerResponse(ArrayList<DestinationDetails> destinationDetails);
}
