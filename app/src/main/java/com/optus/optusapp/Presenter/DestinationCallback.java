package com.optus.optusapp.Presenter;

import com.optus.optusapp.model.DestinationDetails;

import java.util.ArrayList;

/**
 * Created by Anto Stephen on 8/7/2016.
 */
public interface DestinationCallback {
    public void onDestinationListResponse(ArrayList<DestinationDetails> destinationDetails);
}
