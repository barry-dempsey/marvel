package com.dempsey.example.marvelapp.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.business.AppMarvelBusiness;
import com.dempsey.example.marvelapp.data.model.Comic;
import java.util.List;

public class MarvelSplashPresenter extends BasePresenter implements MarvelContract.ActionListener {

  private MarvelContract.View view;
  private AppMarvelBusiness marvelBusiness;

  MarvelSplashPresenter(@NonNull final AppMarvelBusiness marvelBusiness, @NonNull final MarvelContract.View view) {
    this.marvelBusiness = marvelBusiness;
    this.view = view;
  }

  public static MarvelSplashPresenter createPresenter(@NonNull final Activity activity,
                                                @NonNull final MarvelContract.View view) {
    return new MarvelSplashPresenter(AppMarvelBusiness.newInstance(activity), view);
  }

  @Override
  public boolean hasStoredResults() {
    final List<Comic> storedComicList = marvelBusiness.retrieveComicsFromInternalStorage().getComics().getComics();
    return storedComicList != null || storedComicList.size() > 0;
  }

  @Override
  public void retrieveListOfCharacters(@NonNull final String... params) {
    new MarvelCharacterFetchTask().execute(params);
  }

  private class MarvelCharacterFetchTask extends AsyncTask<String, Void, Comic> {

    @Override
    protected Comic doInBackground(String... strings) {
      return marvelBusiness.getFullListOfCharacters(strings);
    }

    @Override
    protected void onPostExecute(final Comic result) {
      super.onPostExecute(result);
      marvelBusiness.storeComicsToInternalStorage(result);
      view.displayResults();
    }
  }

}
