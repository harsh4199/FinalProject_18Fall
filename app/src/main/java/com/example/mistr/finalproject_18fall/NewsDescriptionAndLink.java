package com.example.mistr.finalproject_18fall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsDescriptionAndLink extends Activity {

    TextView desc;
    TextView lnk;
    Button saveArticle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_description_and_link);
        Intent intent = getIntent();
        String head = intent.getStringExtra("headline");
        String description = intent.getStringExtra("description");
        String link = intent.getStringExtra("link");
        Pattern pattern = Pattern.compile("<p>(.+?)</p>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(description);
        matcher.find();
        description = matcher.group(1);
        desc = findViewById(R.id.description);
        lnk = findViewById(R.id.link);
        desc.setText(description);
        lnk.setText(link);
        saveArticle = findViewById(R.id.saveArticle);
        String finalDescription = description;
        Context context = getApplicationContext();
        saveArticle.setOnClickListener(e -> {
            Intent next = new Intent(NewsDescriptionAndLink.this, ListOfArticles.class);
            next.putExtra("headline", head);
            next.putExtra("description", finalDescription);
            next.putExtra("link", link);
            Toast.makeText(context, "Article saved",
                    Toast.LENGTH_LONG).show();

        });

    }



        }




