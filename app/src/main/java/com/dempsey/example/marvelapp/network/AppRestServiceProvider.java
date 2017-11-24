package com.dempsey.example.marvelapp.network;

import android.content.Context;
import android.support.annotation.NonNull;

public class AppRestServiceProvider extends RestServiceProvider<RestService> {

  private static final String BASE_URL = "http://gateway.marvel.com/";

  public AppRestServiceProvider(@NonNull Context context) {
    super(context);
  }

  @Override
  protected String getServiceEndpoint() {
    return BASE_URL;
  }

  @Override
  protected Class<RestService> getServiceClass() {
    return RestService.class;
  }
}
