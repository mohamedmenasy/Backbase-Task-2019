package com.mohamedmenasy.backbasetask.data;

import com.mohamedmenasy.backbasetask.core.model.trie.Trie;
import com.mohamedmenasy.backbasetask.core.data.City;
import com.mohamedmenasy.backbasetask.core.data.Coord;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrieTest {

    private Trie<City> trie;
    private City alabamaCity;
    private City albuquerqueCity;
    private City anaheimCity;
    private City arizonaCity;
    private City sydneyCity;

    @Before
    public void setUp() {
        trie = new Trie<>();
        alabamaCity = new City("US", "Alabama", 1, new Coord(0.0, 0.0));
        albuquerqueCity = new City("US", "Albuquerque", 2, new Coord(0.0, 0.0));
        anaheimCity = new City("US", "Anaheim", 3, new Coord(0.0, 0.0));
        arizonaCity = new City("US", "Arizona", 4, new Coord(0.0, 0.0));
        sydneyCity = new City("AU", "Sydney", 5, new Coord(0.0, 0.0));
    }

    @After
    public void cleanUp() {
        trie = null;
    }

    @Test
    public void insert() {
        assertEquals(0, trie.size());
        trie.insert(alabamaCity.getName(), alabamaCity);
        trie.insert(alabamaCity.getName(), alabamaCity);
        trie.insert(alabamaCity.getName().toLowerCase(), alabamaCity);
        trie.insert(" " + alabamaCity.getName() + " ", alabamaCity);
        trie.insert(" " + alabamaCity.getName().toLowerCase() + " ", alabamaCity);
        assertEquals(1, trie.size());
        trie.insert(albuquerqueCity.getName(), albuquerqueCity);
        assertEquals(2, trie.size());
    }

    @Test
    public void getValueSuggestionsCapitalChars() {
        trie.insert(alabamaCity.getName(), alabamaCity);
        trie.insert(albuquerqueCity.getName(), albuquerqueCity);
        trie.insert(anaheimCity.getName(), anaheimCity);
        trie.insert(arizonaCity.getName(), arizonaCity);
        trie.insert(sydneyCity.getName(), sydneyCity);

        List<City> aSuggestions = trie.getValueSuggestions("A");
        assertTrue(aSuggestions.contains(alabamaCity));
        assertTrue(aSuggestions.contains(albuquerqueCity));
        assertTrue(aSuggestions.contains(anaheimCity));
        assertTrue(aSuggestions.contains(arizonaCity));
        assertFalse(aSuggestions.contains(sydneyCity));

        List<City> alSuggestions = trie.getValueSuggestions("Al");
        assertTrue(alSuggestions.contains(alabamaCity));
        assertTrue(alSuggestions.contains(albuquerqueCity));
        assertFalse(alSuggestions.contains(anaheimCity));
        assertFalse(alSuggestions.contains(arizonaCity));
        assertFalse(alSuggestions.contains(sydneyCity));

        List<City> AlbSuggestions = trie.getValueSuggestions("Alb");
        assertFalse(AlbSuggestions.contains(alabamaCity));
        assertTrue(AlbSuggestions.contains(albuquerqueCity));
        assertFalse(AlbSuggestions.contains(anaheimCity));
        assertFalse(AlbSuggestions.contains(arizonaCity));
        assertFalse(AlbSuggestions.contains(sydneyCity));

    }

    @Test
    public void getValueSuggestionsSmallChar() {
        trie.insert(alabamaCity.getName(), alabamaCity);
        trie.insert(albuquerqueCity.getName(), albuquerqueCity);
        trie.insert(anaheimCity.getName(), anaheimCity);
        trie.insert(arizonaCity.getName(), arizonaCity);
        trie.insert(sydneyCity.getName(), sydneyCity);

        List<City> aSuggestions = trie.getValueSuggestions("a");
        assertTrue(aSuggestions.contains(alabamaCity));
        assertTrue(aSuggestions.contains(albuquerqueCity));
        assertTrue(aSuggestions.contains(anaheimCity));
        assertTrue(aSuggestions.contains(arizonaCity));
        assertFalse(aSuggestions.contains(sydneyCity));

        List<City> alSuggestions = trie.getValueSuggestions("al");
        assertTrue(alSuggestions.contains(alabamaCity));
        assertTrue(alSuggestions.contains(albuquerqueCity));
        assertFalse(alSuggestions.contains(anaheimCity));
        assertFalse(alSuggestions.contains(arizonaCity));
        assertFalse(alSuggestions.contains(sydneyCity));

        List<City> AlbSuggestions = trie.getValueSuggestions("alb");
        assertFalse(AlbSuggestions.contains(alabamaCity));
        assertTrue(AlbSuggestions.contains(albuquerqueCity));
        assertFalse(AlbSuggestions.contains(anaheimCity));
        assertFalse(AlbSuggestions.contains(arizonaCity));
        assertFalse(AlbSuggestions.contains(sydneyCity));

    }

    @Test
    public void deleteKey() {
        trie.insert(alabamaCity.getName(), alabamaCity);
        trie.deleteKey(alabamaCity.getName());
        assertEquals(0, trie.size());

        trie.insert(alabamaCity.getName(), alabamaCity);
        trie.deleteKey(alabamaCity.getName());
        trie.deleteKey(alabamaCity.getName());
        assertEquals(0, trie.size());

        trie.insert(alabamaCity.getName(), alabamaCity);
        trie.insert(albuquerqueCity.getName(), albuquerqueCity);
        trie.deleteKey(alabamaCity.getName());
        assertEquals(1, trie.size());

    }
}
