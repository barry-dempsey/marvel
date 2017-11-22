package com.dempsey.example.marvelapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

public class NetworkConnectivityService {

  private final ConnectivityManager connectivityManager;

  public NetworkConnectivityService(@NonNull final Context context) {
    connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
  }

  public boolean isConnectedOrConnecting() {
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
  }
}
