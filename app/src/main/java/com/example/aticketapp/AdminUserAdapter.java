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

    private Context context;
    private List<User> userList;
    private ArrayList<User> userListFull;
    private RecyclerViewInterface rvInterface;

    public AdminUserAdapter(Context context, List<User> userList, RecyclerViewInterface rvInterface) {
        this.context = context;
        this.userList = userList;
        userListFull = new ArrayList<>(userList);
        this.rvInterface = rvInterface;
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
        holder.txtNumeUser.setText(user.getNume());
        holder.txtPrenumeUser.setText(user.getPrenume());
        holder.txtTelefon.setText(user.getTelefon());
        holder.txtEmailUser.setText(user.getEmail());
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtNumeUser, txtPrenumeUser, txtTelefon,txtEmailUser;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNumeUser = (TextView) itemView.findViewById(R.id.nume_row_userslist_admin);
            txtPrenumeUser = (TextView) itemView.findViewById(R.id.prenume_row_userslist_admin);
            txtTelefon = (TextView) itemView.findViewById(R.id.telefon_row_userslist_admin);
            txtEmailUser = (TextView) itemView.findViewById(R.id.email_row_userslist_admin);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            rvInterface.onItemClick(view, getAbsoluteAdapterPosition());
        }
    }

    public interface RecyclerViewInterface{
        void onItemClick (View v, int position);
    }
}
