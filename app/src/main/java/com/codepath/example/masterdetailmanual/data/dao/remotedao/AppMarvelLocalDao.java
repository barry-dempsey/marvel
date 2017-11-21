package com.codepath.example.masterdetailmanual.data.dao.remotedao;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import com.codepath.example.masterdetailmanual.data.model.Comic;
import com.codepath.example.masterdetailmanual.data.model.Comics;
import com.google.gson.Gson;

public class AppMarvelLocalDao implements MarvelLocalDao {

  private static final String COMIC_ITEMS_PREFS = "comicItemsPrefs";
  private static final String COMIC_ITEMS_KEY = "comicsKey";
  private final SharedPreferences preferences;

  AppMarvelLocalDao(SharedPreferences preferences) {
    this.preferences = preferences;
  }

  public static AppMarvelLocalDao newInstance(@NonNull final Context context) {
    return new AppMarvelLocalDao(context.getSharedPreferences(COMIC_ITEMS_PREFS, Context.MODE_PRIVATE));
  }
  
  @Override
  public void storeToInternal(@NonNull Comic comics) {
    preferences.edit().putString(COMIC_ITEMS_KEY, new Gson().toJson(comics)).apply();
  }

  @Override
  public Comic retrieveFromStorage() {
    final String comicsStringJson = preferences.getString(COMIC_ITEMS_KEY, null);
    return new Gson().fromJson(comicsStringJson, Comic.class);
  }
}
