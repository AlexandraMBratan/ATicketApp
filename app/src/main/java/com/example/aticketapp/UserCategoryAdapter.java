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

import java.util.List;

public class UserCategoryAdapter extends RecyclerView.Adapter<UserCategoryAdapter.ViewHolder>{
    Context context;
    List<Category> categoryList;
    private UserCategoryAdapter.RecyclerViewInterfaceCategory rvInterfaceCategoryUser;

    public UserCategoryAdapter(Context context, List<Category> categoryList, UserCategoryAdapter.RecyclerViewInterfaceCategory rvInterfaceCategoryUser) {
        this.context = context;
        this.categoryList = categoryList;
        this.rvInterfaceCategoryUser = rvInterfaceCategoryUser;
    }

    @NonNull
    @Override
    public UserCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_grid_category_user, parent, false);

        return new UserCategoryAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserCategoryAdapter.ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.txtDenumireCat.setText(category.getDenumire());

        String imageUriCategory = null;
        imageUriCategory = category.getImagineCategorie();
        Picasso.get().load(imageUriCategory).into(holder.imageCatUser);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Context contextCat = view.getContext();
                Intent i = new Intent(view.getContext(), AdminEventsPageActivity.class);
                i.putExtra("denumire", category.getDenumire());
                context.startActivity(i);
                Toast.makeText(context, "Test "+category.getDenumire(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageCatUser;
        TextView txtDenumireCat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageCatUser = itemView.findViewById(R.id.image_design_grid_category_user);
            txtDenumireCat = itemView.findViewById(R.id.denumire_design_grid_category_user);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            rvInterfaceCategoryUser.onItemClick(view, getAbsoluteAdapterPosition());
        }
    }
    public interface RecyclerViewInterfaceCategory{
        void onItemClick (View v, int position);
    }
}
