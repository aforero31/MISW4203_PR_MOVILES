package com.example.moviles_vinils_app_grupo_32.ui;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.moviles_vinils_app_grupo_32.R;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.util.Log;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest4 {

    @BeforeClass
    public static void printSomething(){
        Log.d("ANDER2", " --------------Estoy en el before de la prueba");
    }

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityTestRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void mainActivityTest2() {
        Log.d("ANDER", " --------------Voy a iniciar interaccion");
        ViewInteraction skipBtn = onView(
                allOf(withId(R.id.button)
                        ));
        skipBtn.perform(click());
    }
}