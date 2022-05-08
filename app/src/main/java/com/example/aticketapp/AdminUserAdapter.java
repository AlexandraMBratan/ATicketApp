package com.example.aticketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.ViewHolder> {

    Context context;
    List<User> userList;

    public AdminUserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public AdminUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_row_user_list_admin,parent,false);
        //design row conectivity

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminUserAdapter.ViewHolder holder, int position) {

        User user = userList.get(position);
        if(!(user.getEmail().equals("admin@yahoo.com"))) {
            holder.txtNumeUser.setText(user.getNume());
            holder.txtPrenumeUser.setText(user.getPrenume());
            holder.txtTelefon.setText(user.getTelefon());
            holder.txtEmailUser.setText(user.getEmail());
        }else{
            holder.txtNumeUser.setText(null);
            holder.txtPrenumeUser.setText(null);
            holder.txtTelefon.setText(null);
            holder.txtEmailUser.setText(null);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNumeUser, txtPrenumeUser, txtTelefon,txtEmailUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNumeUser = (TextView) itemView.findViewById(R.id.nume_row_userslist_admin);
            txtPrenumeUser = (TextView) itemView.findViewById(R.id.prenume_row_userslist_admin);
            txtTelefon = (TextView) itemView.findViewById(R.id.telefon_row_userslist_admin);
            txtEmailUser = (TextView) itemView.findViewById(R.id.email_row_userslist_admin);
        }
    }
}
