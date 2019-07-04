package com.sergeyyaniuk.androidworld.data.remote;

import com.sergeyyaniuk.androidworld.data.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiEndPoints {

    @GET("tt/TT.json")
    Call<ApiResponse> getApiResponse();
}
