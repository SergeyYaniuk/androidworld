package com.sergeyyaniuk.androidworld.ui.eventDetail;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.sergeyyaniuk.androidworld.R;
import com.sergeyyaniuk.androidworld.ui.base.BaseActivity;
import com.sergeyyaniuk.androidworld.util.Constants;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailActivity extends BaseActivity implements EventDetailFragment.EventDetFragListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);
        ButterKnife.bind(this);

        setupToolbar();

        showEventFragment();
    }

    private void setupToolbar() {
        toolbar.setTitle(R.string.event);
        toolbar.setTitleTextAppearance(this, R.style.ToolbarTextAppearance);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    private void showEventFragment(){
        String eventId = getIntent().getStringExtra(Constants.EVENT_ID);
        EventDetailFragment detailFragment = new EventDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString(Constants.EVENT_ID, eventId);
        detailFragment.setArguments(arguments);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, detailFragment);
        transaction.commit();
    }

    @Override
    public void onShowMap(String latitude, String longitude) {
        String uri = String.format(Locale.ENGLISH, "geo:%s,%s", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
