package com.example.mistr.finalproject_18fall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button button1 = findViewById(R.id.cbc);
    Button button2 = findViewById(R.id.food);
    Button button3 = findViewById(R.id.movie);
    button1.setOnClickListener(e -> {
      Intent nextScreen = new Intent(MainActivity.this, NewsArticle.class);
      startActivityForResult(nextScreen, 444);
    });




  }
}