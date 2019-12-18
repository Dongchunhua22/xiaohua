package com.example.day11_26;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class Vpadapter extends FragmentPagerAdapter {
   ArrayList<Fragment>fragments;
   ArrayList<String>tables;

    public Vpadapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String> tables) {
        super(fm);
        this.fragments = fragments;
        this.tables = tables;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  tables.get(position);
    }
}
