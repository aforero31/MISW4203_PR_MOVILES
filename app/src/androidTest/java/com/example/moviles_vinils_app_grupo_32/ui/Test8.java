package com.example.moviles_vinils_app_grupo_32.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
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
public class Test8 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void createAlbum() {
        ViewInteraction skipBtn = onView(allOf(withId(R.id.button2), withText("Coleccionista"),isDisplayed()));
        skipBtn.perform(click());

        ViewInteraction collectorBtn = onView(allOf(withId(R.id.button6), withText("Crear Album"),isDisplayed()));
        collectorBtn.perform(click());

        onView(withId(R.id.trackName)).perform(clearText());
        onView(withId(R.id.trackName)).perform(typeText("Welcome To The Jungle"));
        onView(withId(R.id.trackMinutes)).perform(clearText());
        onView(withId(R.id.trackMinutes)).perform(typeText("4"));
        onView(withId(R.id.trackSeconds)).perform(clearText());
        onView(withId(R.id.trackSeconds)).perform(typeText("32"));
        onView(allOf(withId(R.id.addTrackBtn), withText("Agregar canción"),isDisplayed())).perform(click());

        onView(withId(R.id.trackName)).perform(clearText());
        onView(withId(R.id.trackName)).perform(typeText("Mr. Brownstone"));
        onView(withId(R.id.trackMinutes)).perform(clearText());
        onView(withId(R.id.trackMinutes)).perform(typeText("3"));
        onView(withId(R.id.trackSeconds)).perform(clearText());
        onView(withId(R.id.trackSeconds)).perform(typeText("45"));
        onView(allOf(withId(R.id.addTrackBtn), withText("Agregar canción"),isDisplayed())).perform(click());

        onView(withId(R.id.trackName)).perform(clearText());
        onView(withId(R.id.trackName)).perform(typeText("Sweet Child O' Mine"));
        onView(withId(R.id.trackMinutes)).perform(clearText());
        onView(withId(R.id.trackMinutes)).perform(typeText("5"));
        onView(withId(R.id.trackSeconds)).perform(clearText());
        onView(withId(R.id.trackSeconds)).perform(typeText("54"));
        onView(allOf(withId(R.id.addTrackBtn), withText("Agregar canción"),isDisplayed())).perform(click());

        onView(allOf(withId(R.id.saveTracksBtn), withText("Guardar canciones"),isDisplayed())).perform(click());
    }
}
/*
        name = view.findViewById(R.id.)
        minutes = view.findViewById(R.id.)
        seconds = view.findViewById(R.id.)
 */