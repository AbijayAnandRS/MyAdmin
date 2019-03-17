package com.example.myadmin.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myadmin.activities.admin.AdminActivity;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final View[] views;
    public static View rootView;
    private final String[] headers;

    public SectionsPagerAdapter(FragmentManager fm, View[] views, String[] headers) {
        super(fm);
        this.views = views;
        this.headers = headers;
    }

    @Override
    public Fragment getItem(int position) {

        return new AdpView();
    }

    @Override
    public int getCount() {
        return headers.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return headers[position];
    }

    public static class AdpView extends Fragment {


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

            return AdminActivity.Companion.getRootView();
        }

    }

}


