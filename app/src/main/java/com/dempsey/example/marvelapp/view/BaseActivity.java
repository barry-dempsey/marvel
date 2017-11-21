package com.dempsey.example.marvelapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import com.dempsey.example.marvelapp.presenter.BasePresenter;

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {

  protected Presenter presenter;

  public abstract Presenter createPresenter(@NonNull final Activity activity);

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter = createPresenter(this);
  }

  @SuppressWarnings("UnusedParameters")
  protected void configureToolbar(@NonNull final ActionBar actionBar) {
  }

}
