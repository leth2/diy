package com.example.android.fragmentexample2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var isFragmentDisplayed = false
    private val STATE_FRAGMENT = "state_of_fragment"

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
        val simpleFragment = SimpleFragment2.newInstance()
        supportFragmentManager.beginTransaction()
                .add(R.id.fragment2, simpleFragment)
                .addToBackStack(null)
                .commit()

        open_button.text = getText(R.string.close)
        isFragmentDisplayed = true
    }

    private fun closeFragment(){
        val simpleFragment = supportFragmentManager.findFragmentById(R.id.fragment2)
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
