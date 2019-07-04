package com.sergeyyaniuk.androidworld.ui.eventDetail;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sergeyyaniuk.androidworld.R;
import com.sergeyyaniuk.androidworld.data.model.Event;
import com.sergeyyaniuk.androidworld.util.Constants;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Display a detail {@link Event} information.
 */
public class EventDetailFragment extends Fragment {

    @BindView(R.id.large_iv)
    ImageView largeImage;

    @BindView(R.id.title_tv)
    TextView title;

    @BindView(R.id.short_desc_tv)
    TextView shortDescr;

    @BindView(R.id.description_tv)
    TextView description;

    @BindView(R.id.show_map_btn)
    Button showMapButton;

    private Unbinder unbinder;
    DetailViewModel viewModel;
    String eventId;
    LiveData<Event> event;
    EventDetFragListener listener;

    public EventDetailFragment() {
        // Required empty public constructor
    }

    public interface EventDetFragListener{
        void onShowMap(String latitude, String longitude);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        if (arguments != null){
            eventId = arguments.getString(Constants.EVENT_ID);
            setViewModel();
        }
        return view;
    }

    private void setViewModel(){
        viewModel = ViewModelProviders.of(getActivity()).get(DetailViewModel.class);
        event = viewModel.getEvent(eventId);
        event.observe(this, new Observer<Event>() {
            @Override
            public void onChanged(@Nullable Event event) {
                title.setText(event.getTitle());
                shortDescr.setText(event.getShortDescription());
                description.setText(event.getDescription());
                Glide.with(getContext()).load(event.getBigImage()).into(largeImage);
            }
        });
    }

    //open map app to show place of event
    @OnClick(R.id.show_map_btn)
    void onClickMapButton (){
        String latitude = event.getValue().getLatitude();
        String longitude = event.getValue().getLongitude();
        listener.onShowMap(latitude, longitude);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (EventDetFragListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
