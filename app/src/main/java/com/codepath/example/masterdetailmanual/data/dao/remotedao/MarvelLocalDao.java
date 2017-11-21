package com.codepath.example.masterdetailmanual.data.dao.remotedao;

import android.support.annotation.NonNull;
import com.codepath.example.masterdetailmanual.data.model.Comic;
import com.codepath.example.masterdetailmanual.data.model.Comics;

public interface MarvelLocalDao {
  
  void storeToInternal(@NonNull final Comic comics);
  
  Comic retrieveFromStorage();
  
}
