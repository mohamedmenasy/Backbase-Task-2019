package com.mohamedmenasy.backbasetask.features.clitylist.view;

import com.mohamedmenasy.backbasetask.core.data.City;
import java.util.List;

public interface CityView {
  void showProgress();

  void hideProgress();

  void setItems(List<City> items);
}
