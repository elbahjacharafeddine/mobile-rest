package com.project.retrofit.Controller;

import com.project.retrofit.model.Ville;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VilleService {

    @GET("/villes/all")
    public Call<List<Ville>> getAllUsers();

    @POST("/user/add")
    public Call<Map> addNewUser(@Body Ville user);
}
