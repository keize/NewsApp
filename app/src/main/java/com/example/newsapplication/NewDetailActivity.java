package com.example.newsapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class NewDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title, date, content;
    private String mUrl, mImg, mTitle, mDate, mSource, mAuthor, contentText;
    public static final String DATE_PATTERN = "EEE, d MMM HH:mm";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_detail);


        imageView = findViewById(R.id.backdrop);
        date = findViewById(R.id.date);
        title = findViewById(R.id.title);
        content = findViewById(R.id.content);


        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mImg = intent.getStringExtra("img");
        mTitle = intent.getStringExtra("title");
        mDate = intent.getStringExtra("date");
        mSource = intent.getStringExtra("source");
        mAuthor = intent.getStringExtra("author");
        contentText = intent.getStringExtra("content");

        title.setText(mTitle);
        content.setText(contentText);
        date.setText(dateFormat.format(new Date()));


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(Utils.getRandomDrawbleColor());

        Glide.with(this)
                .load(mImg)
                .apply(requestOptions)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);

    }

    final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN, Locale.getDefault());

    public void readMore(View view) {
        Intent browserIntent = new
                Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
        startActivity(browserIntent);
    }
}
