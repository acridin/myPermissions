package com.example.java.mypermissions.tabview;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.java.mypermissions.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingkai6
 * DrawerLayout + AppBarLayout + Toolbar + TabLayout + ViewPager + NavigationView
 */
public class CardViewActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTableLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_card_view);
        init ();
    }

    private void init() {
        android.support.v7.widget.Toolbar toolbar = findViewById (R.id.toobar);
        setSupportActionBar (toolbar);
        final ActionBar ab = getSupportActionBar ();
        ab.setHomeAsUpIndicator (R.drawable.ic_launcher_background);
        ab.setDisplayHomeAsUpEnabled (true);

        mViewPager = findViewById (R.id.viewpager);
        mTableLayout = (TabLayout) findViewById (R.id.tabs);

        mDrawerLayout = (DrawerLayout) findViewById (R.id.dl_main_drawer);

        NavigationView navigationView = (NavigationView) findViewById (R.id.nv_main_navigation);

        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener (item -> {
                item.setChecked (true);
                String title = item.getTitle ().toString ();
                Toast.makeText (CardViewActivity.this, title, Toast.LENGTH_SHORT).show ();
                mDrawerLayout.closeDrawers ();
                return true;
            });
        }
        initViewPager ();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId ()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer (GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected (item);
    }

    private void initViewPager() {
        //设置mode为滚动模式 该模式可以动态改变tabLayout宽度，在宽度超过屏幕宽度以后可以横向滚动
        mTableLayout.setTabMode (TabLayout.MODE_SCROLLABLE);
        List<String> titles = new ArrayList<> ();
        titles.add ("精选");
        titles.add ("体育");
        titles.add ("新闻");
        titles.add ("购物");
        titles.add ("视频");
        titles.add ("娱乐");
        titles.add ("搞笑");

        for (int i = 0; i < titles.size (); i++) {
            mTableLayout.addTab (mTableLayout.newTab ().setText (titles.get (i)));
        }

        List<Fragment> fragments = new ArrayList<> ();
        for (int i = 0; i < titles.size (); i++) {
            fragments.add (new ListFragment ());
        }

        FragmentAdapter mFragmentAdapter = new FragmentAdapter (getSupportFragmentManager (), fragments, titles);

        mViewPager.setAdapter (mFragmentAdapter);
        mTableLayout.setupWithViewPager (mViewPager);
        mTableLayout.setTabsFromPagerAdapter (mFragmentAdapter);
    }

}
