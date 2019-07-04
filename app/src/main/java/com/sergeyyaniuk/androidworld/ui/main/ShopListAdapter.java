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
import com.sergeyyaniuk.androidworld.data.model.Shop;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ViewHolder>{

    private List<Shop> shops = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_shops, viewGroup, false);
        return new ShopListAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.title.setText(shops.get(i).getTitle());
        viewHolder.shortDesc.setText(shops.get(i).getShortDescription());
        Glide.with(viewHolder.itemView.getContext()).load(shops.get(i)
                .getSmallImage()).apply(RequestOptions.circleCropTransform()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return shops.size();
    }

    public void updateShops(List<Shop> shops){
        this.shops = shops;
        notifyDataSetChanged();
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
    }
}
