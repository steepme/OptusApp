package com.optus.optusapp.model;

/**
 * Created by Anto Stephen on 8/7/2016.
 */

public class DestinationDetails {

        public static final String ID="id";
        public static final String NAME="name";
        public static final String FROMCENTRAL="fromcentral";
        public static final String LOCATION="location";

        int id;
        String name;
        TransportTime transportTypeTime=new TransportTime();
        LocationDetails locationDetails=new LocationDetails();

        public DestinationDetails(int id,String name,TransportTime tt,LocationDetails ld)
        {
            this.id=id;
            this.name=name;
            this.transportTypeTime.transportCarTime=tt.transportCarTime;
            this.transportTypeTime.transportTrainTime=tt.transportTrainTime;
            this.locationDetails.locationGeographyAxis1=ld.locationGeographyAxis1;
            this.locationDetails.locationGeographyAxis2=ld.locationGeographyAxis2;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public TransportTime getTransportTypeTime() {
            return transportTypeTime;
        }

        public void setTransportTypeTime(TransportTime transportTypeTime) {
            this.transportTypeTime = transportTypeTime;
        }

        public LocationDetails getLocationDetails() {
            return locationDetails;
        }

        public void setLocationDetails(LocationDetails locationDetails) {
            this.locationDetails = locationDetails;
        }
}

