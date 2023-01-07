package com.project.retrofit.Controller;

import com.project.retrofit.APISET.Apiset;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static final String url="http://10.0.2.2:8088/villes/";
    private static ApiController clientobject;
    private static Retrofit retrofit;
    public static final String URL_001="http://10.0.2.2:8083/personas/";

    public static RestaurantService getPersonaService(){
        return  Cliente.getClient(URL_001).create(RestaurantService.class);
    }
    public ApiController() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  synchronized ApiController getInstance(){
        if(clientobject == null){
            clientobject = new ApiController();
        }
        return clientobject;

    }
    public Apiset getapi() {
        return retrofit.create(Apiset.class);
    }
}
