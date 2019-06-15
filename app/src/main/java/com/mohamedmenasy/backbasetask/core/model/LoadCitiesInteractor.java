package com.mohamedmenasy.backbasetask.core.model;

import android.os.Handler;
import android.os.HandlerThread;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public class LoadCitiesInteractor {
    public interface OnFinishedListener {
        void onFinished(List<City> items);
    }

    public void loadItems(InputStream stream, final OnFinishedListener listener) {
        final HandlerThread handlerThread = new HandlerThread("HandlerThread");

        if (!handlerThread.isAlive())
            handlerThread.start();
        new Handler(handlerThread.getLooper()).post(() -> listener.onFinished(getListOfCities(stream)));
    }

    private List<City> getListOfCities(InputStream stream) {
        ObjectMapper mapper = new ObjectMapper();
        List<City> cities = null;
        try {
            cities = mapper.readValue(stream, new TypeReference<List<City>>() {
            });
            Collections.sort(cities, (data1, data2) -> data1.getName().compareToIgnoreCase(data2.getName()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cities;

    }
}


