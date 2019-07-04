package com.sergeyyaniuk.androidworld.ui.main;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.sergeyyaniuk.androidworld.R;
import com.sergeyyaniuk.androidworld.ui.base.BaseActivity;
import com.sergeyyaniuk.androidworld.ui.eventDetail.EventDetailActivity;
import com.sergeyyaniuk.androidworld.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements EventsFragment.EventsFragmentListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.pager)
    ViewPager viewPager;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupToolbar();

        setupViewPager();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof EventsFragment) {
            EventsFragment eventsFragment = (EventsFragment)fragment;
            eventsFragment.setEventClickListener(this);
        }
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.android_world);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance);
        setSupportActionBar(toolbar);
    }

    private void setupViewPager(){
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onEventClick(String eventId) {
        startActivity(new Intent(this, EventDetailActivity.class)
                .putExtra(Constants.EVENT_ID, eventId));
    }

    private class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new EventsFragment();
                case 1:
                    return new ShopsFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getResources().getText(R.string.events);
                case 1:
                    return getResources().getText(R.string.shops);
            }
            return null;
        }
    }
}
