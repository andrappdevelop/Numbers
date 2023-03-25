package com.example.numbers.main.presentation

import com.example.numbers.numbers.presentation.BaseTest
import org.junit.Assert.*
import org.junit.Test

class MainViewModelTest : BaseTest() {

    @Test
    fun `test navigation at start`() {
        val navigation = TestNavigationCommunication()
        val mainViewModel = MainViewModel(navigation)

        mainViewModel.init(true)
        assertEquals(1, navigation.count)
        assertEquals(true, navigation.strategy is NavigationStrategy.Replace)

        mainViewModel.init(false)
        assertEquals(1, navigation.count)
    }
}