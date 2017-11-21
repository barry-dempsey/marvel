package com.dempsey.example.marvelapp.view;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.dempsey.example.marvelapp.R;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.presenter.ItemsListPresenter;

public class ItemsListFragment extends BaseFragment<ItemsListPresenter> {

	private Comic comics;
	private ItemSelection listener;
	private ItemsAdapter adapter;

	public interface ItemSelection {
		void onItemSelected(Comic comic);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		if (activity instanceof ItemSelection) {
			listener = (ItemSelection) activity;
		} else {
			throw new ClassCastException(activity.toString()
					+ " must implement ItemsListFragment.OnItemSelectedListener");
		}
	}

	@Override
	public ItemsListPresenter createPresenter(@NonNull Activity activity) {
		return ItemsListPresenter.createPresenter(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		comics = presenter.retrieveComicsListFromStorage();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_items_list, container,
				false);
		final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
		adapter = new ItemsAdapter(getActivity(), listener);
		final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
		recyclerView.setLayoutManager(manager);
		recyclerView.setAdapter(adapter);
		return view;
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		adapter.loadResults(comics.getComics().getComics());
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick) {

	}

}
