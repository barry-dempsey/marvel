package com.dempsey.example.marvelapp.presenter;

import com.dempsey.example.marvelapp.data.model.Comic;

public interface ItemsContract {

  interface View {

    void reloadFromSplashScreen();

  }

  interface ActionListener {

    Comic retrieveComicsListFromStorage();

    void refreshListOfComics();

  }

}
