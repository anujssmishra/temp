package com.example.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_otp.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.KeyEvent
import android.view.View
import android.widget.Toast


class OTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        val context = this
        //moving cursor forward
        verification_code1.setOnKeyListener { _, keyCode, event ->
            if (keyCode in 7..16) {
                verification_code2.requestFocus()
            }
            false
        }
        verification_code2.setOnKeyListener { _, keyCode, event ->
            if (keyCode in 7..16) {
                verification_code3.requestFocus()
            }
            if (keyCode == 67) {
                verification_code1.requestFocus()
            }
            false
        }
        verification_code3.setOnKeyListener { _, keyCode, event ->
            if (keyCode in 7..16) {
                verification_code4.requestFocus()
            }
            if (keyCode == 67) {
                verification_code2.requestFocus()
            }
            false
        }
        verification_code4.setOnKeyListener { _, keyCode, event ->
            if (keyCode == 67) {
                verification_code3.requestFocus()
            }
            false
        }

        confirmation.setOnClickListener {
            if (verification_code1.text.toString().length > 0 &&
                verification_code2.text.toString().length > 0 &&
                verification_code3.text.toString().length > 0 &&
                verification_code4.text.toString().length > 0) {
                val intent = Intent(this, QualificationActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(context, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
