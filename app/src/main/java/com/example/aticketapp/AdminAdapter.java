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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
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

        holder.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(holder.txtName.getContext(), "Evenimentul va fi actualizat", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(holder.updateButton.getContext(), AdminUpdateEventPageActivity.class);
                intent.putExtra("numeEveniment", event.getNumeEveniment());
                intent.putExtra("artist", event.getArtist());
                intent.putExtra("data", event.getData());
                intent.putExtra("ora", event.getOra());
                intent.putExtra("locatie", event.getLocatie());
                intent.putExtra("pret", event.getPret());
                intent.putExtra("cantitateTotala", event.getCantitateTotala());
                intent.putExtra("descriere", event.getDescriere());
                intent.putExtra("tip", event.getTip());
                intent.putExtra("imagine", event.getImagine());
                intent.putExtra("idEvent", event.getIdEvent());
                holder.updateButton.getContext().startActivity(intent);
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.txtName.getContext());
                builder.setTitle("Sunteti sigur ca doriti sa stergeti evenimentul?");
                DatabaseReference refData = FirebaseDatabase.getInstance().getReference().child("Evenimente").child(event.getIdEvent());
                FirebaseStorage mStorage = FirebaseStorage.getInstance();
                StorageReference imageStorage = mStorage.getReferenceFromUrl(event.getImagine());

                builder.setPositiveButton("Sterge", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        refData.removeValue();
                        Toast.makeText(holder.txtName.getContext(), "Evenimentul s-a sters", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Inchide", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.txtName.getContext(), "Nu s-a sters evenimentul", Toast.LENGTH_LONG).show();
                    }
                });
                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public void filterEventList(List<Event> filteredEventList){
        this.eventList = filteredEventList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageEv;
        TextView txtName, txtArtist, txtLocation, txtDate,txtTime;

        Button deleteButton,updateButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEv = itemView.findViewById(R.id.imagine_row_eventslist_admin);
            txtName = itemView.findViewById(R.id.nume_row_eventslist_admin);
            txtArtist = itemView.findViewById(R.id.artist_row_eventslist_admin);
            txtLocation = itemView.findViewById(R.id.locatie_row_eventslist_admin);
            txtDate = itemView.findViewById(R.id.data_row_eventslist_admin);
            txtTime = itemView.findViewById(R.id.ora_row_eventslist_admin);

           updateButton = (Button) itemView.findViewById(R.id.updateEventAdmin);
           deleteButton = (Button) itemView.findViewById(R.id.deleteEventAdmin);

        }

    }

}
