package com.optus.optusapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Anto Stephen on on 8/3/2016.
 */
public class FragmentListAdapter extends FragmentPagerAdapter {

    private static int PAGE_COUNT = 4;

    public FragmentListAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        switch (position) {

            case 0:
                return new Fragment1();

            case 1:
                return new Fragment2();

            case 2:
                return new Fragment3();

            case 3:
                return new Fragment4();
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
