package com.dempsey.example.marvelapp.presenter;

import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;

public interface MarvelSplashContract {

  interface View {

    void displayResults();

    void showNoNetworkError();

  }

  interface ActionListener {

    boolean hasStoredResults();

    void retrieveListOfComics(@NonNull final ParameterBuilder paramBuilder);

  }
}
