package com.optus.optusapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Anto Stephen on on 8/3/2016.
 */
public class Scenario1Activity extends AppCompatActivity implements View.OnClickListener{

    private static final String SELECTED_ITEM = "selectedItem";
    private static final String SELECTED_COLOR = "selectedColor";

    private String mSelectedItem;
    private int mSelectedColor;
    private Button redBtn;
    private Button blueBtn;
    private Button greenBtn;
    private TextView scrollItem1;
    private TextView scrollItem2;
    private TextView scrollItem3;
    private TextView scrollItem4;
    private TextView scrollItem5;
    private TextView txtDesc;
    private RelativeLayout colorPanel;
    private ViewPager mViewPager;
    private FragmentListAdapter mFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scenario1_layout);

        redBtn = (Button) findViewById(R.id.redBtn);
        blueBtn = (Button) findViewById(R.id.blueBtn);
        greenBtn = (Button) findViewById(R.id.greenBtn);
        scrollItem1 = (TextView) findViewById(R.id.item1);
        scrollItem2 = (TextView) findViewById(R.id.item2);
        scrollItem3 = (TextView) findViewById(R.id.item3);
        scrollItem4 = (TextView) findViewById(R.id.item4);
        scrollItem5 = (TextView) findViewById(R.id.item5);
        txtDesc = (TextView) findViewById(R.id.txt_desc);
        colorPanel = (RelativeLayout) findViewById(R.id.color_panel);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mFragmentAdapter = new FragmentListAdapter(getSupportFragmentManager());

        redBtn.setOnClickListener(this);
        blueBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        scrollItem1.setOnClickListener(this);
        scrollItem2.setOnClickListener(this);
        scrollItem3.setOnClickListener(this);
        scrollItem4.setOnClickListener(this);
        scrollItem5.setOnClickListener(this);


        mViewPager.setAdapter(mFragmentAdapter);

        if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getString(SELECTED_ITEM);
            mSelectedColor = savedInstanceState.getInt(SELECTED_COLOR);

            if(mSelectedItem != null){
                txtDesc.setText(mSelectedItem);
            }

            if(mSelectedColor != 0 ){
                setBackgroundColor(mSelectedColor);
            }

        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putString(SELECTED_ITEM, mSelectedItem);
        savedInstanceState.putInt(SELECTED_COLOR, mSelectedColor);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setBackgroundColor(int color){
        colorPanel.setBackgroundColor(getColor(color));
        mSelectedColor = color;
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.redBtn:
                setBackgroundColor(R.color.red);
                break;

            case R.id.blueBtn:
                setBackgroundColor(R.color.blue);
                break;

            case R.id.greenBtn:
                setBackgroundColor(R.color.green);
                break;

            case R.id.item1:
                mSelectedItem = scrollItem1.getText().toString();
                txtDesc.setText(mSelectedItem);
                break;

            case R.id.item2:
                mSelectedItem = scrollItem2.getText().toString();
                txtDesc.setText(mSelectedItem);
                break;

            case R.id.item3:
                mSelectedItem = scrollItem3.getText().toString();
                txtDesc.setText(mSelectedItem);
                break;

            case R.id.item4:
                mSelectedItem = scrollItem4.getText().toString();
                txtDesc.setText(mSelectedItem);
                break;

            case R.id.item5:
                mSelectedItem = scrollItem5.getText().toString();
                txtDesc.setText(mSelectedItem);
                break;

            default:
                colorPanel.setBackgroundColor(getColor(R.color.white));
                break;
        }
    }
}
