package com.mohamedmenasy.backbasetask.view;


import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.RecyclerViewItemCountAssertion;
import com.mohamedmenasy.backbasetask.core.view.MainActivity;
import com.mohamedmenasy.backbasetask.features.info.view.InfoActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private IdlingResource mIdlingResource;
    private static final String SEARCH_KEYWORD_1 = "max";
    private static final String SEARCH_KEYWORD_2 = "alb";
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);
    @Rule
    public IntentsTestRule<InfoActivity> intentsTestRule =
            new IntentsTestRule<>(InfoActivity.class);

    @Before
    public void registerIdlingResource() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        activityScenario.onActivity((ActivityScenario.ActivityAction<MainActivity>) activity -> {
            mIdlingResource = activity.getIdlingResource();
            // To prove that the test fails, omit this call:
            IdlingRegistry.getInstance().register(mIdlingResource);
        });
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }

    @Test
    public void checkFragmentIsDisplayed() {

        onView(withId(R.id.list)).check(matches(isDisplayed()));
        onView(withId(R.id.list)).check(new RecyclerViewItemCountAssertion(209557));

        onView(withId(R.id.searchET)).check(matches(isDisplayed()));
    }

    @Test
    public void checkSearching() {

        onView(withId(R.id.searchET))
                .perform(replaceText(SEARCH_KEYWORD_1), closeSoftKeyboard());
        onView(withId(R.id.list)).check(new RecyclerViewItemCountAssertion(25));

        onView(withId(R.id.searchET))
                .perform(replaceText(SEARCH_KEYWORD_2), closeSoftKeyboard());
        onView(withId(R.id.list)).check(new RecyclerViewItemCountAssertion(209));

    }

    @Test
    public void checkNavigationToInfoScreen() {

        onView(withId(R.id.searchET))
                .perform(replaceText(SEARCH_KEYWORD_1), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, clickChildViewWithId(R.id.infoIV)));
        intended(hasComponent(InfoActivity.class.getName()));
        onView(allOf(withId(R.id.idTV), withText("5843946")));
        onView(allOf(withId(R.id.countyTV), withText("US")));
        onView(allOf(withId(R.id.cityTV), withText("Max")));

    }

    @Test
    public void checkNavigationToMapScreen() {

        onView(withId(R.id.searchET))
                .perform(replaceText(SEARCH_KEYWORD_1), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.mapView)).check(matches(isDisplayed()));

        onView(withId(R.id.toolbar)).check(matches(isDisplayed()));
        onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText("Max, US"))));

    }

    public static ViewAction clickChildViewWithId(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}


