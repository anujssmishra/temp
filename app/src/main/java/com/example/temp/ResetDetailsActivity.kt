package com.example.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_reset_details.*
import kotlinx.android.synthetic.main.activity_reset_details2.*

class ResetDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_details)

        val phn = intent.getStringExtra("Phone")
        System.out.println("Phone number received : "+phn)
        if(phn != null){
            System.out.println("I am inside of if statement")
            resetPhone.visibility = View.GONE
            resetPass.visibility = View.VISIBLE
            confirmation.setOnClickListener {
                Toast.makeText(this, "Password reset! Login to continue", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
        else {
            button.setOnClickListener {
                if (phone.text.toString().length == 10) {
                    val intent0 = Intent(this, OTPActivity::class.java)
                    intent0.putExtra("ResetFlag", "1")
                    intent0.putExtra("Phone", phone.text.toString())
                    startActivity(intent0)
                } else {
                    Toast.makeText(this, "Phone Number invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
