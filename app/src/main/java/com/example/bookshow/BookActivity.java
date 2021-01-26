package com.example.bookshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class BookActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_book);

        Bundle extras=getIntent().getExtras();
        String title="", authors="", description="" , categories ="", publishDate=""
                ,info ="", buy ="",preview ="" ,thumbnail ="";
        if (extras != null){
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            description = extras.getString("book_desc");
            categories = extras.getString("book_categories");
            publishDate = extras.getString("book_publish_date");
            info = extras.getString("book_info");
            buy = extras.getString("book_buy");
            preview = extras.getString("book_preview");
            thumbnail = extras.getString("book_thumbnail");
        }
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        TextView title1 = findViewById(R.id.aa_book_name);
        TextView authors1 = findViewById(R.id.aa_author);
        TextView desc1 = findViewById(R.id.aa_description);
        TextView catag1 = findViewById(R.id.aa_categorie);
        TextView publishDate1 = findViewById(R.id.aa_publish_date);
        TextView info1 = findViewById(R.id.aa_info);
        TextView preview1 = findViewById(R.id.aa_preview);
        ImageView thumbnail1 = findViewById(R.id.thumbnail);

        title1.setText(title);
        authors1.setText(authors);
        desc1.setText(description);
        catag1.setText(categories);
        publishDate1.setText(publishDate);

        String finalInfo = info;
        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));
                startActivity(i);
            }
        });
        String finalPreview = preview;
        preview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalPreview));
                startActivity(i);
            }
        });
        collapsingToolbarLayout.setTitle(preview);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);
        Glide.with(this).load(thumbnail).apply(requestOptions).into(thumbnail1);

    }
}
