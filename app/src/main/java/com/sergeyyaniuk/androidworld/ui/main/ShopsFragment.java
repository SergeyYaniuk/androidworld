package com.sergeyyaniuk.androidworld.ui.main;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sergeyyaniuk.androidworld.R;
import com.sergeyyaniuk.androidworld.data.model.Shop;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Display a grid of {@link Shop}s.
 */
public class ShopsFragment extends Fragment {

    @BindView(R.id.shops_recycler)
    RecyclerView shopsRecView;

    private Unbinder unbinder;
    ShopListAdapter adapter;
    MainViewModel viewModel;

    public ShopsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shops, container, false);
        unbinder = ButterKnife.bind(this, view);

        setRecyclerView();

        setViewModel();

        return view;
    }

    private void setRecyclerView(){
        shopsRecView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        adapter = new ShopListAdapter();
        shopsRecView.setAdapter(adapter);
    }

    //fragment listen of LiveData changes
    private void setViewModel(){
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        LiveData<List<Shop>> shops = viewModel.getShopLiveData();
        shops.observe(this, new Observer<List<Shop>>() {
            @Override
            public void onChanged(@Nullable List<Shop> shops) {
                if (shops != null){
                    adapter.updateShops(shops);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
