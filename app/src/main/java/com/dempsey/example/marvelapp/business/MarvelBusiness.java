package com.dempsey.example.marvelapp.business;

import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.data.model.Comic;

public interface MarvelBusiness {

  Comic getFullListOfCharacters(@NonNull final String... param);

  void storeComicsToInternalStorage(@NonNull Comic comics);

  Comic retrieveComicsFromInternalStorage();

}
