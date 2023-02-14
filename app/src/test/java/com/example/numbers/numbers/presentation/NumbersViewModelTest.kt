package com.example.numbers.numbers.presentation

import com.example.numbers.numbers.domain.NumberFact
import com.example.numbers.numbers.domain.NumbersInteractor
import com.example.numbers.numbers.domain.NumbersResult
import org.junit.Assert.*
import org.junit.Test

class NumbersViewModelTest {

    /**
     * Initial test
     * At start fetch data and show it
     * then try to get some data successfully
     * then re-init and check the result
     */
    @Test
    fun `test init and re-init`() {
        val communications = TestNumbersCommunications()
        val interactor = TestNumbersInteractor()
        //1. init
        val viewModel = NumbersViewModel(communications, interactor)
        interactor.changeExpectedResult(NumbersResult.Success())
        //2. action
        viewModel.init(isFirstRun = true)
        //3. check
        assertEquals(true, communications.progressCalledList[0])
        assertEquals(2, communications.progressCalledList.size)
        assertEquals(false, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(UiState.Success(emptyList<NumberUi>()), communications.stateCalledList[0])

        assertEquals(0, communications.numbersList.size)
        assertEquals(0, communications.timesShowList)

        //get some data
        interactor.changeExpectedResult(NumbersResult.Failure("no internet connection"))
        viewModel.fetchRandomNumberFact()

        assertEquals(true, communications.progressCalledList[2])

        assertEquals(1, interactor.fetchAboutRandomNumberCalledList.size)

        assertEquals(4, communications.progressCalledList.size)
        assertEquals(false, communications.progressCalledList[3])

        assertEquals(2, communications.stateCalledList.size)
        assertEquals(UiState.Error("no internet connection"), communications.stateCalledList[1])
        assertEquals(0, communications.timesShowList)

        viewModel.init(isFirstRun = false)
        assertEquals(4, communications.progressCalledList.size)
        assertEquals(2, communications.stateCalledList.size)
        assertEquals(0, communications.timesShowList)
    }

    /**
     * Try to get information about empty number
     */
    @Test
    fun `fact about empty number`() {
        val communications = TestNumbersCommunications()
        val interactor = TestNumbersInteractor()

        val viewModel = NumbersViewModel(communications, interactor)

        viewModel.fetchNumberFact("")

        assertEquals(0, interactor.fetchAboutNumberCalledList.size)

        assertEquals(1, communications.progressCalledList.size)

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(UiState.Error("entered number is empty"), communications.stateCalledList[0])

        assertEquals(0, communications.timesShowList)
    }

    /**
     * Try to get information about some number
     */
    @Test
    fun `fact about some number`() {
        val communications = TestNumbersCommunications()
        val interactor = TestNumbersInteractor()

        val viewModel = NumbersViewModel(communications, interactor)

        interactor.changeExpectedResult(
            NumbersResult.Success(
                listOf(
                    NumberFact(
                        "45",
                        "fact about 45"
                    )
                )
            )
        )
        viewModel.fetchNumberFact("45")

        assertEquals(true, communications.progressCalledList[0])

        assertEquals(1, interactor.fetchAboutNumberCalledList.size)
        assertEquals(Number("45", "fact about 45"), interactor.fetchAboutNumberCalledList[0])

        assertEquals(2, communications.progressCalledList.size)
        assertEquals(false, communications.progressCalledList[1])

        assertEquals(1, communications.stateCalledList.size)
        assertEquals(UiState.Success(), communications.stateCalledList[0])

        assertEquals(1, communications.timesShowList)
        assertEquals(NumberUi("45", "fact about 45"), communications.numbersList[0])
    }

    private class TestNumbersCommunications : NumbersCommunications {

        val progressCalledList = mutableListOf<Boolean>()
        val stateCalledList = mutableListOf<Boolean>()
        val numbersList = mutableListOf<NumberUi>()
        var timesShowList = 0

        override fun showProgress(show: Boolean) {
            progressCalledList.add(show)
        }

        override fun showState(state: UiState) {
            stateCalledList.add(state)
        }

        override fun showList(list: List<NumberUi>) {
            timesShowList++
            numbersList.addAll(list)
        }

    }

    private class TestNumbersInteractor : NumbersInteractor {

        private var result: NumbersResult = NumbersResult.Success()

        val initCalledList = mutableListOf<NumbersResult>()
        val fetchAboutNumberCalledList = mutableListOf<NumbersResult>()
        val fetchAboutRandomNumberCalledList = mutableListOf<NumbersResult>()

        fun changeExpectedResult(newResult: NumbersResult) {
            result = newResult
        }

        override suspend fun init(): NumbersResult {
            initCalledList.add(result)
            return result
        }

        override suspend fun factAboutNumber(number: String): NumbersResult {
            fetchAboutNumberCalledList.add()
            return result
        }

        override suspend fun factAboutRandomNumber(): NumbersResult {
            fetchAboutRandomNumberCalledList.add(result)
            return result
        }
    }
}