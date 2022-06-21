package com.example.aticketapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserMyPurchasesAdapter extends RecyclerView.Adapter<UserMyPurchasesAdapter.ViewHolder>{
    private Context context;
    private List<Purchase> purchasesList;
    private UserMyPurchasesAdapter.RecyclerViewInterface listener;
    private List<Event> eventList;

    public UserMyPurchasesAdapter(Context context, List<Purchase> purchasesList, UserMyPurchasesAdapter.RecyclerViewInterface listener) {
        this.context = context;
        this.purchasesList = purchasesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserMyPurchasesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_purchases_user,parent,false);

        return new UserMyPurchasesAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserMyPurchasesAdapter.ViewHolder holder, int position) {
        Purchase purchase = purchasesList.get(position);

        holder.txtNamePurchaseUser.setText(purchase.getNumeEveniment());
        holder.txtArtistPurchaseUser.setText(purchase.getArtistEveniment());
        holder.txtLocationPurchaseUser.setText(purchase.getLocatieEveniment());
        holder.txtDatePurchaseUser.setText(purchase.getDataEveniment());
        holder.txtTimePurchaseUser.setText(purchase.getOraEveniment());


        String imageUri = null;
        imageUri =  purchase.getImagineEveniment();
        Picasso.get().load(imageUri).into(holder.imageEvPurchaseUser);

        holder.generateTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.generateTicket.getContext(), QRCodeActivity.class);
                intent.putExtra("numeEveniment", purchase.getNumeEveniment());
                holder.generateTicket.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return purchasesList.size();
    }

    public void filterMyPurchasesList(List<Purchase> filteredPurchasesList){
        this.purchasesList = filteredPurchasesList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageEvPurchaseUser;
        TextView txtNamePurchaseUser, txtArtistPurchaseUser, txtLocationPurchaseUser, txtDatePurchaseUser, txtTimePurchaseUser;
        Button generateTicket;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageEvPurchaseUser = itemView.findViewById(R.id.imagine_row_purchase_user);
            txtNamePurchaseUser = itemView.findViewById(R.id.nume_row_purchase_user);
            txtArtistPurchaseUser = itemView.findViewById(R.id.artist_row_purchase_user);
            txtLocationPurchaseUser = itemView.findViewById(R.id.locatie_row_purchase_user);
            txtDatePurchaseUser = itemView.findViewById(R.id.data_row_purchase_user);
            txtTimePurchaseUser = itemView.findViewById(R.id.ora_row_purchase_user);
            generateTicket = itemView.findViewById(R.id.generateTicketUser);

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
