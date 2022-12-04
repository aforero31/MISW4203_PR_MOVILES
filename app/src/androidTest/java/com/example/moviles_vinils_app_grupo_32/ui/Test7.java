package com.example.moviles_vinils_app_grupo_32.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.example.moviles_vinils_app_grupo_32.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Test7 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void createAlbum() {
        ViewInteraction skipBtn = onView(allOf(withId(R.id.button2), withText("Coleccionista"),isDisplayed()));
        skipBtn.perform(click());

        ViewInteraction collectorBtn = onView(allOf(withId(R.id.button6), withText("Crear Album"),isDisplayed()));
        collectorBtn.perform(click());

        onView(withId(R.id.textAlbumName)).perform(clearText());
        onView(withId(R.id.textAlbumName)).perform(typeText("Appetite for Destruction"));
        onView(withId(R.id.cover)).perform(clearText());
        onView(withId(R.id.cover)).perform(typeText("https://en.wikipedia.org/wiki/File:GunsnRosesAppetiteforDestructionalbumcover.jpg"));
        onView(withId(R.id.releaseDate)).perform(clearText());
        onView(withId(R.id.releaseDate)).perform(typeText("1987"));
        onView(withId(R.id.description)).perform(clearText());
        onView(withId(R.id.description)).perform(typeText("Appetite for Destruction is the debut studio album by American hard rock band Guns N' Roses"));
        onView(withId(R.id.genre)).perform(clearText());
        onView(withId(R.id.genre)).perform(typeText("Hard Rock"));
        onView(withId(R.id.recordLabel)).perform(clearText());
        onView(withId(R.id.recordLabel)).perform(typeText("Geffen Records"));
        onView(allOf(withId(R.id.createAlbumBtn), withText("Crear Album"),isDisplayed())).perform(click());
    }
}
