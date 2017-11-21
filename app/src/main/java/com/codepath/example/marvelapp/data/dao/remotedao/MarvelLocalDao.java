package com.codepath.example.marvelapp.data.dao.remotedao;

import android.support.annotation.NonNull;
import com.codepath.example.marvelapp.data.model.Comic;

public interface MarvelLocalDao {
  
  void storeToInternal(@NonNull final Comic comics);
  
  Comic retrieveFromStorage();
  
}
