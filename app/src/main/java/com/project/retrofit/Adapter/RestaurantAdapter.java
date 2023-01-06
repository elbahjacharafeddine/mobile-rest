package com.project.retrofit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.project.retrofit.MapsActivity;
import com.project.retrofit.R;
import com.project.retrofit.model.Restaurant;
import com.project.retrofit.model.Ville;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{
    List<Restaurant> restaurants;
    List<Restaurant> restaurantsFilter;
    Context context;

    public RestaurantAdapter(List<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.restaurant.setText(restaurants.get(position).getNom());
        holder.id_restaurant.setText(Math.toIntExact(restaurants.get(position).getId())+"");
        holder.latitude.setText(restaurants.get(position).getLatitude()+"");
        holder.longitude.setText(restaurants.get(position).getLongitude()+"");
        Glide.with(this.context)
                .load("https://s-media-cache-ak0.pinimg.com/originals/f2/b5/f2/f2b5f2aeb31e079f7e48ac0c338a8507.jpg")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);

        //holder.image.setImageResource("https://i.redd.it/qn7f9oqu7o501.jpg");

             //viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageUrl());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nom = view.findViewById(R.id.nom_restaurant);
                TextView lat = view.findViewById(R.id.latitude);
                TextView lon = view.findViewById(R.id.longitude);

                //Toast.makeText(context.getApplicationContext(),"ite" ,Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, lat.getText().toString(),Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, v.getText().toString(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, MapsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("lat",lat.getText().toString());
                intent.putExtra("lon",lon.getText().toString());
                intent.putExtra("nom",nom.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView restaurant;
        TextView id_restaurant;
        TextView latitude;
        TextView longitude;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurant=itemView.findViewById(R.id.nom_restaurant);
            id_restaurant = itemView.findViewById(R.id.id_rest);
            image = itemView.findViewById(R.id.imageRest);
            latitude=itemView.findViewById(R.id.latitude);
            longitude=itemView.findViewById(R.id.longitude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

        }
    }
}
