package com.sergeyyaniuk.androidworld.data.remote;

import com.sergeyyaniuk.androidworld.data.model.ApiResponse;

public interface ResponseCallback {

    void onResponse(ApiResponse response);

    void onError(Throwable t);
}
