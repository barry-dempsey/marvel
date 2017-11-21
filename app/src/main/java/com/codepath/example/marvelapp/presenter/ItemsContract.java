package com.codepath.example.marvelapp.presenter;

import com.codepath.example.marvelapp.data.model.Comic;

public interface ItemsContract {

  interface View {

  }

  interface ActionListener {

    Comic retrieveComicsListFromStorage();

  }

}
