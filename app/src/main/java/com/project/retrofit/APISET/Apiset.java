package com.project.retrofit.APISET;

import static com.project.retrofit.Controller.ApiController.URL_001;

import com.project.retrofit.Controller.RestaurantService;
import com.project.retrofit.model.Ville;
import com.project.retrofit.model.Zone;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Apiset {


    @GET("/villes/all")
    Call<List<Ville>> getAllVilles();

    @GET("/zones/all")
    Call<List<Zone>> getAllZones();

    @GET("/villes/zones")
    Call<String> getVilleZone(Zone zone);


    @GET("/zone/{ville_id}")
    public Call<List<Zone>> getZonesByVille(@Path("ville_id") int ville_id);
    public static RestaurantService getRestaurantService(){
        return  Cliente.getClient(URL_001).create(RestaurantService.class);
    }


}
