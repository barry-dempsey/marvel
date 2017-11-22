package com.dempsey.example.marvelapp.presenter;

import com.dempsey.example.marvelapp.business.AppMarvelBusiness;
import com.dempsey.example.marvelapp.data.model.Comic;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ItemsListPresenterTest extends TestCase {

  private ItemsListPresenter presenter;

  @Mock
  private AppMarvelBusiness business;

  @Mock
  private ItemsContract.View view;

  @Before
  public void setUp() {
    presenter = new ItemsListPresenter(business, view);
  }

  @Test
  public void testWeReloadBehindTheSplash() {
    presenter.refreshListOfComics();
    verify(view).reloadFromSplashScreen();
  }

  @Test
  public void test() {
    when(presenter.retrieveComicsListFromStorage()).thenReturn(new Comic());
    assertNotNull(presenter.retrieveComicsListFromStorage());
  }

}