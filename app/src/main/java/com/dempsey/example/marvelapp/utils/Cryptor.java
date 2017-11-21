package com.dempsey.example.marvelapp.utils;

import android.provider.Settings;
import com.scottyab.aescrypt.AESCrypt;
import java.security.GeneralSecurityException;

public class Cryptor {

  public String applyEncoding(final String dataString) {
    try {
      if (!StringUtil.isEmpty(dataString)) {
        return AESCrypt.encrypt(Settings.Secure.ANDROID_ID, dataString);
      }
    } catch (GeneralSecurityException e) {
      //Crashlytics.logException(e);//TODO

    }
    return dataString;
  }

  public String applyDecoding(String stringData) {
    try {
      if (!StringUtil.isEmpty(stringData)) {
        return AESCrypt.decrypt(Settings.Secure.ANDROID_ID, stringData);
      }
    } catch (GeneralSecurityException e) {
      //Crashlytics.logException(e);//TODO
    }
    return stringData;
  }

}

