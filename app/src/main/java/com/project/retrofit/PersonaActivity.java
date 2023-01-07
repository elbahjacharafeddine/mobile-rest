package com.project.retrofit;

import static com.project.retrofit.Controller.ApiController.URL_001;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.retrofit.APISET.Apiset;
import com.project.retrofit.Controller.RestaurantService;
import com.project.retrofit.Controller.VilleService;
import com.project.retrofit.Controller.ZoneService;
import com.project.retrofit.model.Restaurant;
import com.project.retrofit.model.Ville;
import com.project.retrofit.model.Zone;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PersonaActivity extends AppCompatActivity  {
    RestaurantService service;
    private Spinner spinner, SpinnerZone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persona);
        spinner = findViewById(R.id.SpinnerVille);
        SpinnerZone = findViewById(R.id.SpinnerZone);

        List<String> Ville = new ArrayList<>();
        List<String> Zone = new ArrayList<>();

        //Call Me
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_001).addConverterFactory(GsonConverterFactory.create()).build();

        VilleService userController = retrofit.create(VilleService.class);

        Call<List<com.project.retrofit.model.Ville>>  usersCall = userController.getAllUsers();


        usersCall.enqueue(new Callback<List<com.project.retrofit.model.Ville>>() {
            @Override
            public void onResponse(Call<List<com.project.retrofit.model.Ville>> call, Response<List<com.project.retrofit.model.Ville>> response) {

                List<Ville> users = response.body();
                List<String> usersString = new ArrayList<>();
                usersString.add("--SELECT--");
                for(Ville user : users){
                    usersString.add(user.getNom());
                }

                ArrayAdapter<String> Usertype = new ArrayAdapter<String>(PersonaActivity.this, android.R.layout.simple_spinner_item,usersString);
                Usertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(Usertype);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                      @Override
                                                      public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                          Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_001).addConverterFactory(GsonConverterFactory.create()).build();

                                                          ZoneService userController1 = retrofit.create(ZoneService.class);

                                                          Call<List<com.project.retrofit.model.Zone>>  usersCall1 = userController1.getZonesByVille(i);

                                                          usersCall1.enqueue(new Callback<List<com.project.retrofit.model.Zone>>() {
                                                              @Override
                                                              public void onResponse(Call<List<com.project.retrofit.model.Zone>> call, Response<List<com.project.retrofit.model.Zone>> response) {

                                                                  List<Zone> zones = response.body();
                                                                  List<String> usersString1 = new ArrayList<>();
                                                                  usersString1.add("--SELECT--");
                                                                  for(Zone zone : zones){
                                                                      usersString1.add(zone.getNom());
                                                                  }

                                                                  ArrayAdapter<String> Usertype1 = new ArrayAdapter<String>(PersonaActivity.this, android.R.layout.simple_spinner_item,usersString1);
                                                                  Usertype1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                                  SpinnerZone.setAdapter(Usertype1);

                                                              }


                                                              @Override
                                                              public void onFailure(Call<List<com.project.retrofit.model.Zone>> call, Throwable t) {
                                                                  Toast.makeText(PersonaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                                              }
                                                          });
                                                      }

                                                      @Override
                                                      public void onNothingSelected(AdapterView<?> adapterView) {

                                                      }
                                                  }

                );

            }

            @Override
            public void onFailure(Call<List<com.project.retrofit.model.Ville>> call, Throwable t) {
                Toast.makeText(PersonaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });




















        TextView idper=(TextView)findViewById(R.id.Id);
        EditText txtId=(EditText)findViewById(R.id.txtId);
        TextView nomR=(TextView)findViewById(R.id.nom);
        final EditText txtNom=(EditText)findViewById(R.id.txtNom);
        TextView adresse=(TextView)findViewById(R.id.adresse);
        final EditText txtAdresse=(EditText)findViewById(R.id.txtAdresse);
        TextView latitude=(TextView)findViewById(R.id.latitude);
        final EditText txtLatitude=(EditText)findViewById(R.id.txtLatitude);
        TextView longitude=(TextView)findViewById(R.id.longitude);
        final EditText txtLongitude=(EditText)findViewById(R.id.txtLongitude);
        TextView rank=(TextView)findViewById(R.id.rank);
        final EditText txtRank=(EditText)findViewById(R.id.txtRank);

        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);


        Bundle bundle=getIntent().getExtras();
        final String id = bundle.getString("ID");
        String nom=bundle.getString("NOM");
        String adresse1=bundle.getString("ADRESSE");
        String latitude1=bundle.getString("LATITUDE");
        String longitude1=bundle.getString("LONGITUDE");
        String rank1=bundle.getString("RANK");

        txtId.setText(id);
        txtNom.setText(nom);
        txtAdresse.setText(adresse1);
        txtLatitude.setText(latitude1);
        txtLongitude.setText(longitude1);
        txtRank.setText(rank1);

        if(id.trim().length()==0||id.equals("")){
            idper.setVisibility(View.INVISIBLE);
            txtId.setVisibility(View.INVISIBLE);
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Restaurant p=new Restaurant();
                p.setNom(txtNom.getText().toString());
                p.setAdresse(txtAdresse.getText().toString());
                p.setLatitude(Double.parseDouble(txtLatitude.getText().toString()));
                p.setLongitude(Double.parseDouble(txtLongitude.getText().toString()));
                p.setRank(txtRank.getText().toString());


                if(id.trim().length()==0||id.equals("")){
                    addPersona(p);
                    Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    updatePersona(p,Integer.valueOf(id));
                    Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePersona(Integer.valueOf(id));
                Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void addPersona(Restaurant p){
        service= Apiset.getRestaurantService();
        Call<Restaurant>call=service.addRestaurant(p);
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonaActivity.this,"Se agrego conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
        startActivity(intent);
    }
    public void updatePersona(Restaurant p, int id){
        service= Apiset.getRestaurantService();
        Call<Restaurant>call=service.updateRestaurant(p,id);
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonaActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }

        });
        Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
        startActivity(intent);
    }


    public void deletePersona(int id){
        service=Apiset.getRestaurantService();
        Call<Restaurant>call=service.deleteRestaurant(id);
        call.enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                if(response.isSuccessful()){
                    Toast.makeText(PersonaActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(PersonaActivity.this,MainActivity.class);
        startActivity(intent);
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
