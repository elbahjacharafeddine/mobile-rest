package com.project.retrofit.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.project.retrofit.PersonaActivity;
import com.project.retrofit.R;
import com.project.retrofit.model.Restaurant;
import com.project.retrofit.ui.gallery.GalleryFragment;

import java.util.List;

public class PersonaAdapter extends ArrayAdapter<Restaurant> {

    private Context context;
    private  List<Restaurant>personas;

    public PersonaAdapter(@NonNull Context context, int resource, @NonNull List<Restaurant> objects) {
        super(context, resource, objects);
        this.context=context;
        this.personas=objects;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=layoutInflater.inflate(R.layout.content_main1,parent,false);

        TextView txtidPersona=(TextView)rowView.findViewById(R.id.IdPersona);
        TextView txtNombre=(TextView)rowView.findViewById(R.id.Nombre);;
        TextView txtApellidos=(TextView)rowView.findViewById(R.id.Apellidos);;

        txtidPersona.setText(String.format("ID:%d",personas.get(position).getId()));
        txtNombre.setText(String.format("NOM:%s",personas.get(position).getNom()));
        txtApellidos.setText(String.format("ADRESSE: %s",personas.get(position).getAdresse()));

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(context, PersonaActivity.class);
               intent.putExtra("ID",String.valueOf(personas.get(position).getId()));
               intent.putExtra("NOM",personas.get(position).getNom());
                intent.putExtra("ADRESSE",personas.get(position).getAdresse());
               context.startActivity(intent);
            }
        });
        return rowView;

    }

}
