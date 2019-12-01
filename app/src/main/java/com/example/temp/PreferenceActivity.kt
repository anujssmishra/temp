package com.example.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.temp.email
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_preference.*
import kotlinx.android.synthetic.main.registration.*

class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val context = this
        prefNext.setOnClickListener {
            var ob = DataInsert()
            ob.preferenceInsert(preference1.toString(), preference2.toString())
            var db = DatabaseHelper(context)
            db.insertPreferenceData(ob)
        }

        val adapter = ArrayAdapter.createFromResource(this, R.array.branch, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        preference1.adapter = adapter
        preference2.adapter = adapter

        val intent = Intent(this, MapsActivity::class.java)
        prefNext.setOnClickListener {
            startActivity(intent)
        }
        prefSkip.setOnClickListener {
            startActivity(intent)
        }
    }

    fun getValues(view: View){
        Toast.makeText(this, "Spinner 1 " + preference1.selectedItem.toString() +
                "\nSpinner 2 " + preference2.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }
}
