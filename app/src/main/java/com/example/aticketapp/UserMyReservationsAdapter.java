package com.example.aticketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserMyReservationsAdapter extends RecyclerView.Adapter<UserMyReservationsAdapter.ViewHolder>{
    private Context context;
    private List<Reservation> reservationsList;
    private UserMyReservationsAdapter.RecyclerViewInterface listener;
    private List<Event> eventList;

    public UserMyReservationsAdapter(Context context, List<Reservation> reservationsList, UserMyReservationsAdapter.RecyclerViewInterface listener) {
        this.context = context;
        this.reservationsList = reservationsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserMyReservationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_reservations_user,parent,false);

        return new UserMyReservationsAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserMyReservationsAdapter.ViewHolder holder, int position) {
        Reservation reservation = reservationsList.get(position);

        holder.txtNameReservationUser.setText(reservation.getNumeEvenimentRezervat());
        holder.txtArtistReservationUser.setText(reservation.getArtistEvenimentRezervat());
        holder.txtLocationReservationUser.setText(reservation.getLocatieEvenimentRezervat());
        //holder.txtDateReservationUser.setText(event.getData());
        //holder.txtTimeReservationUser.setText(event.getOra());

        String imageUri = null;
        imageUri =  reservation.getImagineEvenimentRezervat();
        Picasso.get().load(imageUri).into(holder.imageEvReservationUser);

    }

    @Override
    public int getItemCount() {
        return reservationsList.size();
    }

    public void filterMyReservationList(List<Reservation> filteredReservationList){
        this.reservationsList = filteredReservationList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageEvReservationUser;
        TextView txtNameReservationUser, txtArtistReservationUser, txtLocationReservationUser, txtDateReservationUser, txtTimeReservationUser;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvReservationUser = itemView.findViewById(R.id.imagine_row_reservation_user);
            txtNameReservationUser = itemView.findViewById(R.id.nume_row_reservation_user);
            txtArtistReservationUser = itemView.findViewById(R.id.artist_row_reservation_user);
            txtLocationReservationUser = itemView.findViewById(R.id.locatie_row_reservation_user);
            //txtDateReservationUser = itemView.findViewById(R.id.data_row_reservation_user);
            //txtTimeReservationUser = itemView.findViewById(R.id.ora_row_reservation_user);

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
