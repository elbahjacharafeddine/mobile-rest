package com.project.retrofit.retrofit;

import com.project.retrofit.model.Restaurant;
import com.project.retrofit.model.Ville;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/villes/all")
    Call<List<Ville>> getAllVilles();

    @GET("/restaurant/all")
    Call<List<Restaurant>> getAllRestaurants();

    @GET("/villes/all")
    public Call<List<Ville>> getAllUsers();

    @POST("/user/add")
    public Call<Map> addNewUser(@Body Ville user);

}
