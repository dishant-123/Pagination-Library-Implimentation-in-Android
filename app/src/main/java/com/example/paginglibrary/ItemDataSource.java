package com.example.paginglibrary;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    public static final int PAGE_SIZE = 10;

    private static final int FIRST_PAGE = 1;

    private static final String SITE_NAME = "stackoverflow";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                        Log.i("Main loadInitial",response.message());
                        if (response.body() != null) {
                            callback.onResult(response.body().items, null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.i("Failure",t.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        Log.i("Main loadBefore",response.message());
                        if (response != null) {
                            callback.onResult(response.body().items, adjacentKey);

                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.i("Failure",t.getMessage());
                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<StackApiResponse>() {
                    @Override
                    public void onResponse(Call<StackApiResponse> call, Response<StackApiResponse> response) {
                        Log.i("Main loadAfter",response.message());
                        if (response.body() != null) {
                            Integer key = response.body().has_more ? params.key + 1 : null;
                            callback.onResult(response.body().items, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<StackApiResponse> call, Throwable t) {
                        Log.i("Failure",t.getMessage());
                    }
                });
    }
}
