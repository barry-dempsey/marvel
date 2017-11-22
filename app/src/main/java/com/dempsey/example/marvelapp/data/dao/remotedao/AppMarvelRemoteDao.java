package com.dempsey.example.marvelapp.data.dao.remotedao;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import com.dempsey.example.marvelapp.network.AppRestServiceProvider;
import com.dempsey.example.marvelapp.network.BaseResponseProcessor;
import com.dempsey.example.marvelapp.network.RestService;
import retrofit2.Call;

import static android.support.annotation.VisibleForTesting.PACKAGE_PRIVATE;

public class AppMarvelRemoteDao implements MarvelRemoteDao {

  private static final String LIMIT = "40";
  private final AppRestServiceProvider serviceProvider;

  @VisibleForTesting (otherwise = PACKAGE_PRIVATE)
  AppMarvelRemoteDao(AppRestServiceProvider serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  public static AppMarvelRemoteDao newInstance(@NonNull final Activity activity) {
    return new AppMarvelRemoteDao(new AppRestServiceProvider(activity));
  }

  @Override
  public Comic getAllComics(@NonNull ParameterBuilder param) {
    final RestService restService = serviceProvider.getService();
    Call<Comic> call = restService.getListOfComicForCharacter(param.getCharacterId(),
        param.getDateParameter(), param.getParameterTwo(), LIMIT, param.getEncodedParameter());

    try {
      return BaseResponseProcessor.process(call);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;

  }
}
