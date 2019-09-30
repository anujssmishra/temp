package com.example.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.registration.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showHome()
        register.setOnClickListener {
            showRegistration()
        }
        btn_home.setOnClickListener {
            showHome()
        }

    }

    private fun showRegistration(){
        homeLayout.visibility=View.GONE
        registrationLayout.visibility=View.VISIBLE

    }

    private fun showHome(){
        registrationLayout.visibility=View.GONE
        homeLayout.visibility=View.VISIBLE
    }
}
