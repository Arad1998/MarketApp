package com.example.marketapp.adapter;

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

import com.example.marketapp.R;
import com.example.marketapp.home.SpecificNewsActivity;
import com.example.marketapp.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<NewsModel> models;

    public NewsAdapter(Context context, List<NewsModel> models){
        this.context = context;
        if (models == null)
            this.models = new ArrayList<>();
        else
            this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_adapter_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setItem(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SpecificNewsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("news_url", models.get(holder.getAdapterPosition()).getUrl());
                intent.putExtra("news_author", models.get(holder.getAdapterPosition()).getAuthor());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, description, published;
        ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            published = itemView.findViewById(R.id.published);
            newsImage = itemView.findViewById(R.id.news_image);
        }

        public void setItem(int position){
            name.setText(models.get(position).getName());
            description.setText(models.get(position).getDescription());
            published.setText(models.get(position).getPublishedAt());
            Picasso.get()
                    .load(models.get(position).getUrlToImage())
                    .into(newsImage);
        }
    }
}
