package com.example.temp

//import androidx.core.app.ComponentActivity
//import androidx.core.app.ComponentActivity.ExtraData
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.registration.*
import org.json.JSONException
import org.json.JSONObject


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

            //checking if the fields are empty
            if (name.text.toString().length > 0 &&
                mobileNumber2.text.toString().length > 0 &&
                email.text.toString().length > 0 &&
                newPassword1.text.toString().length > 0) {

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
                    startActivity(intent0)
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
            addArtist()
        }

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
                    startActivity(intent)
                }
                else{
                    Toast.makeText(context, "User not found!", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    private fun addArtist() {

        val stringRequest = object : StringRequest(Request.Method.POST, EndPoints.URL_ROOT,
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
                params.put("Phone_Number", mobileNumber2.text.toString())
                params.put("Email", email.text.toString())
                params.put("Password", newPassword1.text.toString())
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
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
