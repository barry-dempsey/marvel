package com.codepath.example.masterdetailmanual.business;

import android.support.annotation.NonNull;
import com.codepath.example.masterdetailmanual.data.model.Comic;
import com.codepath.example.masterdetailmanual.data.model.Comics;
import java.util.List;

public interface MarvelBusiness {

  Comic getFullListOfCharacters(@NonNull final String... param);

  void storeComicsToInternalStorage(@NonNull Comic comics);

  Comic retrieveComicsFromInternalStorage();

}
