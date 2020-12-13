package com.example.anime.ui.main.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anime.R;
import com.example.anime.adapter.AnimeAdapter;
import com.example.anime.adapter.SlidingImage_Adapter;
import com.example.anime.ui.api.model.manga.PopularResponse;
import com.example.anime.ui.main.ui.about.AboutActivity;
import com.synnapps.carouselview.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;
    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.indicator)
    CirclePageIndicator indicator;


    private List<PopularResponse.MangaListItem> listItems;
    private AnimeAdapter animeAdapter;
    private static int currentPage=0;
    private static int NUM_PAGES=0;
    private static final Integer[]IMAGES ={R.drawable.black,R.drawable.goku,R.drawable.stone};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        prepare();
        initViewModel();
        init();
        return view;
    }

    private void init() {
        for (int i =0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);
        mPager.setAdapter(new SlidingImage_Adapter(getActivity(),ImagesArray));
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
        indicator.setRadius(5*density);
        NUM_PAGES = IMAGES.length;
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage=0;
                }
                mPager.setCurrentItem(currentPage++,true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },3000,3000);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage=position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void prepare() {
        animeAdapter = new AnimeAdapter();
        rvMovie.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvMovie.setAdapter(animeAdapter);

    }

    private void initViewModel() {
        HomeViewModel homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        homeViewModel.getList().observe(getActivity(), new Observer<List<PopularResponse.MangaListItem>>() {
            @Override
            public void onChanged(List<PopularResponse.MangaListItem> items) {
                animeAdapter.setMovieList(items);
            }
        });
    }





}