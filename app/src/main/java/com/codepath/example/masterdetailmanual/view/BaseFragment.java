package com.codepath.example.masterdetailmanual.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import com.codepath.example.masterdetailmanual.presenter.BasePresenter;

public abstract class BaseFragment<Presenter extends BasePresenter> extends Fragment {

  protected Presenter presenter;

  public abstract Presenter createPresenter(@NonNull final Activity activity);

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    presenter = createPresenter(getActivity());
  }
}
