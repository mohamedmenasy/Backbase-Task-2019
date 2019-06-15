package com.mohamedmenasy.backbasetask.core.model;

import android.content.Context;
import android.os.Handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mohamedmenasy.backbasetask.R;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LoadCitiesInteractor {
    public interface OnFinishedListener {
        void onFinished(List<City> items);
    }

    public void findItems(Context context, final OnFinishedListener listener) {
        new Handler().postDelayed(() -> listener.onFinished(getListOfCities(context)), 2000);
    }

    private List<City> getListOfCities(Context context) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(
                SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true
        );
        List<City> cities = null;
        try {
            cities = mapper.readValue(context.getResources().openRawResource(R.raw.cities), new TypeReference<List<City>>() {});
            Collections.sort(cities, (data1, data2) -> data1.getName().compareToIgnoreCase(data2.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;

    }
}
