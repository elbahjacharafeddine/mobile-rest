package com.project.retrofit.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.retrofit.Adapter.VilleAdapter;
import com.project.retrofit.Controller.ApiController;
import com.project.retrofit.R;
import com.project.retrofit.databinding.FragmentHomeBinding;
import com.project.retrofit.model.Ville;
import com.project.retrofit.model.VilleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView= root.findViewById(R.id.rcv_ville);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        //retrofit

        processData();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void processData(){
        Call<List<Ville>> call = ApiController.getInstance()
                                                .getapi()
                                                .getAllVilles();
        call.enqueue(new Callback<List<Ville>>() {

            @Override
            public void onResponse(Call<List<Ville>> call, Response<List<Ville>> response) {
                List<Ville> data = response.body();
                VilleAdapter adapter = new VilleAdapter(getContext(),data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Ville>> call, Throwable t) {

                Toast.makeText(getContext().getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();

            }
        });
     }
}