package com.example.moviles_vinils_app_grupo_32.ui

import android.util.Log
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.example.moviles_vinils_app_grupo_32.R
import junit.framework.Assert.assertNotNull
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTestx {


    @Test
    fun testEventFragment() {
        val scenario = launchFragmentInContainer<AlbumFragment>()
        onView(withId(R.id.albumsRv)).perform(click())
        assertNotNull(scenario)
        Log.d("ANDER", "Estoy en testEventFragment")
    }

    @Test
    fun testEventFragment2() {
        val scenario = launchFragmentInContainer<AlbumFragment>()
        onView(withId(R.id.albumsRv)).perform(click())
        assertNotNull(scenario)
        Log.d("ANDER", "Estoy en testEventFragment2")
    }

    @Test
    fun mainActivityTest() {
        val scenario = launchFragmentInContainer<UsersFragment>()
        assertNotNull(scenario)
        val button = onView(
            Matchers.allOf(
                withId(R.id.button), ViewMatchers.withText("Usuario Visitante"),
                ViewMatchers.isDisplayed()
            )
        )
        button.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Log.d("ANDER", "Estoy en mainActivityTest")
    }

//    @Rule
//    public var mActivityTestRule = ActivityScenarioRule(
//        MainActivity::class.java
//    )
//
//    @Test
//    fun mainActivityTest2() {
//        val skipBtn = onView(
//            allOf(
//                withId(R.id.button),
//                ViewMatchers.withText("Usuario Visitante"),
//                ViewMatchers.isDisplayed()
//            )
//        )
//        skipBtn.perform(ViewActions.click())
//    }
}