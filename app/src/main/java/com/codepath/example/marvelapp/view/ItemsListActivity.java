package com.codepath.example.marvelapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.widget.FrameLayout;
import com.codepath.example.marvelapp.R;
import com.codepath.example.marvelapp.data.model.Comic;
import com.codepath.example.marvelapp.presenter.BasePresenter;

public class ItemsListActivity extends BaseActivity implements ItemsListFragment.ItemSelection {

  private boolean isTwoPane = false;

  public static Intent newIntent(@NonNull final Activity from) {
    return new Intent(from, ItemsListActivity.class);
  }

  @Override
  public BasePresenter createPresenter(@NonNull Activity activity) {
    return BasePresenter.nullPresenter(activity);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_items);
    determinePaneLayout();
  }

  @Override
  protected void configureToolbar(@NonNull ActionBar actionBar) {
    actionBar.setDisplayHomeAsUpEnabled(true);
    super.configureToolbar(actionBar);
  }

  private void determinePaneLayout() {
    FrameLayout fragmentItemDetail = (FrameLayout) findViewById(R.id.flDetailContainer);
    if (fragmentItemDetail != null) {
      isTwoPane = true;
      ItemsListFragment fragmentItemsList = (ItemsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentItemsList);
      fragmentItemsList.setActivateOnItemClick(true);
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.items, menu);
    return true;
  }

  public void onItemSelected(final Comic comic) {
    if (isTwoPane) { // single activity with list and detail
      // Replace frame layout with correct detail fragment
      ItemDetailFragment fragmentItem = ItemDetailFragment.newInstance(comic);
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.flDetailContainer, fragmentItem);
      ft.commit();
    } else {
      startActivity(ItemDetailActivity.newIntent(this, comic));
    }
  }

}
