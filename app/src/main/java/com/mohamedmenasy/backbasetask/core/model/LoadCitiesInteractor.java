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
        return Arrays.asList(
                new City(),
                new City(),
                new City()
        );
    }
}
