package com.mohamedmenasy.backbasetask.view;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.mohamedmenasy.backbasetask.R;
import com.mohamedmenasy.backbasetask.features.about.AboutActivity;

import org.junit.After;
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
public class AboutActivityTest {
    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<AboutActivity> mActivityTestRule = new ActivityTestRule<>(AboutActivity.class);

    @Before
    public void registerIdlingResource() {
        ActivityScenario activityScenario = ActivityScenario.launch(AboutActivity.class);
        activityScenario.onActivity((ActivityScenario.ActivityAction<AboutActivity>) activity -> {
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
    public void checkDataDisplayed() {

        onView(allOf(withId(R.id.companyName), withText("Backbase")));
        onView(allOf(withId(R.id.companyAdress), withText("Jacob Bontiusplaats 9")));
        onView(allOf(withId(R.id.companypostal), withText("1018 LL")));
        onView(allOf(withId(R.id.companyCity), withText("Amsterdam")));
        onView(allOf(withId(R.id.aboutInfo), withText("This is the Backbase assignment for Android engineering positions.")));
    }
}


