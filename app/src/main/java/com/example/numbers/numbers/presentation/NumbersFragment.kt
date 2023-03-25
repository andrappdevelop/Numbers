package com.example.numbers.numbers.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.example.numbers.R
import com.example.numbers.main.presentation.BaseFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class NumbersFragment : BaseFragment<NumbersViewModel.Base>() {

    private lateinit var inputEditText: TextInputEditText

    private val watcher = object : SimpleTextWatcher() {
        override fun afterTextChanged(p0: Editable?) = viewModel.clearError()
    }

    override val viewModelClass = NumbersViewModel.Base::class.java

    override val layoutId = R.layout.fragment_numbers

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val factButton = view.findViewById<Button>(R.id.getFactButton)
        val randomButton = view.findViewById<Button>(R.id.getRandomFactButton)
        val inputLayout = view.findViewById<TextInputLayout>(R.id.textInputLayout)
        val recyclerView = view.findViewById<RecyclerView>(R.id.historyRecyclerView)
        inputEditText = view.findViewById(R.id.inputEditText)
        val adapter = NumbersAdapter(object : ClickListener {
            override fun click(item: NumberUi) = viewModel.showDetails(item)
        })

        recyclerView.adapter = adapter

        factButton.setOnClickListener {
            viewModel.fetchNumberFact(inputEditText.text.toString())
        }

        randomButton.setOnClickListener {
            viewModel.fetchRandomNumberFact()
        }

        viewModel.observeState(this) {
            it.apply(inputLayout, inputEditText)
        }

        viewModel.observeList(this) {
            adapter.map(it)
        }
        viewModel.observeProgress(this) {
            progressBar.visibility = it
        }

        viewModel.init(savedInstanceState == null)
    }

    override fun onResume() {
        inputEditText.addTextChangedListener(watcher)
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        inputEditText.removeTextChangedListener(watcher)
    }
}

abstract class SimpleTextWatcher : TextWatcher {
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
    override fun afterTextChanged(p0: Editable?) = Unit
}