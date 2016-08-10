package com.optus.optusapp.Parser;

import android.util.Log;

import com.optus.optusapp.model.DestinationDetails;
import com.optus.optusapp.model.LocationDetails;
import com.optus.optusapp.model.TransportTime;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Anto Stephen on 8/7/2016.
 */

public class JsonParser {

    public ArrayList<DestinationDetails> parseJsonResponse(String result) {

        Log.i("JSOnParser","inside jsonparser");
        int id;
        String name;
        Double locationGeographyAxis1 = 0.0;
        Double locationGeographyAxis2 = 0.0;
        String transportCarTime = null;
        String transportTrainTime = null;
        ArrayList<DestinationDetails> dd = null;
        DestinationDetails objDd = null;

        try {

            JSONArray array = new JSONArray(result);

            Log.i("JSOnParser","result>>"+result);
            Log.i("JSOnParser","array>>"+array.toString());

            if (array != null) {
                Log.i("JSOnParser","array non null");
                Log.i("JSOnParser","array length>>"+array.length());
                dd=new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    transportCarTime = null;
                    transportTrainTime = null;
                    JSONObject obj = array.getJSONObject(i);
                    Log.i("JSOnParser","array obj >>"+(i+1)+obj.toString());
                    id = obj.getInt(DestinationDetails.ID);
                    name = obj.getString(DestinationDetails.NAME);

                    JSONObject objFromcentral = obj.getJSONObject(DestinationDetails.FROMCENTRAL);
                    if (objFromcentral != null) {

                        transportCarTime = objFromcentral.getString(TransportTime.TRANSPORTTYPECAR);
                        try {
                            transportTrainTime = objFromcentral.getString(TransportTime.TRANSPORTTYPETRAIN);
                        }catch(Exception e)
                        {
                            Log.e("jsonparser",e.toString());
                            Log.i("jsonparser","train info not present for object no>>"+(i+1));
                        }
                        Log.i("JSOnParser","array ele>>"+"car>>"+transportCarTime+"and"+"train"+transportTrainTime);
                    }

                    JSONObject objlocation = obj.getJSONObject(DestinationDetails.LOCATION);
                    if (objlocation != null) {

                        locationGeographyAxis1 = objlocation.getDouble(LocationDetails.LATITUDE);
                        locationGeographyAxis2 = objlocation.getDouble(LocationDetails.LONGITUDE);

                        Log.i("JSOnParser","array ele>>"+"lat>>"+locationGeographyAxis1+"and"+"long"+locationGeographyAxis2);

                    }
                    objDd = new DestinationDetails(id, name, new TransportTime(transportTrainTime, transportCarTime), new LocationDetails(locationGeographyAxis1, locationGeographyAxis2));

                    dd.add(objDd);

                }
            }
        } catch (Exception e) {
            Log.e("jsonparser","Exception>>"+e.toString());
        }
        return dd;
    }
}