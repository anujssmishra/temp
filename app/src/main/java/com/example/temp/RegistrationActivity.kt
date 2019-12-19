package com.example.temp

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_registration.*
import org.json.JSONException
import org.json.JSONObject

class RegistrationActivity : AppCompatActivity() {

    private val URLstring = "http://webstore.apsit.org.in/engg_admissions/send_data.php"
    var genderVal : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val b1 = findViewById<Button>(R.id.btn1)
        val b2 = findViewById<Button>(R.id.btn2)
        val b3 = findViewById<Button>(R.id.btn3)

        btn1.setOnClickListener {
            b1.setBackgroundResource(R.drawable.gender_on_click)
            b2.setBackgroundResource(R.drawable.edit_text_background)
            b3.setBackgroundResource(R.drawable.edit_text_background)
            b1.setTextColor(Color.parseColor("#FFFFFF"))
            b2.setTextColor(Color.parseColor("#80FFFFFF"))
            b3.setTextColor(Color.parseColor("#80FFFFFF"))
            genderVal = "Male"
        }
        btn2.setOnClickListener {
            b1.setBackgroundResource(R.drawable.edit_text_background)
            b2.setBackgroundResource(R.drawable.gender_on_click)
            b3.setBackgroundResource(R.drawable.edit_text_background)
            b2.setTextColor(Color.parseColor("#FFFFFF"))
            b1.setTextColor(Color.parseColor("#80FFFFFF"))
            b3.setTextColor(Color.parseColor("#80FFFFFF"))
            genderVal = "Female"
        }
        btn3.setOnClickListener {
            b1.setBackgroundResource(R.drawable.edit_text_background)
            b2.setBackgroundResource(R.drawable.edit_text_background)
            b3.setBackgroundResource(R.drawable.gender_on_click)
            b3.setTextColor(Color.parseColor("#FFFFFF"))
            b1.setTextColor(Color.parseColor("#80FFFFFF"))
            b2.setTextColor(Color.parseColor("#80FFFFFF"))
            genderVal = "Others"
        }

        var TandC : String? = null
        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        checkBox?.setOnCheckedChangeListener { buttonView, isChecked ->
            TandC = (if (isChecked) "1" else "0")
        }

        val context = this
        newRegistration.setOnClickListener {

            //checking if the fields are empty
            if (name.text.toString().length > 0 &&
                genderVal!!.length > 0 &&
                mobileNumber2.text.toString().length > 0 &&
                email.text.toString().length > 0 &&
                newPassword1.text.toString().length > 0 &&
                TandC == "1") {

                //checking for the passwords to match
                if (newPassword1.text.toString().equals(newPassword2.text.toString()) && newPassword1.text.toString().length>=8 && newPassword1.text.toString().length<=32) {

                    //insert data into local database sqlite
                    var ob = DataInsert()
                    ob.registrationInsert(
                        name.text.toString(),
                        mobileNumber2.text.toString().toLong(),
                        email.text.toString(),
                        newPassword1.text.toString()
                    )
                    var db = DatabaseHelper(context)
                    db.insertRegistrationData(ob)

                    //starting OTP activity
                    val intent0 = Intent(this, OTPActivity::class.java)
                    intent0.putExtra("Phone", mobileNumber2.text.toString())

                    //Volley Request
                    val stringRequest = object : StringRequest(
                        Request.Method.POST, URLstring,
                        Response.Listener<String> { response ->
                            try {
                                val obj = JSONObject(response)
                                Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG)
                                    .show()
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        },
                        object : Response.ErrorListener {
                            override fun onErrorResponse(volleyError: VolleyError) {
                                Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG)
                                    .show()
                            }
                        }) {
                        @Throws(AuthFailureError::class)
                        override fun getParams(): Map<String, String> {
                            val params = HashMap<String, String>()
                            params.put("username", mobileNumber2.text.toString())
                            params.put("password", newPassword1.text.toString())
                            return params
                        }
                    }

                    if (requestJSON(mobileNumber2.text.toString(), email.text.toString())) {
                        addArtist()
                        startActivity(intent0)
                    }
                    else {
                        Toast.makeText(
                            this,
                            "Mobile Number or Email already exists!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                else if (!(genderVal!!.length > 0)) {
                    Toast.makeText(context, "Please specify Gender", Toast.LENGTH_SHORT).show()
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

        btn_home.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private fun addArtist() {
        val stringRequest = object : StringRequest(
            Request.Method.POST, EndPoints.URL_ROOT,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG)
                        .show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG)
                        .show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("Name", name.text.toString())
                params.put("Gender", genderVal.toString())
                params.put("Phone_Number", mobileNumber2.text.toString())
                params.put("Email", email.text.toString())
                params.put("Password", newPassword1.text.toString())
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }

//    private fun requestJSON(mob : String, e_mail : String): Boolean {
//        var rtn = true
//        System.out.println("At the start of volley")
//        //creating a request queue
//        val requestQueue = Volley.newRequestQueue(this@RegistrationActivity)
//        val stringRequest =
//            StringRequest(
//                Request.Method.GET, URLstring,
//                Response.Listener { response ->
//                    Log.d("strrrrr", ">>$response")
//                    try { //getting the whole json object from the response
//                        val obj = JSONObject(response)
//                        if (obj.optString("success").toInt() == 1) {
//                            val abc = mutableListOf<String>()
//                            val dataArray = obj.getJSONArray("data")
//                            for (i in 0 until dataArray.length()) {
//                                val dataobj = dataArray.getJSONObject(i)
//                                abc.add(dataobj.getString("Phone_Number"))
//                                abc.add(dataobj.getString("Email"))
//                            }
//                            for (j in abc.indices) {
//                                rtn = (abc[j].equals(mob) || abc.equals(e_mail))
//                                if (rtn) {
//                                    rtn = !rtn
//                                    break
//                                }
//                            }
//                        } else {
//                            Toast.makeText(
//                                this,
//                                obj.optString("message") + "",
//                                Toast.LENGTH_SHORT
//                            ).show()
//                        }
//                    } catch (e: JSONException) {
//                        e.printStackTrace()
//                    }
//                },
//                Response.ErrorListener { error ->
//                    //displaying the error in toast if occurrs
//                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT)
//                        .show()
//                })
//        //adding the string request to request queue
//        requestQueue.add(stringRequest)
//        return rtn
//    }
}
