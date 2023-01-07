package com.project.retrofit.Controller;

import com.project.retrofit.model.Zone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZoneService {

    @GET("/zones/all")
    public Call<List<Zone>> getAllZones();

    @GET("/zone/{ville_id}")
    public Call<List<Zone>> getZonesByVille(@Path("ville_id") int ville_id);
}
