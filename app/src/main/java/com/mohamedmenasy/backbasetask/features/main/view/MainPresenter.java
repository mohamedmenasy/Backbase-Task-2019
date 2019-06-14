package com.mohamedmenasy.backbasetask.features.main.view;

import com.mohamedmenasy.backbasetask.core.model.City;
import com.mohamedmenasy.backbasetask.core.model.LoadCitiesInteractor;

import java.util.List;

public class MainPresenter {
    private MainView mainView;
    private LoadCitiesInteractor loadCitiesInteractor;

    MainPresenter(MainView mainView, LoadCitiesInteractor loadCitiesInteractor) {
        this.mainView = mainView;
        this.loadCitiesInteractor = loadCitiesInteractor;
    }

    void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        loadCitiesInteractor.findItems(this::onFinished);
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
