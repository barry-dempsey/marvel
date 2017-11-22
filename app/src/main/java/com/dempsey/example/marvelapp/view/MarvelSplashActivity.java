package com.dempsey.example.marvelapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.R;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import com.dempsey.example.marvelapp.presenter.MarvelSplashContract;
import com.dempsey.example.marvelapp.presenter.MarvelSplashPresenter;
import java.util.ArrayList;
import java.util.List;

public class MarvelSplashActivity extends BaseActivity<MarvelSplashPresenter> implements MarvelSplashContract.View {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    retrieveContentToDisplay();
  }

  private void retrieveContentToDisplay() {
    final boolean hasStoredContent = false;//presenter.hasStoredResults();

    if (hasStoredContent) {
      //displayResults();
    } else {
      final String key = getString(R.string.appl_keyP);
      final String key2 = getString(R.string.appl_key);
      final ParameterBuilder paramBuilder = new ParameterBuilder()
          .withFirstParameter(key)
          .withSecondParameter(key2);
      presenter.retrieveListOfComics(paramBuilder);
    }
  }

  @Override
  public MarvelSplashPresenter createPresenter(@NonNull Activity activity) {
    return MarvelSplashPresenter.createPresenter(activity, this);
  }

  @Override
  public void displayResults() {
    startActivity(ItemsListActivity.newIntent(this));
  }
}
