package com.example.anime.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int PRIVATE_MODE =0;

    private static final String PREF_NAME = "movie database";
    private static final String IS_FIRST_TIME_LAUNCH = "Is First Time";
    private static final String IS_MENU = "Is First Time";


    public PrefManager(Context context){
        this.context =context;
        pref = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor =pref.edit();
    }
    public void setFirsTimeLaunch(boolean isFirstTIme){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirstTIme);
        editor.commit();
    }
    public boolean isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }

    public void setIsMenu(boolean isMenu){
        editor.putBoolean(IS_MENU,isMenu).apply();
    }

    public boolean getIsMenu(){
        return pref.getBoolean(IS_MENU,false);
    }


}
