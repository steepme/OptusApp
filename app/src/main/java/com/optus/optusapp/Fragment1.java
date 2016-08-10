package com.optus.optusapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by Anto Stephen on 8/3/2016.
 */
public class Fragment1 extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_layout1, container, false);
        rootView.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), getString(R.string.fragment1), Toast.LENGTH_SHORT).show();
    }
}
