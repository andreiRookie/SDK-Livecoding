package com.andreirookie.sdk_livecoding

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class FragmentWithNumber : Fragment(R.layout.fragment_w_number_layout) {

    private lateinit var backButton: Button
    private lateinit var nextButton: Button
    private lateinit var numberTextView: TextView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        numberTextView = view.findViewById(R.id.frag_number)

        backButton = view.findViewById(R.id.back_button)
        nextButton = view.findViewById(R.id.next_buttopn)

        backButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        var numFromArg: Int = 0
        arguments?.let {
            numFromArg = it.getInt(FRAG_ARG, 1)
        }

        numberTextView.text = numFromArg.toString()

        if (numFromArg == AnotherActivity.PRIME_FRAG) {
            backButton.visibility = View.INVISIBLE
        }

        nextButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.container, FragmentWithNumber.create(numFromArg + 1))
                .addToBackStack(FRAGMENT_TAG)
                .commit()
        }

    }

    companion object {

        const val FRAGMENT_TAG = "tag"

        private val FRAG_ARG = "arg"

        fun create(number: Int) :FragmentWithNumber {
            val frag = FragmentWithNumber()
            frag.arguments = bundleOf(FRAG_ARG to number)
            return frag
        }
    }
}