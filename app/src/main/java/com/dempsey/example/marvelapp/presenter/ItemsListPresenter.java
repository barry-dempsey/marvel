package com.dempsey.example.marvelapp.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.business.AppMarvelBusiness;
import com.dempsey.example.marvelapp.data.model.Comic;

public class ItemsListPresenter extends BasePresenter implements ItemsContract.ActionListener {

  private AppMarvelBusiness marvelBusiness;

  ItemsListPresenter(@NonNull final AppMarvelBusiness appMarvelBusiness) {
    this.marvelBusiness = appMarvelBusiness;
  }

  public static ItemsListPresenter createPresenter(@NonNull Activity activity) {
    return new ItemsListPresenter(AppMarvelBusiness.newInstance(activity));
  }


  @Override
  public Comic retrieveComicsListFromStorage() {
    return marvelBusiness.retrieveComicsFromInternalStorage();
  }
}
