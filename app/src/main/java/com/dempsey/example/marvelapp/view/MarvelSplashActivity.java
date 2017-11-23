package com.dempsey.example.marvelapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.dempsey.example.marvelapp.R;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import com.dempsey.example.marvelapp.presenter.MarvelSplashContract;
import com.dempsey.example.marvelapp.presenter.MarvelSplashPresenter;;

public class MarvelSplashActivity extends BaseActivity<MarvelSplashPresenter> implements MarvelSplashContract.View {

  private static final String CHARACTER_ID = "1009268";

  public static Intent newInstance(final FragmentActivity from) {
    return new Intent(from, MarvelSplashActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    retrieveContentToDisplay();
  }

  private void retrieveContentToDisplay() {
    final boolean hasStoredContent = presenter.hasStoredResults();

    if (hasStoredContent) {
      showListOfComics();
    } else {

      final String key = getString(R.string.appl_keyP);
      final String key2 = getString(R.string.appl_key);
      final ParameterBuilder paramBuilder = new ParameterBuilder()
          .withCharacterId(CHARACTER_ID)
          .withFirstParameter(key)
          .withSecondParameter(key2);

      presenter.retrieveListOfComics(paramBuilder);
    }
  }

  private void showListOfComics() {
    startActivity(ItemsListActivity.newIntent(this));
  }

  @Override
  public MarvelSplashPresenter createPresenter(@NonNull Activity activity) {
    return MarvelSplashPresenter.createPresenter(activity, this);
  }

  @Override
  public void displayResults() {
    startActivity(ItemsListActivity.newIntent(this));
  }

  @Override
  public void showNoNetworkError() {
    Toast.makeText(this, R.string.connectivity_warning, Toast.LENGTH_LONG).show();

    try {
      Thread.sleep(3000);//TODO

      if (presenter.hasStoredResults()) {
        displayResults();
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
