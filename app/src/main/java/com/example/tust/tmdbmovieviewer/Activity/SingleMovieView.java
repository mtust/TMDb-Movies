package com.example.tust.tmdbmovieviewer.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tust.tmdbmovieviewer.R;


public class SingleMovieView extends Activity {
        String title;
        String description;
        Bitmap bitmap;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.singlelayout);

            Intent i = getIntent();

            title = i.getStringExtra("title");
            description = i.getStringExtra("description");
            bitmap = i.getParcelableExtra("img");


            ImageView img = (ImageView) findViewById(R.id.ItemIcon);
            TextView txttitle = (TextView) findViewById(R.id.ItemTitle);
            TextView txtDescription = (TextView) findViewById(R.id.ItemDescription);


            img.setImageBitmap(bitmap);
            txttitle.setText(title);
            txtDescription.setText(description);

        }
    }
