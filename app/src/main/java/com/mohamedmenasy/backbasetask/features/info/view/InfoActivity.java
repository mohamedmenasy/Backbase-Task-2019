package com.mohamedmenasy.backbasetask.features.info.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.core.data.City;

public class InfoActivity extends AppCompatActivity {
  public static final String INTENT_KEY = "city_obj";
  private TextView countryID;
  private TextView countyName;
  private TextView cityName;
  private TextView longitude;
  private TextView latitude;
  private android.view.View infoContainer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_info);
    countryID = findViewById(R.id.idTV);
    countyName = findViewById(R.id.countyTV);
    cityName = findViewById(R.id.cityTV);
    longitude = findViewById(R.id.longitudeTV);
    latitude = findViewById(R.id.latitudeTV);
    infoContainer = findViewById(R.id.infoContainer);

    City city = getIntent().getParcelableExtra(INTENT_KEY);
    if (city != null) {
      setCountryID(city.getId().toString());
      setCountyName(city.getCountry());
      setCityName(city.getName());
      setLongitude(city.getCoord().getLon().toString());
      setLatitude(city.getCoord().getLon().toString());
    }
  }

  public void setCountryID(String countryID) {
    infoContainer.setVisibility(android.view.View.VISIBLE);
    this.countryID.setText(countryID);
  }

  public void setCountyName(String countyName) {
    this.countyName.setText(countyName);
  }

  public void setCityName(String cityName) {
    this.cityName.setText(cityName);
  }

  public void setLongitude(String longitude) {
    this.longitude.setText(longitude);
  }

  public void setLatitude(String latitude) {
    this.latitude.setText(latitude);
  }
}
