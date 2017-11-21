package com.codepath.example.masterdetailmanual.presenter;

import android.content.Context;
import android.support.annotation.NonNull;

public abstract class BasePresenter {

  @NonNull
  public static BasePresenter nullPresenter(@NonNull final Context context) {
    return new BasePresenter() {};
  }

}
