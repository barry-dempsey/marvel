package com.codepath.example.masterdetailmanual.presenter;

import android.support.annotation.NonNull;

public interface MarvelContract {

  interface View {

    void displayResults();

  }

  interface ActionListener {

    boolean hasStoredResults();

    void retrieveListOfCharacters(@NonNull final String... param);

  }
}
