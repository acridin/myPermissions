package com.example.java.mypermissions.tabview;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by dingkai .
 * DATA: 2019/1/30 .
 * Description :
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> mFragment;
    private List<String> mTitles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super (fm);
        this.mFragment = fragments;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragment.get (position);
    }

    @Override
    public int getCount() {
        return mFragment.size ();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle (position);
    }
}
