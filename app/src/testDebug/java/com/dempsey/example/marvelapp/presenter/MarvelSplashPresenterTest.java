package com.dempsey.example.marvelapp.presenter;

import com.dempsey.example.marvelapp.business.AppMarvelBusiness;
import com.dempsey.example.marvelapp.data.model.Comic;
import com.dempsey.example.marvelapp.data.model.Comics;
import com.dempsey.example.marvelapp.data.model.ParameterBuilder;
import com.dempsey.example.marvelapp.utils.NetworkConnectivityService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MarvelSplashPresenterTest extends TestCase {

  private MarvelSplashPresenter presenter;

  @Mock private AppMarvelBusiness business;

  @Mock private MarvelSplashContract.View view;

  @Mock private NetworkConnectivityService service;

  @Before
  public void setUp() {
    presenter = new MarvelSplashPresenter(business, view, service);
  }

  @Test
  public void assertWeHaveNoComicsStoredOnStartup() {
    when(business.retrieveComicsFromInternalStorage()).thenReturn(provideComicMock());
    final boolean hasResults = presenter.hasStoredResults();
    assertFalse(hasResults);
  }

  @Test
  public void assertWeGetAComicObjectWithComicList() {
    final Comic comic = provideComicMock();
    final Comics comics = comic.getComics();
    comics.setComics(Collections.singletonList(provideComicMock()));
    when(business.retrieveComicsFromInternalStorage()).thenReturn(comic);
    final boolean hasResults = presenter.hasStoredResults();
    assertTrue(hasResults);
  }

  @Test
  public void testWeGetEncodedStringAndDate() {
    final String firstParam = "keyOne";
    final String secondParam = "keyTwo";
    final ParameterBuilder params = new ParameterBuilder()
        .withFirstParameter(firstParam)
        .withSecondParameter(secondParam);

    final ParameterBuilder paramWithEncodedString =  presenter.buildParams(params);

    assertNotNull(paramWithEncodedString.getEncodedParameter());
    assertNotNull(paramWithEncodedString.getDateParameter());
  }

  @Test
  public void testNetworkCallWithNoNetwork() {
    when(service.isConnectedOrConnecting()).thenReturn(false);
    presenter.retrieveListOfComics(provideMockParams());
    verify(view).showNoNetworkError();
  }

  private Comic provideComicMock() {
    final Comic comic = new Comic();
    final List<Comic> comicList = new ArrayList<>();
    final Comics comics = new Comics();
    comics.setComics(comicList);
    comic.setComics(comics);
    return comic;
  }

  private ParameterBuilder provideMockParams() {
    return new ParameterBuilder().withFirstParameter("first")
        .withSecondParameter("second")
        .withDateParameter("theDate")
        .withEncodedParameter("encodedString");
  }
}