package com.example.numbers

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import com.example.numbers.main.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun details_navigation() {
        val device = UiDevice.getInstance(getInstrumentation())

        onView(withId(R.id.inputEditText)).perform(typeText("10"))
        closeSoftKeyboard()
        onView(withId(R.id.getFactButton)).perform(click())
        onView(withId(R.id.titleTextView)).check(matches(withText("10")))
        onView(withId(R.id.subTitleTextView)).check(matches(withText("fact about 10")))
        onView(withId(R.id.subTitleTextView)).perform(click())
        onView(withId(R.id.textViewDetails)).check(matches(withText("10\n\nfact about 10")))

        device.pressBack()
        onView(withId(R.id.titleTextView)).check(matches(withText("10")))
        onView(withId(R.id.subTitleTextView)).check(matches(withText("fact about 10")))
    }
}