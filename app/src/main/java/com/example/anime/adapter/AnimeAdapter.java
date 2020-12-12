package com.example.anime.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.anime.R;
import com.example.anime.ui.api.model.manga.PopularResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {
    private Context context;
    public List<PopularResponse.MangaListItem> movieList = new ArrayList<>();
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    public void setMovieList(List<PopularResponse.MangaListItem> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_movie,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PopularResponse.MangaListItem item = movieList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(item.getThumb())
                .centerCrop()
                .into(holder.ivAnime);
        holder.tvTitle.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_anime)
        ImageView ivAnime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
