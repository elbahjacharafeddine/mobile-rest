package com.project.retrofit.APISET;

import com.project.retrofit.model.Ville;
import com.project.retrofit.model.Zone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Apiset {
    @GET("/villes/all")
    Call<List<Ville>> getAllVilles();

    @GET("zones/all")
    Call<List<Zone>> getAllZones();

    @GET("villes/zones")
    Call<String> getVilleZone(Zone zone);


}
