package com.mohamedmenasy.backbasetask.features.clitylist.data;

import android.os.Handler;

import com.mohamedmenasy.backbasetask.core.data.City;
import com.mohamedmenasy.backbasetask.core.model.trie.Trie;

import java.util.List;

public class SearchForCitiesInteractor {
    public interface OnSearchFinishedListener {
        void onFinished(List<City> items);
    }

    public void findCities(Trie<City> trie, String keyword, final OnSearchFinishedListener listener) {
        new Handler().post(() -> listener.onFinished(trieFind(trie, keyword)));
    }

    private List<City> trieFind(Trie<City> trie, String keyword) {
        // search the Trie data structure by prefix
        return trie.getValueSuggestions(keyword);
    }
}


