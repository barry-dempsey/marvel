package com.codepath.example.marvelapp.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.codepath.example.marvelapp.R;
import com.codepath.example.marvelapp.data.model.Comic;
import com.codepath.example.marvelapp.utils.RandomImageProvider;
import com.codepath.example.marvelapp.utils.StringUtil;

public class ItemDetailFragment extends Fragment {

  public static final String COMIC_ARG = "itemArg";
  private RandomImageProvider imageProvider;
  private Comic comic;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    comic = (Comic) getArguments().getSerializable(COMIC_ARG);
    imageProvider = RandomImageProvider.newInstance();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.fragment_item_detail, container, false);
    initialiseViews(view);
    return view;
  }

  private void initialiseViews(final View view) {
    ((TextView) view.findViewById(R.id.tvTitle)).setText(StringUtil.getWordsFromString(comic.getDescription()));
    ((TextView) view.findViewById(R.id.tvBody)).setText(comic.getDescription());
    ImageView imageView = (ImageView) view.findViewById(R.id.main_image);
    imageView.setImageDrawable(imageProvider.provideImage(getActivity()));
  }

  public static ItemDetailFragment newInstance(Comic item) {
    ItemDetailFragment fragmentDemo = new ItemDetailFragment();
    Bundle args = new Bundle();
    args.putSerializable(COMIC_ARG, item);
    fragmentDemo.setArguments(args);
    return fragmentDemo;
  }
}
