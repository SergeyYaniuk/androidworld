package com.sergeyyaniuk.androidworld.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sergeyyaniuk.androidworld.data.model.ApiResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitBuilder {

    private static final String BASE_URL = "http://smartbox.software/";
    private static Retrofit retrofit = null;

    public static Retrofit initialize(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(gsonConverterFactory())
                    .build();
        }
        return retrofit;
    }

    private static GsonConverterFactory gsonConverterFactory(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(ApiResponse.class, new ResponseDeserializer());
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
}
