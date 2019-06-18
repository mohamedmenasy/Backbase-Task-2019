package com.mohamedmenasy.backbasetask.presenter;

import android.content.Context;
import android.content.res.Resources;

import com.mohamedmenasy.backbasetask.core.data.City;
import com.mohamedmenasy.backbasetask.core.data.Coord;
import com.mohamedmenasy.backbasetask.features.clitylist.data.LoadCitiesInteractor;
import com.mohamedmenasy.backbasetask.features.clitylist.data.SearchForCitiesInteractor;
import com.mohamedmenasy.backbasetask.features.clitylist.view.CityPresenter;
import com.mohamedmenasy.backbasetask.features.clitylist.view.CityView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CityPresenterTest {

    @Mock
    Context context;
    @Mock
    private Resources mockContextResources;
    @Mock
    CityView view;
    @Mock
    LoadCitiesInteractor loadCitiesInteractor;
    @Mock
    SearchForCitiesInteractor searchForCitiesInteractor;
    private CityPresenter presenter;

    @Before
    public void setUp() throws FileNotFoundException {
        MockitoAnnotations.initMocks(this);
        when(context.getResources()).thenReturn(mockContextResources);
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("raw/cities.json");

        when(mockContextResources.openRawResource(anyInt())).thenReturn(inputStream);

        presenter = new CityPresenter(context, view, loadCitiesInteractor, searchForCitiesInteractor, null);
    }

    @Test
    public void checkIfShowsProgressOnResume() {
        presenter.onResume();
        verify(view, times(1)).showProgress();
    }

    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.onDestroy();
        assertNull(presenter.getMainCityView());
    }

    @Test
    public void checkIfItemsArePassedToView() {
        City city1 = new City("EG", "Cairo", 1, new Coord(0.0, 0.0));
        City city2 = new City("EG", "Alexandria", 2, new Coord(0.0, 0.0));

        List<City> items = Arrays.asList(city1, city2);
        presenter.onFinished(items);
        verify(view, times(1)).setItems(items);
        verify(view, times(1)).hideProgress();
    }

    @Test
    public void checkIfSearchItemsArePassedToView() {
        City city1 = new City("EG", "Cairo", 1, new Coord(0.0, 0.0));
        City city2 = new City("EG", "Alexandria", 2, new Coord(0.0, 0.0));

        List<City> items = Arrays.asList(city1, city2);
        presenter.onSearchFinished(items);
        verify(view, times(1)).setItems(items);
        verify(view, times(1)).hideProgress();
    }

}
