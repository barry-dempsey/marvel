package com.dempsey.example.marvelapp.data.dao.remotedao;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import rx.Observable;

import static android.support.annotation.VisibleForTesting.PRIVATE;

public interface MarvelRemoteDao {

  Comic getAllComics(@NonNull final ParameterBuilder paramBuilder);


}
