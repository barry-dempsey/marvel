package com.codepath.example.marvelapp.utils;

import android.text.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

  static boolean isEmpty(String string) {
    return string == null || string.length() == 0;
  }

  public static String trimSpaces(String str) {
    if (!TextUtils.isEmpty(str)) {
      return str.replaceAll("\\s+", " ").trim();
    }
    return "";
  }

  public static String getWordsFromString(final String arg) {
    Pattern pattern = Pattern.compile("([\\S]+\\s*){1,4}");
    Matcher matcher = pattern.matcher(arg);
    matcher.find();
    return matcher.group();
  }

}
