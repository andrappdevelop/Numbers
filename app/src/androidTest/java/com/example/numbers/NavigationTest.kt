package com.example.numbers

import androidx.test.espresso.Espresso.pressBack
import org.junit.Test

class NavigationTest : BaseTest() {

    @Test
    fun details_navigation() {

        val numbersPage = NumbersPage()

        numbersPage.run {
            input.view().typeText("10")
            getFactButton.view().click()

            recycler.run {
                viewInRecycler(0, titleItem).checkText("10")
                viewInRecycler(0, subtitleItem).checkText("fact about 10")
                viewInRecycler(0, subtitleItem).click()
            }
        }

        DetailsPage().run {
            details.view().checkText("10\n\nfact about 10")
        }

        pressBack()

        numbersPage.run {
            recycler.run {
                viewInRecycler(0, titleItem).checkText("10")
                viewInRecycler(0, subtitleItem).checkText("fact about 10")
            }
        }
    }
}