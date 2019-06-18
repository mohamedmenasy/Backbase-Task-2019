package com.mohamedmenasy.backbasetask.view;


import android.content.Intent;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.core.data.City;
import com.mohamedmenasy.backbasetask.core.data.Coord;
import com.mohamedmenasy.backbasetask.features.info.view.InfoActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class InfoActivityTest {
    @Rule
    public ActivityTestRule<InfoActivity> mActivityTestRule = new ActivityTestRule<>(InfoActivity.class);

    @Before
    public void setUp() {
        Intent intent = new Intent();
        City city = new City("EG", "Cairo", 1, new Coord(0.0, 0.0));
        intent.putExtra(InfoActivity.INTENT_KEY, city);
        mActivityTestRule.launchActivity(intent);
    }

    @Test
    public void checkDataDisplayed() {
        onView(allOf(withId(R.id.idTV), withText("1")));
        onView(allOf(withId(R.id.countyTV), withText("EG")));
        onView(allOf(withId(R.id.cityTV), withText("Cairo")));
    }


}


