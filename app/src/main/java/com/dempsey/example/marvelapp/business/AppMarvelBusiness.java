package com.dempsey.example.marvelapp.business;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.data.dao.remotedao.AppMarvelLocalDao;
import com.dempsey.example.marvelapp.data.dao.remotedao.AppMarvelRemoteDao;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import rx.Observable;

public class AppMarvelBusiness implements MarvelBusiness {

  private final AppMarvelRemoteDao remoteDao;
  private final AppMarvelLocalDao localDao;

  AppMarvelBusiness(@NonNull final AppMarvelRemoteDao remoteDao, @NonNull final AppMarvelLocalDao localDao) {
    this.remoteDao = remoteDao;
    this.localDao = localDao;
  }

  public static AppMarvelBusiness newInstance(@NonNull final Activity activity) {
    return new AppMarvelBusiness(AppMarvelRemoteDao.newInstance(activity), AppMarvelLocalDao.newInstance(activity));
  }

  @Override
  public Observable<Comic> getFullListOfComics(@NonNull ParameterBuilder paramBuilder) {
    return Observable.just(remoteDao.getAllComics(paramBuilder));
  }

  @Override
  public void storeComicsToInternalStorage(@NonNull Comic comics) {
    deleteAllFromStorage();
    localDao.storeToInternal(comics);
  }

  @Override
  public Comic retrieveComicsFromInternalStorage() {
    return localDao.retrieveFromStorage();
  }

  @Override
  public void deleteAllFromStorage() {
    localDao.deleteAllFromStorage();
  }

}
