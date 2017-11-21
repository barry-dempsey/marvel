package com.codepath.example.masterdetailmanual.data.dao.remotedao;

import android.support.annotation.NonNull;
import com.codepath.example.masterdetailmanual.data.model.Comic;
import com.codepath.example.masterdetailmanual.data.model.Comics;
import java.util.List;

interface MarvelRemoteDao {

  Comic getAllCharacters(@NonNull final String param, @NonNull final String... params);


}
