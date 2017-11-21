package com.codepath.example.marvelapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.codepath.example.marvelapp.utils.ListHelper;
import com.codepath.example.marvelapp.R;
import com.codepath.example.marvelapp.utils.StringUtil;
import com.codepath.example.marvelapp.data.model.Comic;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final ItemsListFragment.ItemSelection listener;
  private final Context context;
  private List<Comic> comics;
  private Map<String, Integer> mapWithSections;


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
    final String section = getSection(comic);
    final boolean showSection = mapWithSections.get(section) == position;
    ((SuperHeroViewHolder) holder).bind(comic, section, showSection);
  }

  void loadResults(@NonNull final List<Comic> comics) {
    this.comics = comics;
    mapWithSections = ListHelper.provideListHelper().getMapWithSections(comics);
    notifyDataSetChanged();
  }

  private String getSection(final Comic comic) {
    return comic.getDescription().substring(0, 1).toUpperCase();
  }

  @Override
  public int getItemCount() {
    return comics.size();
  }

  private class SuperHeroViewHolder extends RecyclerView.ViewHolder {

    private View rootView;
    private TextView sectionView;
    private TextView labelTextView;
    private ImageView imageIcon;

    SuperHeroViewHolder(View view) {
      super(view);
      rootView = view;
      sectionView = (TextView) view.findViewById(R.id.section_title);
      labelTextView = (TextView) view.findViewById(R.id.character_result_item_name);
      imageIcon = (ImageView) view.findViewById(R.id.image_icon);
    }

    void bind(@NonNull final Comic comic, final String section, final boolean showSection) {
      sectionView.setText(section);
      sectionView.setVisibility(showSection ? View.VISIBLE : View.GONE);
      final String shortDescription = StringUtil.getWordsFromString(comic.getDescription());
      labelTextView.setText(String.format("%s ...", shortDescription));
      Picasso.with(context)
          .load(comic.getResourceURI()).into(imageIcon);
      rootView.setOnClickListener(view -> listener.onItemSelected(comic));
    }
  }
}
