package com.practice.now.adaptor;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.practice.now.modalClasses.Article;
import com.practice.now.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 02-03-2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private List<Article> data;
    private Context context;
    private final String TAG = "NewsAdaptor";

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

      //  Log.d("TAG",data.get(position).getDescription());

        holder.title.setText(data.get(position).getTitle());
        holder.des.setText(data.get(position).getDescription());
        Glide.with(context).load(data.get(position).getUrlToImage()).into(holder.image);
        holder.url.setText(data.get(position).getUrl());
        Log.d("Tag","News displayed in the card");

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView image;
        TextView title;
        TextView des;
        TextView url;

        public ViewHolder(View itemview) {
            super(itemview);
            cv = (CardView) itemview.findViewById(R.id.cv);
            image = (ImageView) itemview.findViewById(R.id.imagenews);
            title = (TextView) itemview.findViewById(R.id.title);
            des = (TextView) itemview.findViewById(R.id.des);
            url = (TextView) itemview.findViewById(R.id.link);
            url.setMovementMethod(LinkMovementMethod.getInstance());

        }
    }

    public NewsAdapter(Context context, ArrayList<Article> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }
}
