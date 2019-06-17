package com.mohamedmenasy.backbasetask.features.main.view;

import com.mohamedmenasy.backbasetask.features.main.data.City;

import java.util.List;

public interface CityView {
    void showProgress();

    void hideProgress();

    void setItems(List<City> items);

    void showMessage(String message);
}
