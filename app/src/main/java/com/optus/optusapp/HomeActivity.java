package com.optus.optusapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Anto Stephen on on 8/3/2016.
 */
public class HomeActivity extends AppCompatActivity {

    private Button scBtn1;
    private Button scBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        scBtn1 = (Button) findViewById(R.id.scenariobtn1);
        scBtn2 = (Button) findViewById(R.id.scenariobtn2);

        scBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scenario1Intent = new Intent(HomeActivity.this, Scenario1Activity.class);
                startActivity(scenario1Intent);
            }
        });

        scBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scenario2Intent = new Intent(HomeActivity.this, Scenario2Activity.class);
                startActivity(scenario2Intent);
            }
        });
    }
}
