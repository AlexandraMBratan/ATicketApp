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

public class UserEventsByCategoryAdapter extends RecyclerView.Adapter<UserEventsByCategoryAdapter.ViewHolder>{

    private Context context;
    private List<Event> eventsList;
    private RecyclerViewInterface listener;

    public UserEventsByCategoryAdapter(Context context, List<Event> eventsList, RecyclerViewInterface listener) {
        this.context = context;
        this.eventsList = eventsList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public UserEventsByCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_event_by_category_user,parent,false);
        //design row conectivity
        return new UserEventsByCategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserEventsByCategoryAdapter.ViewHolder holder, int position) {

        Event event = eventsList.get(position);
        holder.txtNameCatUser.setText(event.getNumeEveniment());
        holder.txtArtistCatUser.setText(event.getArtist());
        holder.txtLocationCatUser.setText(event.getLocatie());
        holder.txtDateCatUser.setText(event.getData());
        holder.txtTimeCatUser.setText(event.getOra());

        String imageUri = null;
        imageUri =  event.getImagine();
        Picasso.get().load(imageUri).into(holder.imageEvCatUser);

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    public void filterEventByCategoryList(List<Event> filteredEventList){
        this.eventsList = filteredEventList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageEvCatUser;
        TextView txtNameCatUser, txtArtistCatUser, txtLocationCatUser, txtDateCatUser, txtTimeCatUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvCatUser = itemView.findViewById(R.id.imagine_events_by_category_user);
            txtNameCatUser = itemView.findViewById(R.id.nume_events_by_category_user);
            txtArtistCatUser = itemView.findViewById(R.id.artist_events_by_category_user);
            txtLocationCatUser = itemView.findViewById(R.id.locatie_events_by_category_user);
            txtDateCatUser = itemView.findViewById(R.id.data_events_by_category_user);
            txtTimeCatUser = itemView.findViewById(R.id.ora_events_by_category_user);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view, getAbsoluteAdapterPosition());
        }
    }


    public interface RecyclerViewInterface {
        void onItemClick(View view, int position);
    }
}
