package com.sergeyyaniuk.androidworld.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergeyyaniuk.androidworld.R;
import com.sergeyyaniuk.androidworld.data.model.Event;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Display a grid of {@link Event}s. User can click on event item.
 */
public class EventsFragment extends Fragment {

    public interface EventsFragmentListener{
        void onEventClick(String eventId);
    }

    @BindView(R.id.events_recycler)
    RecyclerView eventsRecView;

    private Unbinder unbinder;
    EventsFragmentListener listener;
    EventListAdapter adapter;
    MainViewModel viewModel;

    public EventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        unbinder = ButterKnife.bind(this, view);

        setRecyclerView();

        setViewModel();

        return view;
    }

    private void setRecyclerView(){
        eventsRecView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        adapter = new EventListAdapter(eventClickListener);
        eventsRecView.setAdapter(adapter);
    }

    //fragment listen of LiveData changes
    private void setViewModel(){
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        LiveData<List<Event>> events = viewModel.getEventsLiveData();
        events.observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                if (events != null){
                    adapter.updateEvents(events);
                }
            }
        });
    }

    //interface for listening on event item click
    EventListAdapter.EventClickLictener eventClickListener = new EventListAdapter.EventClickLictener() {
        @Override
        public void onEventClick(String eventId) {
            listener.onEventClick(eventId);
        }
    };

    public void setEventClickListener(EventsFragmentListener eventClickListener){
        listener = eventClickListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (EventsFragmentListener) context;
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
