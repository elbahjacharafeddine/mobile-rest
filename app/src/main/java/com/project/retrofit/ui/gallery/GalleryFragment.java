package com.project.retrofit.ui.gallery;

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
import com.project.retrofit.Adapter.ZoneAdapter;
import com.project.retrofit.Controller.ApiController;
import com.project.retrofit.R;
import com.project.retrofit.databinding.FragmentGalleryBinding;
import com.project.retrofit.model.Ville;
import com.project.retrofit.model.Zone;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GalleryFragment extends Fragment {

    RecyclerView recyclerView;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView= root.findViewById(R.id.rcv_zone);
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
        Call<List<Zone>> call = ApiController.getInstance()
                .getapi()
                .getAllZones();
        call.enqueue(new Callback<List<Zone>>() {

            @Override
            public void onResponse(Call<List<Zone>> call, Response<List<Zone>> response) {
                List<Zone> data = response.body();
                ZoneAdapter adapter = new ZoneAdapter(getContext(),data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Zone>> call, Throwable t) {

                Toast.makeText(getContext().getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();

            }
        });
    }
}