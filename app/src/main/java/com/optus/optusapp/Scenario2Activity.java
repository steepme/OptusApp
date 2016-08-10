package com.optus.optusapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.optus.optusapp.Presenter.DestinationCallback;
import com.optus.optusapp.Presenter.TransportPresenter;
import com.optus.optusapp.model.DestinationDetails;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Anto Stephen on on 8/3/2016.
 */
public class Scenario2Activity extends AppCompatActivity implements DestinationCallback {

    private Button mNavigateBtn;
    private Spinner mLocationSpinner;
    private ArrayList<DestinationDetails> mDestinationDetails;
    private int mSelectedIndex = 0;
    private TextView mCar;
    private TextView mTrain;
    private ProgressDialog mProgressLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenario2_layout);

        mNavigateBtn = (Button)findViewById(R.id.navigatebtn);
        mLocationSpinner = (Spinner)findViewById(R.id.spinner);
        mCar = (TextView)findViewById(R.id.mode_car);
        mTrain = (TextView)findViewById(R.id.mode_train);
        mProgressLoader = ProgressDialog.show(Scenario2Activity.this, "Getting Locations",
                "Please wait", true);
        mProgressLoader.setIndeterminate(true);

        mDestinationDetails = new ArrayList<DestinationDetails>();
        TransportPresenter destinations =new TransportPresenter();
        destinations.getLocationData(Scenario2Activity.this);

        mNavigateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent scenario1Intent = new Intent(Scenario2Activity.this, MapActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("latitude", mDestinationDetails.get(mSelectedIndex).getLocationDetails().getLocationGeographyAxis1());
                bundle.putDouble("longitude", mDestinationDetails.get(mSelectedIndex).getLocationDetails().getLocationGeographyAxis2());
                bundle.putString("name", mDestinationDetails.get(mSelectedIndex).getName());
                scenario1Intent.putExtras(bundle);
                startActivity(scenario1Intent);
            }
        });

        mLocationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String carTime;
                String trainTime;

                mSelectedIndex  = position;
                carTime = mDestinationDetails.get(mSelectedIndex).getTransportTypeTime().getTransportCarTime();
                trainTime = mDestinationDetails.get(mSelectedIndex).getTransportTypeTime().getTransportTrainTime();
                Log.i("","<<Item Selected = "+position);

                if(carTime != null){
                    mCar.setText("Car: "+carTime);
                } else{
                    mCar.setText("Car: Not Available");
                }

                if(trainTime != null){
                    mTrain.setText("Train: "+trainTime);
                } else{
                    mTrain.setText("Train: Not Available");
                }

                mNavigateBtn.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onDestinationListResponse(ArrayList<DestinationDetails> destinationDetails) {
        mDestinationDetails.clear();
        mDestinationDetails.addAll(destinationDetails);

        String[] destinations = null;

        destinations = new String[destinationDetails.size()];
        for(int i=0; i<destinationDetails.size(); i++){

            destinations[i] = destinationDetails.get(i).getName();
            Log.i("","<<Item "+1+destinationDetails.get(i).getName()+"\n");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, destinations);
        mLocationSpinner.setAdapter(adapter);
        mProgressLoader.dismiss();
    }
}
