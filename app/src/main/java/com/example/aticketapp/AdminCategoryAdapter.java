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

import java.util.ArrayList;
import java.util.List;

public class AdminCategoryAdapter extends RecyclerView.Adapter<AdminCategoryAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList;
 //   private AdminCategoryAdapter.RecyclerViewInterface rvInterface;

    public AdminCategoryAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
       // this.rvInterface = rvInterface;
    }

    @NonNull
    @Override
    public AdminCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_grid_category_admin,parent,false);

        return new AdminCategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCategoryAdapter.ViewHolder holder, int position) {

        Category category = categoryList.get(position);
        holder.txtDenumire.setText(category.getDenumire());

        String imageUriCategory = null;
        imageUriCategory =  category.getImagineCategorie();
        Picasso.get().load(imageUriCategory).into(holder.imageCat);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCat;
        TextView txtDenumire;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCat = itemView.findViewById(R.id.image_design_grid_category_admin);
            txtDenumire = itemView.findViewById(R.id.denumire_design_grid_category_admin);
        }
    }
}
