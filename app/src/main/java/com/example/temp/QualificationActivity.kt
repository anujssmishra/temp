package com.example.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_qualification.*

class QualificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qualification)

        qualNext.setOnClickListener {
            val intent = Intent(this, PreferenceActivity::class.java)
            startActivity(intent)
        }
    }
}
