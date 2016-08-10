package com.optus.optusapp.model;

/**
 * Created by Anto Stephen on 8/7/2016.
 */
public class LocationDetails {

    public static final String LATITUDE="latitude";
    public static final String LONGITUDE="longitude";

    Double locationGeographyAxis1;
    Double locationGeographyAxis2;


    public LocationDetails(Double locationGeographyAxis1,Double locationGeographyAxis2)
    {
        this.locationGeographyAxis1=locationGeographyAxis1;
        this.locationGeographyAxis2=locationGeographyAxis2;
    }

    public LocationDetails(){

    }

    public Double getLocationGeographyAxis1() {
        return locationGeographyAxis1;
    }

    public Double getLocationGeographyAxis2() {
        return locationGeographyAxis2;
    }

    public void setLocationGeographyAxis1(Double locationGeographyAxis1) {
        this.locationGeographyAxis1 = locationGeographyAxis1;
    }
}
