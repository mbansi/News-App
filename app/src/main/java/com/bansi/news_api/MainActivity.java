package com.bansi.news_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bansi.news_api.model.Articles;
import com.bansi.news_api.model.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    final String API_KEY = "cd5daf62d7e7417c9d45ffd840aec2b8";
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    Adapter adapter = new Adapter();
    List<Articles> articles = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.rvNewsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        String country = "in";

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson(country,API_KEY);
            }
        });
        retrieveJson(country,API_KEY);
    }

    public void retrieveJson(String country, String apiKey){
        swipeRefreshLayout.setRefreshing(true);
        Call<Headlines> call = APIClient.getInstance().getAPI().getHeadlines(country,apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles() != null){
                    swipeRefreshLayout.setRefreshing(false);
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new Adapter(MainActivity.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public String getCountry(){

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        Log.d("Doing::","getcountry()"  + country );
        return country.toLowerCase();
    }
}