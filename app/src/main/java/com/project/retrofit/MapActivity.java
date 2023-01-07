package com.project.retrofit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.project.retrofit.Adapter.RestaurantAdapter;
import com.project.retrofit.model.Restaurant;
import com.project.retrofit.retrofit.ApiInterface;
import com.project.retrofit.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // below are the latitude and longitude
    // of 4 different locations.
    LatLng sydney = new LatLng(-34, 151);
    LatLng TamWorth = new LatLng(-31.083332, 150.916672);
    LatLng NewCastle = new LatLng(-32.916668, 151.750000);
    LatLng Brisbane = new LatLng(-27.470125, 153.021072);



    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // in below line we are initializing our array list.


        // on below line we are adding our
        // locations in our array list.

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        locationArrayList = new ArrayList<>();

        RetrofitService retrofitService = new RetrofitService();
        ApiInterface apiInterface = retrofitService.getRetrofit().create(ApiInterface.class);
        apiInterface.getAllRestaurants()
                .enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {

                       // Toast.makeText(getApplicationContext(),"tteee",Toast.LENGTH_SHORT).show();
                        for (Restaurant r: response.body()
                        ) {
                            System.out.println(r.getLatitude()+"elbahja");
                            locationArrayList.add(new LatLng(r.getLatitude(),r.getLongitude()));
                        }

                        mMap = googleMap;
                        // inside on map ready method
                        // we will be displaying all our markers.
                        // for adding markers we are running for loop and
                        // inside that we are drawing marker on our map.
                        System.out.println(locationArrayList.size()+"elbahja");
                        for (int i = 0; i < locationArrayList.size(); i++) {

                            // below line is use to add marker to each location of our array list.
                            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(i)).title("Marker"));

                            // below line is use to zoom our camera on map.
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

                            // below line is use to move our camera to the specific location.
                            //mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));
                        }

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationArrayList.get(0),8));

                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                        System.out.println("error error");
                        //t.printStackTrace();
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    }
                });


    }
}