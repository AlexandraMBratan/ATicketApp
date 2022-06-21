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

public class UserCloseEventsAdapter extends RecyclerView.Adapter<UserCloseEventsAdapter.ViewHolder>{


    private Context context;
    private List<Event> closeEventsList;
    private UserCloseEventsAdapter.RecyclerViewInterface listener;

    public UserCloseEventsAdapter(Context context, List<Event> closeEventsList, UserCloseEventsAdapter.RecyclerViewInterface listener) {
        this.context = context;
        this.closeEventsList = closeEventsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserCloseEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_close_events_user,parent,false);
        //design row conectivity
        return new UserCloseEventsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCloseEventsAdapter.ViewHolder holder, int position) {


        Event event = closeEventsList.get(position);
        holder.txtNameCloseUser.setText(event.getNumeEveniment());
        holder.txtArtistCloseUser.setText(event.getArtist());
        holder.txtLocationCloseUser.setText(event.getLocatie());
        holder.txtDateCloseUser.setText(event.getData());
        holder.txtTimeCloseUser.setText(event.getOra());

        String imageUri = null;
        imageUri =  event.getImagine();
        Picasso.get().load(imageUri).into(holder.imageEvCloseUser);
    }

    @Override
    public int getItemCount() {
        return closeEventsList.size();
    }

    public void filterCloseEventList(List<Event> filteredEventList){
        this.closeEventsList = filteredEventList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imageEvCloseUser;
        TextView txtNameCloseUser, txtArtistCloseUser, txtLocationCloseUser, txtDateCloseUser, txtTimeCloseUser;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            imageEvCloseUser = itemView.findViewById(R.id.imagine_close_events_user);
            txtNameCloseUser = itemView.findViewById(R.id.nume_close_events_user);
            txtArtistCloseUser = itemView.findViewById(R.id.artist_close_events_user);
            txtLocationCloseUser = itemView.findViewById(R.id.locatie_close_events_user);
            txtDateCloseUser = itemView.findViewById(R.id.data_close_events_user);
            txtTimeCloseUser = itemView.findViewById(R.id.ora_close_events_user);

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
