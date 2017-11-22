package com.dempsey.example.marvelapp.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.dempsey.example.marvelapp.business.AppMarvelBusiness;
import com.dempsey.example.marvelapp.data.model.Comic;

import static android.support.annotation.VisibleForTesting.PACKAGE_PRIVATE;

public class ItemsListPresenter extends BasePresenter implements ItemsContract.ActionListener {

  private AppMarvelBusiness marvelBusiness;
  private ItemsContract.View view;

  @VisibleForTesting (otherwise = PACKAGE_PRIVATE)
  ItemsListPresenter(@NonNull final AppMarvelBusiness appMarvelBusiness,
                     @NonNull final ItemsContract.View view) {
    this.marvelBusiness = appMarvelBusiness;
    this.view = view;
  }

  public static ItemsListPresenter createPresenter(@NonNull final Activity activity,
                                                   @NonNull final ItemsContract.View view) {
    return new ItemsListPresenter(AppMarvelBusiness.newInstance(activity), view);
  }


  @Override
  public Comic retrieveComicsListFromStorage() {
    return marvelBusiness.retrieveComicsFromInternalStorage();
  }

  @Override
  public void refreshListOfComics() {
    view.reloadFromSplashScreen();
  }
}
