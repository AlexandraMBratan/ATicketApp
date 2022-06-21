package com.example.aticketapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
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
        holder.txtDateReservationUser.setText(reservation.getDataEvenimentRezervat());
        holder.txtTimeReservationUser.setText(reservation.getOraEvenimentRezervat());

        String imageUri = null;
        imageUri =  reservation.getImagineEvenimentRezervat();
        Picasso.get().load(imageUri).into(holder.imageEvReservationUser);

        holder.updateRezervationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.txtNameReservationUser.getContext(), "Rezervarea va fi actualizata", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(holder.updateRezervationButton.getContext(), UserUpdateReservationPageActivity.class);
                intent.putExtra("numeEveniment", reservation.getNumeEvenimentRezervat());
                intent.putExtra("artist", reservation.getArtistEvenimentRezervat());
                intent.putExtra("data", reservation.getDataEvenimentRezervat());
                intent.putExtra("ora", reservation.getOraEvenimentRezervat());
                intent.putExtra("locatie", reservation.getLocatieEvenimentRezervat());
                intent.putExtra("pret", reservation.getPretBiletEvenimentRezervat());
                //intent.putExtra("cantitateTotala", reservation.getCantitateTotala());
                intent.putExtra("descriere", reservation.getDescriereEvenimentRezervat());
                //intent.putExtra("tip", reservation.getTip());
                intent.putExtra("imagine", reservation.getImagineEvenimentRezervat());
                intent.putExtra("idEvent", reservation.getIdEventRezervat());
                intent.putExtra("cantitateBileteRezervat", reservation.getCantitateBileteRezervat());
                intent.putExtra("pretTotal", reservation.getPretTotalRezervat());
                intent.putExtra("idUser", reservation.getIdUserRezervat());
                intent.putExtra("idReservation", reservation.getIdRezervation());
                intent.putExtra("status", reservation.getStatus());
                holder.updateRezervationButton.getContext().startActivity(intent);
            }
        });

        holder.deleteReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txtNameReservationUser.getContext());
                builder.setTitle("Sunteti sigur ca doriti sa stergeti rezervarea?");
                DatabaseReference refData = FirebaseDatabase.getInstance().getReference().child("Rezervari").child(reservation.getIdRezervation());
                FirebaseStorage mStorage = FirebaseStorage.getInstance();
                StorageReference imageStorage = mStorage.getReferenceFromUrl(reservation.getImagineEvenimentRezervat());

                builder.setPositiveButton("Sterge", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        refData.removeValue();
                        Toast.makeText(holder.txtNameReservationUser.getContext(), "Rezervarea s-a sters", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(holder.deleteReservationButton.getContext(), UserMyReservationsPageActivity.class);
                        holder.updateRezervationButton.getContext().startActivity(intent);
                    }
                });

                builder.setNegativeButton("Inchide", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.txtNameReservationUser.getContext(), "Nu s-a sters rezervarea", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });
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
        Button deleteReservationButton,updateRezervationButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvReservationUser = itemView.findViewById(R.id.imagine_row_reservation_user);
            txtNameReservationUser = itemView.findViewById(R.id.nume_row_reservation_user);
            txtArtistReservationUser = itemView.findViewById(R.id.artist_row_reservation_user);
            txtLocationReservationUser = itemView.findViewById(R.id.locatie_row_reservation_user);
            txtDateReservationUser = itemView.findViewById(R.id.data_row_reservation_user);
            txtTimeReservationUser = itemView.findViewById(R.id.ora_row_reservation_user);

            deleteReservationButton = itemView.findViewById(R.id.deleteReservationUser);
            updateRezervationButton = itemView.findViewById(R.id.updateReservationUser);

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
