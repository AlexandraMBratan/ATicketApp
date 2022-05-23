package com.example.aticketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdminEventsByCategoryAdapter extends RecyclerView.Adapter<AdminEventsByCategoryAdapter.ViewHolder> {

    private Context context;
    private List<Event> eventsList;
    //private AdminEventsByCategoryAdapter.RecyclerViewInterface rvInterfaceEvByCat;


    public AdminEventsByCategoryAdapter(Context context, List<Event> eventsList) {
        this.context = context;
        this.eventsList = eventsList;
    }

    @NonNull
    @Override
    public AdminEventsByCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_event_by_category_admin,parent,false);
        //design row conectivity
        return new AdminEventsByCategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminEventsByCategoryAdapter.ViewHolder holder, int position) {

        Event event = eventsList.get(position);
        holder.txtNameCat.setText(event.getNumeEveniment());
        holder.txtArtistCat.setText(event.getArtist());
        holder.txtLocationCat.setText(event.getLocatie());
        holder.txtDateCat.setText(event.getData());
        holder.txtTimeCat.setText(event.getOra());

        String imageUri = null;
        imageUri =  event.getImagine();
        Picasso.get().load(imageUri).into(holder.imageEvCat);

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageEvCat;
        TextView txtNameCat, txtArtistCat, txtLocationCat, txtDateCat, txtTimeCat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvCat = itemView.findViewById(R.id.imagine_events_by_category_admin);
            txtNameCat = itemView.findViewById(R.id.nume_events_by_category_admin);
            txtArtistCat = itemView.findViewById(R.id.artist_events_by_category_admin);
            txtLocationCat = itemView.findViewById(R.id.locatie_events_by_category_admin);
            txtDateCat = itemView.findViewById(R.id.data_events_by_category_admin);
            txtTimeCat = itemView.findViewById(R.id.ora_events_by_category_admin);

        }
    }
}
