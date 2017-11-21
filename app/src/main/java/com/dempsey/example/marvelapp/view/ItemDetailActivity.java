package com.dempsey.example.marvelapp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import com.dempsey.example.marvelapp.R;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.presenter.BasePresenter;

public class ItemDetailActivity extends BaseActivity {

	private static final String ITEM_EXTRA = "itemExtra";
	private ItemDetailFragment fragmentItemDetail;
	private Comic item;

	public static Intent newIntent(@NonNull Activity from, @NonNull final Comic comic) {
		return new Intent(from, ItemDetailActivity.class).putExtra(ITEM_EXTRA, comic);
	}

	@Override
	public BasePresenter createPresenter(@NonNull Activity activity) {
		return BasePresenter.nullPresenter(activity);
	}

	@Override
	protected void configureToolbar(@NonNull ActionBar actionBar) {
		super.configureToolbar(actionBar);
		actionBar.setTitle(item.getName() != null ? item.getName() : item.getDescription());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		item = (Comic) getIntent().getSerializableExtra(ITEM_EXTRA);
		setContentView(R.layout.activity_item_detail);

		if (savedInstanceState == null) {
			fragmentItemDetail = ItemDetailFragment.newInstance(item);
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.flDetailContainer, fragmentItemDetail);
			ft.commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.item_detail, menu);
		return true;
	}

}
