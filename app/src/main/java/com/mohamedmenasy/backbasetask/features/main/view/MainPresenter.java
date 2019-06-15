package com.mohamedmenasy.backbasetask.features.main.view;

import android.content.Context;

import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.core.model.City;
import com.mohamedmenasy.backbasetask.core.model.LoadCitiesInteractor;
import com.mohamedmenasy.backbasetask.core.model.SearchForCitiesInteractor;
import com.mohamedmenasy.backbasetask.core.model.trie.SortedMapTrie;

import java.util.List;

public class MainPresenter {
    private MainView mainView;
    private LoadCitiesInteractor loadCitiesInteractor;
    private SearchForCitiesInteractor searchForCitiesInteractor;
    private Context context;
    private SortedMapTrie<City> trie = new SortedMapTrie<>();

    MainPresenter(Context context, MainView mainView, LoadCitiesInteractor loadCitiesInteractor, SearchForCitiesInteractor searchForCitiesInteractor) {
        this.mainView = mainView;
        this.loadCitiesInteractor = loadCitiesInteractor;
        this.context = context;
        this.searchForCitiesInteractor = searchForCitiesInteractor;
    }

    void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        loadCitiesInteractor.loadItems(context.getResources().openRawResource(R.raw.cities), this::onFinished);
    }

    void onItemClicked(String item) {
        if (mainView != null) {
            mainView.showMessage(String.format("%s clicked", item));
        }
    }

    void onDestroy() {
        mainView = null;
    }

    public void onFinished(List<City> items) {
        if (mainView != null) {

            mainView.setItems(items);
            for (City c : items) {
                trie.insert(c.getName(), c);
            }
            mainView.hideProgress();
        }
    }

    public void onSearchFinished(List<City> items) {
        if (mainView != null) {
            mainView.setItems(items);
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }

    public void search(String keyword) {
        searchForCitiesInteractor.findCities(trie, keyword, this::onSearchFinished);
    }
}
