package com.dempsey.example.marvelapp.business;

import android.content.SharedPreferences;
import com.dempsey.example.marvelapp.data.dao.remotedao.AppMarvelLocalDao;
import com.dempsey.example.marvelapp.data.dao.remotedao.AppMarvelRemoteDao;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.Comics;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppMarvelBusinessTest extends TestCase {

  private AppMarvelBusiness marvelBusiness;

  @Mock
  private AppMarvelLocalDao localDao;

  @Mock
  private AppMarvelRemoteDao remoteDao;

  @Mock
  private SharedPreferences preferences;

  @Before
  public void setUp() {
    marvelBusiness = new AppMarvelBusiness(remoteDao, localDao);
  }

  @Test
  public void testInternalStorageOfComics() {
    marvelBusiness.storeComicsToInternalStorage(provideComicMock());
    when(preferences.getString("comicsStored", null)).thenReturn("jsonString");
    when(marvelBusiness.retrieveComicsFromInternalStorage()).thenReturn(provideComicMock());
    assertNotNull(marvelBusiness.retrieveComicsFromInternalStorage());
  }

  @Test
  public void testForDeleteFromStorage() {
    marvelBusiness.deleteAllFromStorage();
    when(marvelBusiness.retrieveComicsFromInternalStorage()).thenReturn(null);
    assertNull(marvelBusiness.retrieveComicsFromInternalStorage());
  }

  private Comic provideComicMock() {
    final Comic comic = new Comic();
    final List<Comic> comicList = new ArrayList<>();
    final Comics comics = new Comics();
    comics.setComics(comicList);
    comic.setComics(comics);
    return comic;
  }

}