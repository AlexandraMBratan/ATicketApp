package com.example.aticketapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FilterReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdminUserAdapter extends RecyclerView.Adapter<AdminUserAdapter.ViewHolder> implements Filterable {

    Context context;
    List<User> userList;
    ArrayList<User> userListFull;

    public AdminUserAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
        userListFull = new ArrayList<>(userList);
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
            holder.txtNumeUser.setText("admin");
            holder.txtPrenumeUser.setText("admin");
            holder.txtTelefon.setText("admin");
            holder.txtEmailUser.setText("admin");
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public Filter getFilter() {
        return userFilter;
    }

    public void filterUserList(List<User> filteredUserList){
        this.userList = filteredUserList;
        notifyDataSetChanged();
    }

    private final Filter userFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<User> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                filteredList.addAll(userList);
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for(User item : userList){
                    if(item.getNume().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            userListFull.clear();
            userListFull.addAll((ArrayList) filterResults.values);
            notifyDataSetChanged();
        }
    };

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
