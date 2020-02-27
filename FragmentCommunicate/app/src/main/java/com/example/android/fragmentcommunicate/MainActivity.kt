package com.example.android.fragmentcommunicate

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SimpleFragment2.OnFragmentInteractionListener {
    private var isFragmentDisplayed = false
    private var mRadioButtonChoice = 2
    private val STATE_FRAGMENT = "state_of_fragment"

    override fun onRadioButtonChoice(choice: Int) {
        mRadioButtonChoice = choice
        val choiceName = when(choice){
            0 -> "YES"
            1 -> "NO"
            2 -> "NONE"
            else -> null
        }

        Toast.makeText(this, "Choice is $choiceName", Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                open_button.text = getText(R.string.close)
            }
        }

        setContentView(R.layout.activity_main)

        open_button.setOnClickListener{
            if(!isFragmentDisplayed){
                displayFragment()
            }else{
                closeFragment()
            }
        }

        //homework
        next_button.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    private fun displayFragment(){
        val simpleFragment = SimpleFragment2.newInstance(mRadioButtonChoice)
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment2, simpleFragment)
                .addToBackStack(null)
                .commit()

        open_button.text = getText(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment(){
        val simpleFragment = SimpleFragment2.newInstance(mRadioButtonChoice)
        if(simpleFragment != null){
            supportFragmentManager.beginTransaction()
                    .remove(simpleFragment)
                    .commit()
        }
        open_button.text = getText(R.string.open)
        isFragmentDisplayed = false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(STATE_FRAGMENT,isFragmentDisplayed)
        super.onSaveInstanceState(outState)
    }
}
