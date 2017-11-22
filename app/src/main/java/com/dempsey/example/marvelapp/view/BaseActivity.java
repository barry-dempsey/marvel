package com.dempsey.example.marvelapp.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import com.dempsey.example.marvelapp.R;
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

  @Override
  public void setContentView(@LayoutRes final int layoutResID) {
    super.setContentView(layoutResID);
    setUpToolbar();
  }

  @Override
  public void setContentView(final View view) {
    super.setContentView(view);
    setUpToolbar();
  }

  @Override
  public void setContentView(final View view, final ViewGroup.LayoutParams params) {
    super.setContentView(view, params);
    setUpToolbar();
  }

  private void setUpToolbar() {
    final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (toolbar != null) {
      setSupportActionBar(toolbar);
    }
    final ActionBar actionBar = getSupportActionBar();
    if (actionBar != null) {
      configureToolbar(actionBar);
    }
  }

}
