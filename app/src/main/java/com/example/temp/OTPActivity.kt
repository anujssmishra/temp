package com.example.temp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_otp.*


class OTPActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)

        //moving cursor forward
        verification_code1.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                val textlength1: Int = verification_code1.text.toString().length
                if (textlength1 >= 1) {
                    verification_code2.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable) { // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) { // TODO Auto-generated method stub
            }
        })

        verification_code2.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                val textlength2: Int = verification_code2.text.toString().length
                if (textlength2 >= 1) {
                    verification_code3.requestFocus()
                }
                else if(textlength2 == 0) {
                    verification_code2.setOnKeyListener { _, keyCode, event ->
                        if (keyCode == 67) {
                            verification_code1.requestFocus()
                        }
                        false
                    }
                }
            }

            override fun afterTextChanged(s: Editable) { // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) { // TODO Auto-generated method stub
            }
        })

        verification_code3.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int,
                count: Int
            ) {
                val textlength3: Int = verification_code3.text.toString().length
                if (textlength3 >= 1) {
                    verification_code4.requestFocus()
                }
                else if(textlength3 == 0) {
                    verification_code3.setOnKeyListener { _, keyCode, event ->
                        if (keyCode == 67) {
                            verification_code2.requestFocus()
                        }
                        false
                    }
                }
            }

            override fun afterTextChanged(s: Editable) { // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int, count: Int,
                after: Int
            ) { // TODO Auto-generated method stub
            }
        })

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

                //starting new activity : QualificationActivity
                val phn_no = intent.getStringExtra("Phone")?.toString()
                val flag = intent.getStringExtra("ResetFlag")
                System.out.println("Going well untill... and flag = "+flag)
                var intent1: Intent
                if (flag != null) {
                    intent1 = Intent(this@OTPActivity, ResetDetailsActivity::class.java)
                }
                else {
                    System.out.println("Starting qualification activity")
                    intent1 = Intent(this@OTPActivity, QualificationActivity::class.java)
                }
                intent1?.putExtra("Phone", phn_no)
                startActivity(intent1)
            }
            else{
                val context = this
                Toast.makeText(context, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
