package com.example.moviles_vinils_app_grupo_32.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.moviles_vinils_app_grupo_32.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Test6 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void collectorDetail() {
        ViewInteraction usVisBtn = onView(allOf(withId(R.id.button), withText("Usuario Visitante"),isDisplayed()));
        usVisBtn.perform(click());

        ViewInteraction artistsBtn = onView(allOf(withId(R.id.collectors_button), withText("Coleccionistas"),isDisplayed()));
        artistsBtn.perform(click());

        ViewInteraction imageView = onView(allOf(withId(R.id.collector_item_name), withText("..."))).check(matches(isDisplayed()));
        // imageView.perform(click());
    }
}
