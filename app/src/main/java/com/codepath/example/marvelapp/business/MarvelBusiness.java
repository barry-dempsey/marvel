package com.codepath.example.marvelapp.business;

import android.support.annotation.NonNull;
import com.codepath.example.marvelapp.data.model.Comic;

public interface MarvelBusiness {

  Comic getFullListOfCharacters(@NonNull final String... param);

  void storeComicsToInternalStorage(@NonNull Comic comics);

  Comic retrieveComicsFromInternalStorage();

}
