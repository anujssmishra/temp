package com.example.temp

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_otp.*
import kotlinx.android.synthetic.main.registration.btn_home


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

        newRegistration.setOnClickListener {
            val name: String = (findViewById(R.id.name) as EditText).text.toString()
            val mob: String = (findViewById(R.id.mobileNumber2) as EditText).text.toString()
            val email: String = (findViewById(R.id.email) as EditText).text.toString()
            val clgloc: String = (findViewById(R.id.clgLocation) as EditText).text.toString()
            val pass1: String = (findViewById(R.id.newPassword1) as EditText).text.toString()
            val pass2: String = (findViewById(R.id.newPassword2) as EditText).text.toString()
            if ((name.trim().length<=0) or (mob.trim().length<=0) or (email.trim().length<=0) or (clgloc.trim().length<=0) or (pass1.trim().length<=0) or (pass2.trim().length<=0))
                Toast.makeText(applicationContext, "Username or Password not specified!", Toast.LENGTH_SHORT).show()
            else
                showVerification()
        }

        btn_next.setOnClickListener() {
            val intent = Intent(this, MapsActivity::class.java)
// To pass any data to next activity
//            intent.putExtra("keyIdentifier", value)
// start your next activity

            val mob: String = (findViewById(R.id.mobileNumber) as EditText).text.toString()
            val pass: String = (findViewById(R.id.password) as EditText).text.toString()
            if ((mob.trim().length<=0) or (pass.trim().length<=0))
                Toast.makeText(applicationContext, "Username or Password not specified!", Toast.LENGTH_SHORT).show()
            else
                startActivity(intent)
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

    private fun showVerification(){
        registrationLayout.visibility=View.GONE
        verificationLayout.visibility=View.VISIBLE
    }

}
