package com.example.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_otp.*

class OTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        confirmation.setOnClickListener {
            val intent = Intent(this, QualificationActivity::class.java)
            startActivity(intent)
        }
    }
}
