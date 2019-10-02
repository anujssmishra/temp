package com.example.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.registration.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
//import androidx.core.app.ComponentActivity
//import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.EditText


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

        btn_next.setOnClickListener() {
            val mob: String = (findViewById(R.id.mobileNumber) as EditText).text.toString()
            val pass: String = (findViewById(R.id.password) as EditText).text.toString()
            if ((mob.trim().length<=0) or (pass.trim().length<=0))
                Toast.makeText(applicationContext, "Username or Password not specified!", Toast.LENGTH_SHORT).show()
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
