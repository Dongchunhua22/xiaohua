package com.example.day11_26;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mVp;
    private TabLayout mTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();
        initTab();
    }

    private void initTab() {
        mTab.setupWithViewPager(mVp);
    }

    private void initFragment() {
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(fragment1);
        fragments.add(fragment2);

        ArrayList<String> tables = new ArrayList<>();
        tables.add("fragment1");
        tables.add("fragment2");
        Vpadapter vpadapter = new Vpadapter(getSupportFragmentManager(), fragments, tables);
        mVp.setAdapter(vpadapter);

    }

    private void initView() {
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
    }
}
