package com.bansi.news_api;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailedActivity extends AppCompatActivity {

    TextView tvTitle,tvSource,tvDate,tvDescription;
    ProgressBar loader;
    ImageView imageView;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        getSupportActionBar().hide();


        tvTitle = findViewById(R.id.tvTitle);
        tvSource = findViewById(R.id.tvSource);
        tvDate = findViewById(R.id.tvDate);
        tvDescription = findViewById(R.id.tvDesc);

        imageView = findViewById(R.id.imageview);
        webView = findViewById(R.id.webview);
        loader = findViewById(R.id.webviewLoader);
        loader.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String source = intent.getStringExtra("source");
        String date = intent.getStringExtra("time");
        String imageUrl = intent.getStringExtra("imageUrl");
        String url = intent.getStringExtra("url");
        String description = intent.getStringExtra("description");

        tvTitle.setText(title);
        tvSource.setText(source);
        tvDate.setText(date);
        tvDescription.setText(description);

        Picasso.get().load(imageUrl).into(imageView);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        if (webView.isShown()){
            loader.setVisibility(View.INVISIBLE);
        }

    }



}