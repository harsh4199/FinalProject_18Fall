package com.example.mistr.finalproject_18fall;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ImageButton cbc = (ImageButton) findViewById(R.id.img_cbc);
        Drawable d1 = Drawable.createFromPath("@drawable/cbc");
        cbc.setImageDrawable(d1);


        final ImageButton food = (ImageButton) findViewById(R.id.img_food);
        Drawable d2 = Drawable.createFromPath("@drawable/cbc");
        food.setImageDrawable(d2);

        final ImageButton movie = (ImageButton) findViewById(R.id.img_movie);
        Drawable d3 = Drawable.createFromPath("@drawable/cbc");
        movie.setImageDrawable(d3);

    }

}
