package com.example.anime.ui.main.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.anime.BuildConfig;
import com.example.anime.ui.api.ApiEndpoint;
import com.example.anime.ui.api.model.manga.PopularResponse;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<PopularResponse.MangaListItem>> mListAnime;

    public LiveData<List<PopularResponse.MangaListItem>>getList(){
        if (mListAnime == null) {
            mListAnime = new MutableLiveData<>();
            loadAnime();
        }
        return mListAnime;
    }

    private void loadAnime() {
        AndroidNetworking.get(BuildConfig.BASE_URL + ApiEndpoint.POPULAR)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsObject(PopularResponse.class, new ParsedRequestListener<PopularResponse>() {
                    @Override
                    public void onResponse(PopularResponse response) {
                        List<PopularResponse.MangaListItem> dataItemList =response.getMangaList();
                        mListAnime.postValue(dataItemList);
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}
