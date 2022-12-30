package com.project.retrofit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.retrofit.R;
import com.project.retrofit.model.Zone;

import java.util.List;

public class ZoneAdapter extends RecyclerView.Adapter<ZoneAdapter.ViewHolder> {

    List<Zone> zones;
    Context context;

    public ZoneAdapter(Context context, List<Zone> zones) {
        this.context=context;
        this.zones = zones;
    }

    @NonNull
    @Override
    public ZoneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_zone,parent,false);
        return new ZoneAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZoneAdapter.ViewHolder holder, int position) {
        holder.zone.setText(zones.get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return zones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView zone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            zone=itemView.findViewById(R.id.nom_zone);

        }
    }
}
