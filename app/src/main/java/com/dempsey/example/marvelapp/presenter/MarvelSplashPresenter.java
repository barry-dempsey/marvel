package com.dempsey.example.marvelapp.presenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.dempsey.example.marvelapp.business.AppMarvelBusiness;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import com.dempsey.example.marvelapp.utils.MD5EncoderUtil;
import com.dempsey.example.marvelapp.utils.NetworkConnectivityService;
import rx.android.schedulers.AndroidSchedulers;

import static android.support.annotation.VisibleForTesting.PACKAGE_PRIVATE;

public class MarvelSplashPresenter extends BasePresenter implements MarvelSplashContract.ActionListener {

  private final MarvelSplashContract.View view;
  private final AppMarvelBusiness marvelBusiness;
  private final NetworkConnectivityService service;

  @VisibleForTesting (otherwise = PACKAGE_PRIVATE)
  MarvelSplashPresenter(@NonNull final AppMarvelBusiness marvelBusiness,
                        @NonNull final MarvelSplashContract.View view,
                        @NonNull final NetworkConnectivityService service) {
    this.marvelBusiness = marvelBusiness;
    this.view = view;
    this.service = service;
  }

  public static MarvelSplashPresenter createPresenter(@NonNull final Activity activity,
                                                      @NonNull final MarvelSplashContract.View view) {
    return new MarvelSplashPresenter(AppMarvelBusiness.newInstance(activity), view, new NetworkConnectivityService(activity));
  }

  @Override
  public boolean hasStoredResults() {
    return marvelBusiness.retrieveComicsFromInternalStorage() != null;
  }

  @Override
  public void retrieveListOfComics(@NonNull final ParameterBuilder params) {
    buildParams(params);

    if (service.isConnectedOrConnecting()) {

      AsyncTask.execute(() -> {
        marvelBusiness.getFullListOfComics(buildParams(params))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(comic -> {
              marvelBusiness.storeComicsToInternalStorage(comic);
              view.displayResults();
            });

      });

    } else {

      view.showNoNetworkError();

    }

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
