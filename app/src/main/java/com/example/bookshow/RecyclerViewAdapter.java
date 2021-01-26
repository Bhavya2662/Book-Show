package com.example.bookshow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context context;
    private List<Book> data;
    public RecyclerViewAdapter(Context context, List<Book> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.book_raw_item,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(context, BookActivity.class);
                int position = viewHolder.getAdapterPosition();
                i.putExtra("book_title" ,data.get(position).getTitle());
                i.putExtra("book_author" ,data.get(position).getAuthors());
                i.putExtra("book_desc" ,data.get(position).getDescription());
                i.putExtra("book_categories" ,data.get(position).getCategories());
                i.putExtra("book_publish_date" ,data.get(position).getPublishedDate());
                i.putExtra("book_info" ,data.get(position).getUrl());
                i.putExtra("book_buy" ,data.get(position).getBuy());
                i.putExtra("book_preview" ,data.get(position).getPreview());
                i.putExtra("book_thumbnail" ,data.get(position).getThumbnail());

                context.startActivity(i);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book= data.get(position);
        holder.title.setText(book.getTitle());
        holder.author.setText(book.getAuthors());
        holder.category.setText(book.getCategories());
        holder.price.setText(book.getPrice());
        Glide.with(holder.thumbnail.getContext()).load(book.getThumbnail()).into(holder.thumbnail);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView title;
        TextView category;
        TextView price;
        TextView author;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            price = itemView.findViewById(R.id.price);
            author = itemView.findViewById(R.id.author);
            linearLayout = itemView.findViewById(R.id.container);
        }

        public ImageView getThumbnail() {
            return thumbnail;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getCategory() {
            return category;
        }

        public TextView getPrice() {
            return price;
        }

        public TextView getAuthor() {
            return author;
        }
    }
}
