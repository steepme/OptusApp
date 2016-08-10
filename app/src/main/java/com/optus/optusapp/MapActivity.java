package com.optus.optusapp;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Anto Stephen on on 8/5/2016.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private double mLat;
    private double mLong;
    private String mDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);
        setContentView(R.layout.map_layout);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null) {
            mLat = bundle.getDouble("latitude");
            mLong = bundle.getDouble("longitude");
            mDestination = bundle.getString("name");
            Log.d("MapActivity","<<Item Lat="+mLat+"Long="+mLong);
        }

    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng location = new LatLng(mLat, mLong);

        map.addMarker(new MarkerOptions()
                .title(mDestination)
                .position(location));
        moveToCurrentLocation(map,location);
    }

    private void moveToCurrentLocation(GoogleMap googleMap, LatLng currentLocation)
    {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation,15));
        googleMap.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}

