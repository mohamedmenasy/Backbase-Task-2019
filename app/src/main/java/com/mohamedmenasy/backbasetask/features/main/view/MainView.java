package com.mohamedmenasy.backbasetask.features.main.view;

import com.mohamedmenasy.backbasetask.core.model.City;

import java.util.List;

public interface MainView {
    void showProgress();

    void hideProgress();

    void setItems(List<City> items);

    void showMessage(String message);
}
