package com.sergeyyaniuk.androidworld.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sergeyyaniuk.androidworld.R;
import com.sergeyyaniuk.androidworld.data.model.Event;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.ViewHolder>{

    public interface EventClickLictener{
        void onEventClick(String eventId);
    }

    private EventClickLictener clickLictener;
    private List<Event> events = new ArrayList<>();

    public EventListAdapter(EventClickLictener clickLictener) {
        this.clickLictener = clickLictener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from((viewGroup.getContext()))
                .inflate(R.layout.list_item_events, viewGroup, false);
        return new EventListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(events.get(i).getTitle());
        viewHolder.shortDesc.setText(events.get(i).getShortDescription());
        Glide.with(viewHolder.itemView.getContext()).load(events.get(i)
                .getSmallImage()).apply(RequestOptions.circleCropTransform()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void updateEvents(List<Event> events){
        this.events = events;
        notifyDataSetChanged();
    }

    public Event getItem(int position) {
        return events.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.small_image)
        ImageView image;

        @BindView(R.id.title_tv)
        TextView title;

        @BindView(R.id.short_desc_tv)
        TextView shortDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onItemClicked(View v){
            String eventId = getItem(getAdapterPosition()).getId();
            clickLictener.onEventClick(eventId);
        }
    }
}
