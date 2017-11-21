package com.dempsey.example.marvelapp.utils;

import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.data.model.Comic;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class ListHelper {

  public static ListHelper provideListHelper() {
    return new ListHelper();
  }

  public LinkedHashMap<String, Integer> getMapWithSections(@NonNull final List<Comic> items) {
    final List<Comic> comicList = provideSortedList(items);
    final ArrayList<String> sectionList;
    final String[] sections;
    final LinkedHashMap<String, Integer> mapIndex = new LinkedHashMap<String, Integer>();

    for (int index = 0; index < comicList.size(); index++) {
      String displayName;

      displayName = comicList.get(index).getDescription();

      if (displayName.length() > 1) {
        String ch = displayName.substring(0, 1);
        ch = ch.toUpperCase();
        if (!mapIndex.containsKey(ch)) {
          mapIndex.put(ch, index);
        }
      }
    }
    Set<String> sectionLetters = mapIndex.keySet();
    sectionList = new ArrayList<>(sectionLetters);
    Collections.sort(sectionList);

    sections = new String[sectionList.size()];
    sectionList.toArray(sections);
    return mapIndex;
  }

  private List<Comic> provideSortedList(final List<Comic> comics) {
    Collections.sort(comics, new Comparator<Comic>() {
      @Override
      public int compare(Comic lhs, Comic rhs) {
        return lhs.getDescription().compareTo(rhs.getDescription());
      }
    });
    return comics;
  }



}
