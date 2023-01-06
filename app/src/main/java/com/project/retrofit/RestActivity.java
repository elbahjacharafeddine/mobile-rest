package com.project.retrofit;

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


    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        btn = findViewById(R.id.test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RestActivity.this,MainActivity.class));
            }
        });

        //String[] courses = { "Charaf", "Data structures",
          //      "Interview prep", "Algorithms",
            //    "DSA with java", "OS" };
       // Spinner spino = findViewById(R.id.villeSpinner);

       // spino.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        RetrofitService retrofitService = new RetrofitService();
        ApiInterface apiInterface = retrofitService.getRetrofit().create(ApiInterface.class);
        apiInterface.getAllRestaurants()
                .enqueue(new Callback<List<Restaurant>>() {
                    @Override
                    public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                        populateListView(response.body());
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
                RetrofitService retrofitService = new RetrofitService();
                ApiInterface apiInterface = retrofitService.getRetrofit().create(ApiInterface.class);
                apiInterface.getAllRestaurants()
                        .enqueue(new Callback<List<Restaurant>>() {
                            @Override
                            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                                for (Restaurant r:response.body()
                                     ) {

                                }
                                //populateListView(response.body());
                            }

                            @Override
                            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                                System.out.println("error error");
                                //t.printStackTrace();
                                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                            }
                        });
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate menu with items using MenuInflator
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuu, menu);

        // Initialise menu item search bar
        // with id and take its object
        MenuItem searchViewItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        // attach setOnQueryTextListener
        // to search view defined above
        return super.onCreateOptionsMenu(menu);
    }
}