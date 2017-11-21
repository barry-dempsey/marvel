package com.codepath.example.masterdetailmanual.data.dao.remotedao;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.codepath.example.masterdetailmanual.data.model.Comic;
import com.codepath.example.masterdetailmanual.network.AppRestServiceProvider;
import com.codepath.example.masterdetailmanual.network.BaseResponseProcessor;
import com.codepath.example.masterdetailmanual.network.RestService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import retrofit2.Call;

public class AppMarvelRemoteDao implements MarvelRemoteDao {

  private final AppRestServiceProvider serviceProvider;

  AppMarvelRemoteDao(AppRestServiceProvider serviceProvider) {
    this.serviceProvider = serviceProvider;
  }

  public static AppMarvelRemoteDao newInstance(@NonNull final Activity activity) {
    return new AppMarvelRemoteDao(new AppRestServiceProvider(activity));
  }

  @Override
  public Comic getAllCharacters(@NonNull final String param, @NonNull final String... params) {
    final String now = DateTime.now().withZone(DateTimeZone.UTC).toString("yyyyMMddHHmm");
    final RestService restService = serviceProvider.getService();
    final String secondParam = params[1];
    Call<Comic> listCall = restService.getListOfComicForCharacter("1009268", now, secondParam, "40", param);
    try {
      return BaseResponseProcessor.process(listCall);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;

  }
}
