package com.project.retrofit.ui.home;

import android.widget.ListView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.project.retrofit.Controller.RestaurantService;
import com.project.retrofit.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {
    RestaurantService restaurantService;
    List<Restaurant> listRestaurant=new ArrayList<>();
    ListView listView;
    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");


    }

    public LiveData<String> getText() {
        return mText;
    }
}