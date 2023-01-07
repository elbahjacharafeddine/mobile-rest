package com.project.retrofit.Controller;

import com.project.retrofit.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestaurantService {

    @GET("lister")
    Call<List<Restaurant>> getRestaurants();

    @POST("agreger")
    Call<Restaurant>addRestaurant(@Body Restaurant persona);

    @POST("actualiser/{id}")
    Call<Restaurant>updateRestaurant(@Body Restaurant restaurant,@Path("id") int id);

    @POST("eliminer/{id}")
    Call<Restaurant>deleteRestaurant(@Path("id")int id);

}
