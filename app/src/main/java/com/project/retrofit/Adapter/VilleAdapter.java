package com.project.retrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.retrofit.R;
import com.project.retrofit.model.Ville;

import java.util.List;

public class VilleAdapter extends RecyclerView.Adapter<VilleAdapter.ViewHolder> {

    List<Ville> villes;
    Context context;

    public VilleAdapter(Context context, List<Ville> villes) {
        this.context=context;
        this.villes = villes;
    }

    @NonNull
    @Override
    public VilleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_ville,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VilleAdapter.ViewHolder holder, int position) {
        holder.ville.setText(villes.get(position).getNom());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(),"ite" ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return villes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ville;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ville=itemView.findViewById(R.id.nom_ville);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
