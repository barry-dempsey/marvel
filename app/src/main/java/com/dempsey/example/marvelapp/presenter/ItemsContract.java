package com.dempsey.example.marvelapp.presenter;

import com.dempsey.example.marvelapp.data.model.Comic;

public interface ItemsContract {

  interface View {

  }

  interface ActionListener {

    Comic retrieveComicsListFromStorage();

  }

}
