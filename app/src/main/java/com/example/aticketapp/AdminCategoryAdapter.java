package com.example.aticketapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdminCategoryAdapter extends RecyclerView.Adapter<AdminCategoryAdapter.ViewHolder> {

    Context context;
    List<Category> categoryList;
    private AdminCategoryAdapter.RecyclerViewInterfaceCategory rvInterfaceCategory;

    public AdminCategoryAdapter(Context context, List<Category> categoryList, RecyclerViewInterfaceCategory rvInterfaceCategory) {
        this.context = context;
        this.categoryList = categoryList;
        this.rvInterfaceCategory = rvInterfaceCategory;
    }

    @NonNull
    @Override
    public AdminCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_grid_category_admin, parent, false);

        return new AdminCategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminCategoryAdapter.ViewHolder holder, int position) {

        Category category = categoryList.get(position);
        holder.txtDenumire.setText(category.getDenumire());

        String imageUriCategory = null;
        imageUriCategory = category.getImagineCategorie();
        Picasso.get().load(imageUriCategory).into(holder.imageCat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Context contextCat = view.getContext();
                Intent i = new Intent(view.getContext(), AdminEventsPageActivity.class);
                i.putExtra("denumire", category.getDenumire());
                context.startActivity(i);
                Toast.makeText(context, "Test"+category.getDenumire(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageCat;
        TextView txtDenumire;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCat = itemView.findViewById(R.id.image_design_grid_category_admin);
            txtDenumire = itemView.findViewById(R.id.denumire_design_grid_category_admin);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            rvInterfaceCategory.onItemClick(view, getAbsoluteAdapterPosition());
        }
    }

    public interface RecyclerViewInterfaceCategory{
        void onItemClick (View v, int position);
    }
}