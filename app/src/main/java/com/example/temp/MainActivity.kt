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

        val context = this
        newRegistration.setOnClickListener {
            if (name.text.toString().length > 0 &&
                mobileNumber2.text.toString().length > 0 &&
                email.text.toString().length > 0 &&
                newPassword1.text.toString().length > 0) {
                if (newPassword1.text.toString().equals(newPassword2.text.toString()) && newPassword1.text.toString().length>=8 && newPassword1.text.toString().length<=32) {
                    var ob = DataInsert()
                    ob.registrationInsert(
                        name.text.toString(),
                        mobileNumber2.text.toString().toLong(),
                        email.text.toString(),
                        newPassword1.text.toString()
                    )
                    var db = DatabaseHelper(context)
                    db.insertRegistrationData(ob)
                    val intent = Intent(this, OTPActivity::class.java)
                    startActivity(intent)
                }
                else if(mobileNumber2.text.toString().length != 10){
                    Toast.makeText(context, "Mobile number too short", Toast.LENGTH_SHORT).show()
                }
                else if(newPassword1.text.toString().length < 8 || newPassword1.text.toString().length > 32){
                    Toast.makeText(context, "Password should be 8 to 32 characters long", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }


        }

        btn_next.setOnClickListener() {
            val intent = Intent(this, MapsActivity::class.java)
// To pass any data to next activity
//            intent.putExtra("keyIdentifier", value)
// start your next activity

            val mob: String = (findViewById(R.id.mobileNumber) as EditText).text.toString()
            val pass: String = (findViewById(R.id.password) as EditText).text.toString()
            if ((mob.trim().length <= 0) or (pass.trim().length <= 0))
                Toast.makeText(
                    applicationContext,
                    "Username or Password not specified!",
                    Toast.LENGTH_SHORT
                ).show()
            else {
//                val phno: String = mobileNumber.text.toString()
//                val password: String = password.text.toString()
                val ob = DatabaseHelper(context)
//                val check: Boolean = ob.checkLogin(phno.toLong(), password)
                if (ob.checkLogin(mobileNumber.text.toString().toLong(), password.text.toString()) == true) {
                    startActivity(intent)
                }
                else{
                    Toast.makeText(context, "User not found!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun showRegistration() {
        homeLayout.visibility = View.GONE
        registrationLayout.visibility = View.VISIBLE

    }

    private fun showHome() {
        registrationLayout.visibility = View.GONE
        homeLayout.visibility = View.VISIBLE
    }

}
