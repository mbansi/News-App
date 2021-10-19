package com.bansi.news_api;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bansi.news_api.model.Articles;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles> articles;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    public Adapter() {

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.items, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles a = articles.get(position);
        holder.title.setText(a.getTitle());
        holder.source.setText(a.getSource().getName());
        holder.date.setText(dateTime(a.getPublishedAt()));

        String imageURL = a.getUrlToImage();
        Picasso.get().load(imageURL).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,source,date;
        CardView card;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            source = itemView.findViewById(R.id.tvSource);
            date = itemView.findViewById(R.id.tvDate);
            card = itemView.findViewById(R.id.cardview);
            image = itemView.findViewById(R.id.imageview);

        }
    }

    public String  dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }
        catch (ParseException e ){
            e.printStackTrace();
        }
        return time;
    }

    public String getCountry(){

        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        Log.d("Doing::","getcountry()"  + country );
        return country.toLowerCase();
    }
}
