package com.dempsey.example.marvelapp.business;

import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import rx.Observable;

public interface MarvelBusiness {

  Observable<Comic> getFullListOfComics(@NonNull ParameterBuilder paramBuilder);

  void storeComicsToInternalStorage(@NonNull Comic comics);

  Comic retrieveComicsFromInternalStorage();

  void deleteAllFromStorage();

}
