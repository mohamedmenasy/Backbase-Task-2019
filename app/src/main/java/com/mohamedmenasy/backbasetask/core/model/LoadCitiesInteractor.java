package com.mohamedmenasy.backbasetask.core.model;

import android.os.Handler;

import java.util.Arrays;
import java.util.List;

public class LoadCitiesInteractor {
    public interface OnFinishedListener {
        void onFinished(List<City> items);
    }

    public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(() -> listener.onFinished(createArrayList()), 2000);
    }

    private List<City> createArrayList() {
        //TODO : delete this dummy data and load the actual data
        return Arrays.asList(
                new City("Cairo", "EG", 1, new Coord(31.2335109, 30.0444191)),
                new City("Alex", "EG", 2, new Coord(31.2335109, 30.0444191)),
                new City("Cairo", "EG", 3, new Coord(31.2335109, 30.0444191))

        );
    }
}
