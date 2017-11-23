package com.dempsey.example.marvelapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.dempsey.example.marvelapp.R;
import com.dempsey.example.marvelapp.utils.StringUtil;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final ItemsListFragment.ItemSelection listener;
  private final Context context;
  private List<Comic> comics;

  ItemsAdapter(final Context context, final ItemsListFragment.ItemSelection listener) {
    this.comics = new ArrayList<>();
    this.listener = listener;
    this.context = context;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    final View view = inflater.inflate(R.layout.list_item, parent, false);
    return new SuperHeroViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    final Comic comic = comics.get(position);
    ((SuperHeroViewHolder) holder).bind(comic);
  }

  void loadResults(@NonNull final List<Comic> comics) {
    this.comics = comics;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return comics.size();
  }

  private class SuperHeroViewHolder extends RecyclerView.ViewHolder {

    private View rootView;
    private TextView labelTextView;
    private ImageView imageIcon;

    SuperHeroViewHolder(View view) {
      super(view);
      rootView = view;
      labelTextView = (TextView) view.findViewById(R.id.character_result_item_name);
      imageIcon = (ImageView) view.findViewById(R.id.image_icon);
    }

    void bind(@NonNull final Comic comic) {
      final String storyName = comic.getStories().getItems().get(1).getName();
      labelTextView.setText(StringUtil.removeUnwantedString(storyName, "story from"));
      String url = getThumbnailUrl(comic);
      Picasso.with(context)
          .load(url).into(imageIcon);
      rootView.setOnClickListener(view -> listener.onItemSelected(comic));
    }

    private String getThumbnailUrl(final Comic comic) {
      return String.format("%s.%s", comic.getThumbnail().getPath(), comic.getThumbnail().getExtension());
    }
  }
}
