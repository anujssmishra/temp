package com.example.temp

//import androidx.core.app.ComponentActivity
//import androidx.core.app.ComponentActivity.ExtraData

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pdf.*


class MainActivity : AppCompatActivity() {

    private val URLstring = "http://webstore.apsit.org.in/engg_admissions/send_data.php"
    var genderVal : String? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        btn_next.setOnClickListener() {
            val intent = Intent(this, MapsActivity::class.java)

            val mob: String = (findViewById(R.id.mobileNumber) as EditText).text.toString()
            val pass: String = (findViewById(R.id.password) as EditText).text.toString()

            if ((mob.trim().length <= 0) or (pass.trim().length <= 0))
                Toast.makeText(
                    applicationContext,
                    "Username or Password not specified!",
                    Toast.LENGTH_SHORT
                ).show()
            else {
                val ob = DatabaseHelper(context)
                if (ob.checkLogin(mobileNumber.text.toString().toLong(), password.text.toString()) == true) {
//                    getUsers()
                    startActivity(intent)
                }
                else{
                    Toast.makeText(context, "User not found!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        register.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, ResetDetailsActivity::class.java))
        }

    }

//    fun getUsers() {
//        // Instantiate the RequestQueue.
//        val queue = Volley.newRequestQueue(this)
//        val url: String = "https://webstore.apsit.org.in/engg_admissions/send_data.php"
//
//        // Request a string response from the provided URL.
//        val stringReq = StringRequest(Request.Method.GET, url,
//            Response.Listener<String> { response ->
//
//                var strResp = response.toString()
//                val jsonObj: JSONObject = JSONObject(strResp)
//                val jsonArray: JSONArray = jsonObj.getJSONArray("data")
//                var str_user: String = ""
//                for (i in 0 until jsonArray.length()) {
//                    var jsonInner: JSONObject = jsonArray.getJSONObject(i)
//                    str_user = str_user + "\n" + jsonInner.get("Name")
//                }
//                System.out.println("response : $str_user ")
//            },
//            Response.ErrorListener { System.out.println("That didn't work!") })
//        queue.add(stringReq)
//    }

}
