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
                val textlength1: Int = verification_code1.getText().length
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
                val textlength2: Int = verification_code2.getText().length
                if (textlength2 >= 1) {
                    verification_code3.requestFocus()
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
                val textlength2: Int = verification_code3.getText().length
                if (textlength2 >= 1) {
                    verification_code4.requestFocus()
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

        confirmation.setOnClickListener {
            if (verification_code1.text.toString().length > 0 &&
                verification_code2.text.toString().length > 0 &&
                verification_code3.text.toString().length > 0 &&
                verification_code4.text.toString().length > 0) {
                val intent = Intent(this, QualificationActivity::class.java)
                startActivity(intent)
            }
            else{
                val context = this
                Toast.makeText(context, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
