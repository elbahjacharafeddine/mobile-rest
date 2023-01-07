package com.project.retrofit.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.retrofit.APISET.Apiset;
import com.project.retrofit.Adapter.PersonaAdapter;
import com.project.retrofit.Controller.RestaurantService;
import com.project.retrofit.MainActivity;
import com.project.retrofit.PersonaActivity;
import com.project.retrofit.R;
import com.project.retrofit.databinding.FragmentGalleryBinding;
import com.project.retrofit.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GalleryFragment extends Fragment {

    RestaurantService restaurantService;
    List<Restaurant>listRestaurant=new ArrayList<>();
    ListView listView;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        listView=(ListView)root.findViewById(R.id.listView);

        listPersons();

        FloatingActionButton fab = getView().findViewById(R.id.fabe);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), PersonaActivity.class);
                intent.putExtra("ID","");
                intent.putExtra("NOM","");
                intent.putExtra("ADRESSE","");
                startActivity(intent);
            }
        });

        //retrofit




        return root;
    }

    public void listPersons(){
        restaurantService= Apiset.getRestaurantService();
        Call<List<Restaurant>>call=restaurantService.getRestaurants();
        call.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                if(response.isSuccessful()) {
                    listRestaurant = response.body();
                    listView.setAdapter(new PersonaAdapter(getContext(),R.layout.content_main1,listRestaurant
                    ));
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}