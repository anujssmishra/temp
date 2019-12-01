package com.example.temp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_qualification.*

class QualificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qualification)

        val intent = Intent(this, PreferenceActivity::class.java)
        var context = this
        qualNext.setOnClickListener {
            if(jeemarks.text.toString().length>0 && cetmarks.text.toString().length>0){
                var ob = DataInsert()
                ob.qualificationInsert(jeemarks.text.toString().toInt(), cetmarks.text.toString().toInt())
                var db = DatabaseHelper(context)
                db.insertQualificationData(ob)

                startActivity(intent)
            }
            else{
                Toast.makeText(context, "Please fill all the details!", Toast.LENGTH_SHORT).show()
            }
        }

        qualSkip.setOnClickListener {
            startActivity(intent)
        }
    }
}
