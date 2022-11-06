package com.example.moviles_vinils_app_grupo_32.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.espresso.contrib.RecyclerViewActions;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.ViewInteraction;
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
public class Test1 {

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        ViewInteraction skipBtn = onView(allOf(withId(R.id.button), withText("Usuario Visitante"),isDisplayed()));
        skipBtn.perform(click());

        ViewInteraction usVisBtn = onView(allOf(withId(R.id.button3), withText("Albums"),isDisplayed()));
        usVisBtn.perform(click());

        //Validar como probar que existe resultado de la consulta
        ViewInteraction imageView = onView(allOf(withId(R.id.textView6), withText("Artista: Rubén Blades Bellido de Luna"))).check(matches(isDisplayed()));
        imageView.check(matches(isDisplayed()));
    }

    @Test(expected = PerformException.class)
    public void itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(ViewMatchers.withId(R.id.albumsRv))
                // scrollTo will fail the test if no item matches.
                .perform(RecyclerViewActions.scrollTo(
                        hasDescendant(withText("not in the list"))
                ));
    }
}
