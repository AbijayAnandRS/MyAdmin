package com.example.myadmin.adapters;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

  private final View[] views;
  private final String[] headers;

  public SectionsPagerAdapter(FragmentManager fm, View[] views, String[] headers) {
    super(fm);
    this.views = views;
    this.headers = headers;
  }

  @Override
  public Fragment getItem(int position) {

    return new AdpView(views[position]);
  }

  @Override
  public int getCount() {
    return headers.length;
  }


  @Override
  public CharSequence getPageTitle(int position) {
    return headers[position];
  }

  @SuppressLint("ValidFragment")
  public static class AdpView extends Fragment {

    private final View view;

    public AdpView(View view) {
      this.view = view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

      return view;
    }

  }

}


