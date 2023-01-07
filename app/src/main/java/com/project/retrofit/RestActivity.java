package com.project.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.retrofit.Adapter.RestaurantAdapter;
import com.project.retrofit.model.Restaurant;
import com.project.retrofit.model.Ville;
import com.project.retrofit.retrofit.ApiInterface;
import com.project.retrofit.retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    // and store name of courses
    private RecyclerView recyclerView;
    BottomNavigationView bottomNavigationView ;

    private RestaurantAdapter restaurantAdapter;
    private ArrayList<Restaurant> restaurantList;


    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);


        //String[] courses = { "Charaf", "Data structures",
          //      "Interview prep", "Algorithms",
            //    "DSA with java", "OS" };
       // Spinner spino = findViewById(R.id.villeSpinner);

       // spino.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item0:
                        // do something here
                        return true;
                    case R.id.item1:
                        // do something here
                        startActivity(new Intent(RestActivity.this,MapActivity.class));
                        return true;
                    case R.id.item2:
                        // do something here
                        return true;
                    case R.id.item3:
                        startActivity(new Intent(RestActivity.this,MainActivity.class));
                        return true;
                    default: return true;
                }
            }
        });



        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RetrofitService retrofitService = new RetrofitService();
        ApiInterface apiInterface = retrofitService.getRetrofit().create(ApiInterface.class);
        apiInterface.getAllRestaurants()
                .enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                        restaurantList = new ArrayList<>(response.body());
                        restaurantAdapter = new RestaurantAdapter(restaurantList,getApplicationContext());
                        recyclerView.setAdapter(restaurantAdapter);
                        //populateListView(restaurantList);
                    }

                    @Override
                    public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                        System.out.println("error error");
                            //t.printStackTrace();
                            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    }
                });


        SearchView simpleSearchView = (SearchView) findViewById(R.id.simpleSearchView); // inititate a search view
        CharSequence query = simpleSearchView.getQuery();
        simpleSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Toast.makeText(getApplicationContext(),"elbahja",Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
                restaurantAdapter.getFilter().filter(s);
                return false;
            }
        });

    }

    private void populateListView(List<Restaurant> listBody) {

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(listBody,getApplicationContext());
        recyclerView.setAdapter(restaurantAdapter);
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}