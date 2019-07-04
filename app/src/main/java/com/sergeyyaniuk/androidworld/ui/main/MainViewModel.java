package com.sergeyyaniuk.androidworld.ui.main;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sergeyyaniuk.androidworld.data.Repository;
import com.sergeyyaniuk.androidworld.data.model.ApiResponse;
import com.sergeyyaniuk.androidworld.data.model.Event;
import com.sergeyyaniuk.androidworld.data.model.Shop;
import com.sergeyyaniuk.androidworld.data.remote.ResponseCallback;

import java.util.List;

/**
 * Exposes the data to be used in the Events and Shops fragments.
 * <p>
 * Invoke repository to get data from server using Retrofit async method
 */
class MainViewModel extends ViewModel {

    private MutableLiveData<List<Event>> eventsLiveData;
    private MutableLiveData<List<Shop>> shopsLiveData;

    LiveData<List<Event>> getEventsLiveData(){
        if (eventsLiveData == null){
            eventsLiveData = new MutableLiveData<>();
            loadData();
        }
        return eventsLiveData;
    }

    LiveData<List<Shop>> getShopLiveData(){
        if (shopsLiveData == null){
            shopsLiveData = new MutableLiveData<>();
            loadData();
        }
        return shopsLiveData;
    }

    private void loadData(){
        Repository repository = Repository.build();
        repository.loadData(new ResponseCallback() {
            @Override
            public void onResponse(ApiResponse response) {
                eventsLiveData.postValue(response.getEvents());
                shopsLiveData.postValue(response.getShops());
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
