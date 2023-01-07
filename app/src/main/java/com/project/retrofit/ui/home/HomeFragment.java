package com.project.retrofit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.retrofit.Adapter.VilleAdapter;
import com.project.retrofit.Controller.ApiController;
import com.project.retrofit.Controller.RestaurantService;
import com.project.retrofit.R;
import com.project.retrofit.databinding.FragmentHomeBinding;
import com.project.retrofit.model.Restaurant;
import com.project.retrofit.model.Ville;
import com.project.retrofit.model.VilleResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    RestaurantService restaurantService;
    List<Restaurant>listRestaurant=new ArrayList<>();
    ListView listView;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();




        //retrofit


        return root;
    }


}