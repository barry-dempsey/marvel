package com.dempsey.example.marvelapp.data.dao.remotedao;

import android.support.annotation.NonNull;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import rx.Observable;

interface MarvelRemoteDao {

  Comic getAllComics(@NonNull final ParameterBuilder paramBuilder);


}
