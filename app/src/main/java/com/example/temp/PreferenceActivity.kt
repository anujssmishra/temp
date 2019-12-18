package com.example.temp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_preference.*
import org.json.JSONException
import org.json.JSONObject


class PreferenceActivity : AppCompatActivity() {

    var phn_no:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val intent3 = Intent(this, MapsActivity::class.java)
        val context = this
        prefNext.setOnClickListener {

            //inserting data into local database sqlite
            var ob = DataInsert()
            ob.preferenceInsert(preference1.toString(), preference2.toString())
            var db = DatabaseHelper(context)
            db.insertPreferenceData(ob)

            //starting new Activity : MapsActivity
            phn_no = intent.getStringExtra("Phone")?.toString()
            addToDatabase()
            startActivity(intent3)
        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.branch, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        preference1.adapter = adapter
        preference2.adapter = adapter

        prefSkip.setOnClickListener {
            startActivity(intent3)
        }
    }

    fun getValues(view: View){
        Toast.makeText(this, "Spinner 1 " + preference1.selectedItem.toString() +
                "\nSpinner 2 " + preference2.selectedItem.toString(), Toast.LENGTH_LONG).show()
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
                params.put("Phone_Number", phn_no.toString())
                params.put("Preference_1", ""+preference1.selectedItem.toString())
                params.put("Preference_2", ""+preference2.selectedItem.toString())
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)
    }

}
