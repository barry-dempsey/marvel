package com.codepath.example.masterdetailmanual.presenter;

import com.codepath.example.masterdetailmanual.data.model.Comic;

public interface ItemsContract {

  interface View {

  }

  interface ActionListener {

    Comic retrieveComicsListFromStorage();

  }

}
