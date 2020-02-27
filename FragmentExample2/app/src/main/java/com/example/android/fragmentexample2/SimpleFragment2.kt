package com.example.android.fragmentexample2

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_simple2.*


class SimpleFragment2 : Fragment() {
    private val YES = 0
    private val NO = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_simple2, container, false)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group2)
        radioGroup?.setOnCheckedChangeListener { p0, p1 ->
            when(radioGroup.indexOfChild(radioGroup.findViewById<View>(p1))){
                YES -> fragment_header2.text = getString(R.string.yes_message)
                NO -> fragment_header2.text = getString(R.string.no_message)
            }
        }

        //coding challenge
        val ratingBar = rootView.findViewById<RatingBar>(R.id.ratingBar3)
        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(rootView.context, "My Rating $fl",Toast.LENGTH_SHORT ).show()
        }

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance() = SimpleFragment2()
    }
}
