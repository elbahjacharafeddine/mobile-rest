package com.project.retrofit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> implements Filterable {
    List<Restaurant> restaurants;
    List<Restaurant> restaurantsFilter;
    Context context;
    ArrayList<String> lista;



    public RestaurantAdapter(List<Restaurant> restaurants, Context context) {
        this.restaurants = restaurants;
        this.context = context;
        this.restaurantsFilter = restaurants;
        lista = new ArrayList<>();
        lista.add("\n" +
                "https://thumbs.dreamstime.com/z/int%C3%A9rieur-moderne-de-bar-ou-de-restaurant-9989889.jpg");
        lista.add("https://previews.123rf.com/images/fuutamin/fuutamin1301/fuutamin130100002/17269412-salon-wxyz.jpg");

        lista.add("https://previews.123rf.com/images/krsmanovic/krsmanovic1212/krsmanovic121200032/16988255-int%C3%A9rieur-du-restaurant-moderne-qui-fait-partie-d-une-sc%C3%A8ne-de-nuit-h%C3%B4tel-.jpg");
        lista.add("https://img.freepik.com/photos-gratuite/salle-restaurant-murs-briques-rouges-tables-tuyaux-bois-au-plafond_140725-8504.jpg");
        lista.add("https://img.freepik.com/photos-gratuite/salle-restaurant-murs-briques-rouges-tables-tuyaux-bois-au-plafond_140725-8504.jpg");
        lista.add("https://media.admagazine.fr/photos/61890c6bdbe704a2aec1047c/master/w_1600%2Cc_limit/SLIDER-1.jpeg");


    }

    @NonNull
    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int randomNumber=new Random().nextInt(6);;
        holder.restaurant.setText(restaurantsFilter.get(position).getNom());
        holder.id_restaurant.setText(Math.toIntExact(restaurantsFilter.get(position).getId())+"");
        holder.latitude.setText(restaurantsFilter.get(position).getLatitude()+"");
        holder.longitude.setText(restaurantsFilter.get(position).getLongitude()+"");
        holder.ville.setText("ville :"+restaurantsFilter.get(position).getZone().getVille().getNom());
        holder.zone.setText("zone :"+restaurantsFilter.get(position).getZone().getNom());
        holder.open.setText("heure d'ouverture"+ restaurantsFilter.get(position).getHeureOpen());
        holder.close.setText("heure de fermeture"+restaurantsFilter.get(position).getHeureClose());
        Glide.with(this.context)
                .load(lista.get(randomNumber))
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
        return restaurantsFilter.size();
    }




    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                    String searchString = charSequence.toString();

                    if (searchString.isEmpty()) {

                        restaurantsFilter = restaurants;

                    } else {

                        ArrayList<Restaurant> tempFilteredList = new ArrayList<>();

                        for (Restaurant restaurant : restaurants) {

                            // search for user title
                            if (restaurant.getNom().toLowerCase().contains(searchString) || restaurant.getZone().getNom().toLowerCase().contains(searchString) || restaurant.getZone().getVille().getNom().toLowerCase().contains(searchString) || restaurant.getHeureOpen().toLowerCase().contains(searchString)){

                                tempFilteredList.add(restaurant);
                            }
                        }

                        restaurantsFilter = tempFilteredList;
                    }

                    FilterResults filterResults = new FilterResults();
                    filterResults.values = restaurantsFilter;
                    return filterResults;
                }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                restaurantsFilter = (ArrayList<Restaurant>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView restaurant;
        TextView id_restaurant;
        TextView latitude;
        TextView longitude,ville,zone,open,close;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurant=itemView.findViewById(R.id.nom_restaurant);
            id_restaurant = itemView.findViewById(R.id.id_rest);
            image = itemView.findViewById(R.id.imageRest);
            latitude=itemView.findViewById(R.id.latitude);
            longitude=itemView.findViewById(R.id.longitude);
            ville = itemView.findViewById(R.id.ville);
            zone = itemView.findViewById(R.id.zone);
            open = itemView.findViewById(R.id.open);
            close =itemView.findViewById(R.id.close);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

        }
    }
}
