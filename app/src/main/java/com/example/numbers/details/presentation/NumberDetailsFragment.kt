package com.example.numbers.details.presentation

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.numbers.R
import com.example.numbers.main.presentation.BaseFragment

class NumberDetailsFragment : BaseFragment<NumberDetailsViewModel>() {

    override val viewModelClass = NumberDetailsViewModel::class.java

    override val layoutId = R.layout.fragment_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val value = viewModel.read()
        view.findViewById<TextView>(R.id.detailsTextView).text = value
    }
}