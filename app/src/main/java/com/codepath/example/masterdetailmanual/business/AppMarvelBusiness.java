package com.codepath.example.masterdetailmanual.business;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.codepath.example.masterdetailmanual.data.dao.remotedao.AppMarvelLocalDao;
import com.codepath.example.masterdetailmanual.data.dao.remotedao.AppMarvelRemoteDao;
import com.codepath.example.masterdetailmanual.data.model.Comic;
import com.codepath.example.masterdetailmanual.data.model.Comics;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

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

  @NonNull
  private String paramGenerator(@NonNull String... params) {
    try {
      final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      final String now = DateTime.now().withZone(DateTimeZone.UTC).toString("yyyyMMddHHmm");
      final String args = String.format("%s%s%s", now, params[0], params[1]);
      messageDigest.update(args.getBytes());
      final byte[] byteDigest = messageDigest.digest();
      return convertByteToHex(byteDigest);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  @Override
  public Comic getFullListOfCharacters(@NonNull final String... param) {
    return remoteDao.getAllCharacters(paramGenerator(param), param);
  }

  @Override
  public void storeComicsToInternalStorage(@NonNull Comic comics) {
    localDao.storeToInternal(comics);
  }

  @Override
  public Comic retrieveComicsFromInternalStorage() {
    return localDao.retrieveFromStorage();
  }

  private String convertByteToHex(byte[] byteData) {

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }

    return sb.toString();
  }
}
