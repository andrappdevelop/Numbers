package com.example.numbers

import org.junit.Test


class CheckItemReplacedTest : BaseTest() {

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