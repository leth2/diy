package com.example.android.fragmentcommunicate

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.RatingBar
import android.widget.Toast

import kotlinx.android.synthetic.main.fragment_simple2.*
import java.lang.ClassCastException


class SimpleFragment2 : Fragment() {
    private val YES = 0
    private val NO = 1
    private val NONE = 2
    private var mRadioButtonChoice = NONE
    private var mlistener: OnFragmentInteractionListener? = null
    private val CHOICE = "choice"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_simple2, container, false)
        val radioGroup = rootView.findViewById<RadioGroup>(R.id.radio_group2)

        if(arguments.containsKey(CHOICE)) {
            mRadioButtonChoice = arguments.getInt(CHOICE)
            if(mRadioButtonChoice != NONE){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).id)
            }
        }

        radioGroup?.setOnCheckedChangeListener { p0, p1 ->
            when(radioGroup.indexOfChild(radioGroup.findViewById<View>(p1))){
                YES -> {
                    fragment_header2.text = getString(R.string.yes_message)
                    mRadioButtonChoice = YES
                    mlistener?.onRadioButtonChoice(YES)
                }
                NO -> {
                    fragment_header2.text = getString(R.string.no_message)
                    mRadioButtonChoice = NO
                    mlistener?.onRadioButtonChoice(NO)
                }
                else -> {
                    mRadioButtonChoice = NONE
                    mlistener?.onRadioButtonChoice(NONE)
                }
            }
        }

        //coding challenge
        val ratingBar = rootView.findViewById<RatingBar>(R.id.ratingBar3)
        ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            Toast.makeText(rootView.context, "My Rating $fl",Toast.LENGTH_SHORT ).show()
        }

        return rootView
    }

    interface OnFragmentInteractionListener {
        fun onRadioButtonChoice(choice : Int)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mlistener = context
        } else {
            throw ClassCastException(context.toString() + getString(R.string.exception_message))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(choice: Int) =
                SimpleFragment2().apply {
                    arguments = Bundle().apply {
                        putInt(CHOICE, choice)
                    }
                }
    }
}
