package com.example.temp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_qualification.*
import org.json.JSONException
import org.json.JSONObject

class QualificationActivity : AppCompatActivity() {

    var phn_no : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qualification)

        val intent2 = Intent(this, PreferenceActivity::class.java)
        var context = this
        qualNext.setOnClickListener {

            if(jeemarks.text.toString().length>0 && cetmarks.text.toString().length>0){

                //inserting data into local database sqlite
                var ob = DataInsert()
                ob.qualificationInsert(jeemarks.text.toString().toInt(), cetmarks.text.toString().toInt())
                var db = DatabaseHelper(context)
                db.insertQualificationData(ob)

                //starting new activity : PreferenceActivity
                phn_no = intent.getStringExtra("Phone")?.toString()
                intent2.putExtra("Phone", phn_no)
                addToDatabase()
                startActivity(intent2)
            }
            else{
                Toast.makeText(context, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }
        }

        qualSkip.setOnClickListener {
            startActivity(intent2)
        }
    }

    fun addToDatabase() {
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
                System.out.println("Phone number in Qual : "+phn_no.toString())
                params.put("Phone_Number", phn_no.toString())
                params.put("JEE_Marks", jeemarks.text.toString())
                params.put("MHTCET_Marks", cetmarks.text.toString())
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }
}
