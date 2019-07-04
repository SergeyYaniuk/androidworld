package com.sergeyyaniuk.androidworld.data;

import com.sergeyyaniuk.androidworld.data.model.ApiResponse;
import com.sergeyyaniuk.androidworld.data.remote.ApiEndPoints;
import com.sergeyyaniuk.androidworld.data.remote.ResponseCallback;
import com.sergeyyaniuk.androidworld.data.remote.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Concrete implementation to load data from server.
 * <p>
 * For simplicity, was using enqueue method of Retrofit. In real application will be better to
 * use RxJava2, Coroutines or other async tools.
 *
 * //TODO: Implement this class using LiveData.
 */
public class Repository {

    private ApiEndPoints apiEndPoints;

    public Repository(ApiEndPoints apiEndPoints) {
        this.apiEndPoints = apiEndPoints;
    }

    public static Repository build(){
        Retrofit retrofit = RetrofitBuilder.initialize();
        return new Repository(retrofit.create(ApiEndPoints.class));
    }

    public void loadData(final ResponseCallback responseCallback){
        final Call<ApiResponse> apiResponse = apiEndPoints.getApiResponse();
        apiResponse.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()){
                    ApiResponse data = response.body();
                    responseCallback.onResponse(data);
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                responseCallback.onError(t);
            }
        });
    }
}
