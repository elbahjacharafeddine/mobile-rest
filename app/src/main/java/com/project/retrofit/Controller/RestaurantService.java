package com.project.retrofit.Controller;

import com.project.retrofit.model.Restaurant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RestaurantService {

    @GET("listar")
    Call<List<Restaurant>> getRestaurants();

    @POST("agregar")
    Call<Restaurant>addRestaurant(@Body Restaurant restaurant);

    @POST("actualizar/{id}")
    Call<Restaurant>updateRestaurant(@Body Restaurant restaurant, @Path("id") int id);

    @POST("eliminar/{id}")
    Call<Restaurant>deleteRestaurant(@Path("id")int id);

}
