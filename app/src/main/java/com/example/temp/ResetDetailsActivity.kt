package com.example.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.temp.email
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.android.synthetic.main.activity_reset_details.*
import kotlinx.android.synthetic.main.activity_reset_details2.*
import org.json.JSONException
import org.json.JSONObject

class ResetDetailsActivity : AppCompatActivity() {

    private val URLstring = "http://webstore.apsit.org.in/engg_admissions/send_data.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_details)

        val phn = intent.getStringExtra("Phone")
        if(phn != null){
            resetPhone.visibility = View.GONE
            resetPass.visibility = View.VISIBLE
            confirmation.setOnClickListener {
                if (password.text.toString().length >= 8 && password.text.toString().equals(password2.text.toString())) {
                    addToDatabase()
                    Toast.makeText(this, "Password reset! Login to continue", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }
                else if (password.text.toString().length < 8) {
                    Toast.makeText(this, "Password must be at least 8 characters long", Toast.LENGTH_SHORT).show()
                }
                else if (!(password.text.toString().equals(password2.text.toString()))) {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else {
            button.setOnClickListener {
                if (phone.text.toString().length == 10) {
                    if (getFromDatabase(phone.text.toString())) {
                        val intent0 = Intent(this, OTPActivity::class.java)
                        intent0.putExtra("ResetFlag", "1")
                        intent0.putExtra("Phone", phone.text.toString())
                        startActivity(intent0)
                    }
                    else {
                        Toast.makeText(this, "Phone Number not found", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Phone Number invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getFromDatabase(mob : String): Boolean {
        var rtn = false
        val stringRequest =
            StringRequest(
                Request.Method.GET, URLstring,
                Response.Listener { response ->
                    Log.d("strrrrr", ">>$response")
                    try { //getting the whole json object from the response
                        val obj = JSONObject(response)
                        if (obj.optString("success").toInt() == 1) {
                            val abc = mutableListOf<String>()
                            val dataArray = obj.getJSONArray("data")
                            for (i in 0 until dataArray.length()) {
                                val dataobj = dataArray.getJSONObject(i)
                                abc.add(dataobj.getString("Phone_Number"))
                            }
                            for (j in abc.indices) {
                                rtn = (abc[j].equals(mob))
                                if (rtn) {
                                    System.out.println("I am here")
                                    break
                                }
                            }
                        } else {
                            Toast.makeText(
                                this,
                                obj.optString("message") + "",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { error ->
                    //displaying the error in toast if occurrs
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT)
                        .show()
                })
        //creating a request queue
        val requestQueue = Volley.newRequestQueue(this)
        //adding the string request to request queue
        requestQueue.add(stringRequest)
        System.out.println("Returning : "+rtn)
        return rtn
    }

    private fun addToDatabase() {
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
                params.put("Phone_Number", phone.toString())
                params.put("Password", password.text.toString())
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }
}
