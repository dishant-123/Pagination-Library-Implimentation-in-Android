package com.example.paginglibrary;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class ItemDataSourceFactory extends  DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer,Item> create() {
        Log.i("Main ItemDataFactory","create() called");
        ItemDataSource itemDataSource = new ItemDataSource();

        itemLiveDataSource.postValue(itemDataSource);
        return itemDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource(){
        Log.i("Main ItemDataFactory","getItemLiveDataSource() called");
        return itemLiveDataSource;
    }

}
