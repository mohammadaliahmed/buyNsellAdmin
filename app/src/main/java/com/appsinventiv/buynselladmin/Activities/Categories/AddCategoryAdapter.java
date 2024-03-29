package com.appsinventiv.buynselladmin.Activities.Categories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.appsinventiv.buynselladmin.R;

import java.util.ArrayList;

/**
 * Created by AliAh on 27/11/2018.
 */

public class AddCategoryAdapter extends RecyclerView.Adapter<AddCategoryAdapter.ViewHolder> {
    Context context;
    ArrayList<String> list;

    public AddCategoryAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false);
        AddCategoryAdapter.ViewHolder viewHolder = new AddCategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String title = list.get(position);
        holder.title.setText(title);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddSubCategories.class);
                i.putExtra("parentCategory", title);
                context.startActivity(i);

            }
        }); holder.addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AddSubCategories.class);
                i.putExtra("parentCategory", title);
                context.startActivity(i);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView addCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            addCategory = itemView.findViewById(R.id.addCategory);
            title = itemView.findViewById(R.id.title);
        }
    }

}
