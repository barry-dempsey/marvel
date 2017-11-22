package com.dempsey.example.marvelapp.utils;

import android.support.annotation.NonNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class MD5EncoderUtil {

  public static MD5EncoderUtil newInstance() {
    return new MD5EncoderUtil();
  }

  @NonNull
  public String paramGenerator(@NonNull String... params) {
    try {
      final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
      final String args = String.format("%s%s%s", dateNow(), params[0], params[1]);
      messageDigest.update(args.getBytes());
      final byte[] byteDigest = messageDigest.digest();
      return convertByteToHex(byteDigest);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return "";
  }

  private String convertByteToHex(byte[] byteData) {

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < byteData.length; i++) {
      sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
    }

    return sb.toString();
  }

  public String dateNow() {
    return DateTime.now().withZone(DateTimeZone.UTC).toString("yyyyMMddHHmm");
  }



}
