package com.dempsey.example.marvelapp.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.dempsey.example.marvelapp.business.AppMarvelBusiness;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import com.dempsey.example.marvelapp.utils.MD5EncoderUtil;
import rx.android.schedulers.AndroidSchedulers;

import static android.support.annotation.VisibleForTesting.PACKAGE_PRIVATE;

public class MarvelSplashPresenter extends BasePresenter implements MarvelSplashContract.ActionListener {

  private MarvelSplashContract.View view;
  private AppMarvelBusiness marvelBusiness;

  @VisibleForTesting (otherwise = PACKAGE_PRIVATE)
  MarvelSplashPresenter(@NonNull final AppMarvelBusiness marvelBusiness, @NonNull final MarvelSplashContract.View view) {
    this.marvelBusiness = marvelBusiness;
    this.view = view;
  }

  public static MarvelSplashPresenter createPresenter(@NonNull final Activity activity,
                                                @NonNull final MarvelSplashContract.View view) {
    return new MarvelSplashPresenter(AppMarvelBusiness.newInstance(activity), view);
  }

  @Override
  public boolean hasStoredResults() {
    return marvelBusiness.retrieveComicsFromInternalStorage().getComics().getComics().size() > 0;
  }

  @Override
  public void retrieveListOfComics(@NonNull final ParameterBuilder params) {
    buildParams(params);

    AsyncTask.execute(() -> {
      marvelBusiness.getFullListOfComics(buildParams(params))
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(comic -> {
            marvelBusiness.storeComicsToInternalStorage(comic);
            view.displayResults();
          });
    });
  }

  @VisibleForTesting (otherwise = PACKAGE_PRIVATE)
  ParameterBuilder buildParams(final ParameterBuilder paramBuilder) {
    final MD5EncoderUtil encoderUtil = MD5EncoderUtil.newInstance();
    final String encodedParam = encoderUtil.paramGenerator(paramBuilder.getParameterOne(), paramBuilder.getParameterTwo());
    paramBuilder.withEncodedParameter(encodedParam);
    paramBuilder.withDateParameter(encoderUtil.dateNow());
    return paramBuilder;
  }

}
