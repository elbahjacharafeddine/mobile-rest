package com.project.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.project.retrofit.model.Ville;
import com.project.retrofit.retrofit.ApiInterface;
import com.project.retrofit.retrofit.RetrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    // and store name of courses
    String[] courses = { "C", "Data structures",
            "Interview prep", "Algorithms",
            "DSA with java", "OS" };

    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);

        btn = findViewById(R.id.test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RestActivity.this,SignInSignUp.class));
            }
        });

        Spinner spino = findViewById(R.id.villeSpinner);
        spino.setOnItemSelectedListener(this);

        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad
                = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                courses);

        ad.setDropDownViewResource(
                android.R.layout
                        .simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spino.setAdapter(ad);


        RetrofitService retrofitService = new RetrofitService();
        ApiInterface apiInterface = retrofitService.getRetrofit().create(ApiInterface.class);
        apiInterface.getAllVilles()
                .enqueue(new Callback<List<Ville>>() {
                    @Override
                    public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                        adapterSpinner(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Ville>> call, Throwable t) {

                    }
                });


    }

    private void adapterSpinner(List<Ville> listVilles) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}