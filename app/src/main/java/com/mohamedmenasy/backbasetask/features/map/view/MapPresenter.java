package com.mohamedmenasy.backbasetask.features.map.view;

import com.mohamedmenasy.backbasetask.features.main.view.CityView;
import com.mohamedmenasy.backbasetask.core.model.City;

import java.util.List;

public class MapPresenter {
    private CityView mainCityView;

    MapPresenter(CityView mainCityView) {
        this.mainCityView = mainCityView;
    }

    void onResume() {
        if (mainCityView != null) {
            mainCityView.showProgress();
        }
    }

    void onItemClicked(String item) {
        if (mainCityView != null) {
            mainCityView.showMessage(String.format("%s clicked", item));
        }
    }

    void onDestroy() {
        mainCityView = null;
    }

    public void onFinished(List<City> items) {
        if (mainCityView != null) {
            mainCityView.setItems(items);
            mainCityView.hideProgress();
        }
    }

    public CityView getMainCityView() {
        return mainCityView;
    }
}
