package com.example.numbers

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.numbers.main.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CheckItemReplacedTest : BaseTest() {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_history(): Unit = NumbersPage().run {

        input.view().typeText("1")
        getFactButton.view().click()
        recycler.run {
            viewInRecycler(0, titleItem).checkText("1")
            viewInRecycler(0, subtitleItem).checkText("fact about 1")
        }

        input.view().typeText("2")
        getFactButton.view().click()
        recycler.run {
            viewInRecycler(0, titleItem).checkText("2")
            viewInRecycler(0, subtitleItem).checkText("fact about 2")
            viewInRecycler(1, titleItem).checkText("1")
            viewInRecycler(1, subtitleItem).checkText("fact about 1")
        }

        input.view().typeText("1")
        getFactButton.view().click()
        recycler.run {
            viewInRecycler(0, titleItem).checkText("1")
            viewInRecycler(0, subtitleItem).checkText("fact about 1")
            viewInRecycler(1, titleItem).checkText("2")
            viewInRecycler(1, subtitleItem).checkText("fact about 2")
        }
    }
}