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

import java.util.List;

public class AdminAdapter extends RecyclerView.Adapter<AdminAdapter.ViewHolder> {

    Context context;
    List<Event> eventList;

    public AdminAdapter(Context context, List<Event> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_event_list_admin,parent,false);
        //design row conectivity

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Event event = eventList.get(position);
        holder.txtName.setText(event.getNumeEveniment());
        holder.txtArtist.setText(event.getArtist());
        holder.txtLocation.setText(event.getLocatie());
        holder.txtDate.setText(event.getData());
        holder.txtTime.setText(event.getOra());

        String imageUri = null;
        imageUri =  event.getImagine();
        Picasso.get().load(imageUri).into(holder.imageEv);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageEv;
        TextView txtName, txtArtist, txtLocation, txtDate,txtTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEv = itemView.findViewById(R.id.imagine_row_eventslist_admin);
            txtName = itemView.findViewById(R.id.nume_row_eventslist_admin);
            txtArtist = itemView.findViewById(R.id.artist_row_eventslist_admin);
            txtLocation = itemView.findViewById(R.id.locatie_row_eventslist_admin);
            txtDate = itemView.findViewById(R.id.data_row_eventslist_admin);
            txtTime = itemView.findViewById(R.id.ora_row_eventslist_admin);
        }

    }
}
