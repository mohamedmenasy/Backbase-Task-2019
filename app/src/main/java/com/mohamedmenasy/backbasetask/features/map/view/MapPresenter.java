package com.mohamedmenasy.backbasetask.features.map.view;

import com.mohamedmenasy.backbasetask.features.main.view.MainView;
import com.mohamedmenasy.backbasetask.core.model.City;
import com.mohamedmenasy.backbasetask.core.model.LoadCitiesInteractor;

import java.util.List;

public class MapPresenter {
    private MainView mainView;

    MapPresenter(MainView mainView) {
        this.mainView = mainView;
    }

    void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }
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
            mainView.hideProgress();
        }
    }

    public MainView getMainView() {
        return mainView;
    }
}
