package com.sergeyyaniuk.androidworld.ui.eventDetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.sergeyyaniuk.androidworld.data.Repository;
import com.sergeyyaniuk.androidworld.data.model.ApiResponse;
import com.sergeyyaniuk.androidworld.data.model.Event;
import com.sergeyyaniuk.androidworld.data.remote.ResponseCallback;

/**
 * Exposes the data to be used in the EventDetail fragments.
 * <p>
 * Invoke repository to get data from server using Retrofit async method
 */

public class DetailViewModel extends ViewModel {

    private MutableLiveData<Event> eventLiveData;

    LiveData<Event> getEvent(String eventId){
        if (eventLiveData == null){
            eventLiveData = new MutableLiveData<>();
            loadData(eventId);
        }
        return eventLiveData;
    }

    private void loadData(String eventId){
        Repository repository = Repository.build();
        repository.loadData(new ResponseCallback() {
            @Override
            public void onResponse(ApiResponse response) {
                for(Event event:response.getEvents()){
                    if (event.getId().equals(eventId)){
                        eventLiveData.postValue(event);
                    }
                }
            }

            @Override
            public void onError(Throwable t) {

            }
        });
    }
}
