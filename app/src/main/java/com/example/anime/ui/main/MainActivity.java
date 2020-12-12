package com.example.anime.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.anime.BuildConfig;
import com.example.anime.R;
import com.example.anime.adapter.AnimeAdapter;
import com.example.anime.ui.api.ApiEndpoint;
import com.example.anime.ui.api.model.manga.PopularResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;
    private List<PopularResponse.MangaListItem> items;
    private AnimeAdapter animeAdapter;
    public static boolean loadMore = true;

    LinearLayoutManager mLayoutManager;
    private boolean isScrolling = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepare();
        loadAnime();
    }

    private void prepare() {
        mLayoutManager= new LinearLayoutManager(this);
        animeAdapter = new AnimeAdapter();
        rvMovie.setLayoutManager(new GridLayoutManager(this, 2));
        rvMovie.setAdapter(animeAdapter);
        rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                if (lastVisiblePosition==recyclerView.getChildCount()){
                    if (loadMore){
                        loadMore = false;
                        loadAnime();
                    }
                }
            }
        });


    }



    private void loadAnime() {
        AndroidNetworking.get(BuildConfig.BASE_URL + ApiEndpoint.POPULAR)
                .setPriority(Priority.MEDIUM)
                .setTag(this)
                .build()
                .getAsObject(PopularResponse.class, new ParsedRequestListener<PopularResponse>() {
                    @Override
                    public void onResponse(PopularResponse response) {
                        animeAdapter.setMovieList(response.getMangaList());
                        loadMore=true;
                    }

                    @Override
                    public void onError(ANError anError) {
                        Toast.makeText(MainActivity.this, anError.getErrorBody(), Toast.LENGTH_SHORT).show();

                    }
                });

    }
}