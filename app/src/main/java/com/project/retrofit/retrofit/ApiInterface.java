package com.project.retrofit.retrofit;

import com.project.retrofit.model.Ville;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("/user/all")
    Call<List<Ville>> getAllVilles();


}
