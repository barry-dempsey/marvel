package com.codepath.example.masterdetailmanual.network;

import android.content.Context;
import android.support.annotation.NonNull;

public class AppRestServiceProvider extends RestServiceProvider<RestService> {

  public AppRestServiceProvider(@NonNull Context context) {
    super(context);
  }

  @Override
  protected String getServiceEndpoint() {
    return "http://gateway.marvel.com/";
  }

  @Override
  protected Class<RestService> getServiceClass() {
    return RestService.class;
  }
}
