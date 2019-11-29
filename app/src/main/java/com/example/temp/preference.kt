package com.example.temp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_preference.*

class preference : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        val adapter = ArrayAdapter.createFromResource(this, R.array.branch, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        preference1.adapter = adapter
        preference2.adapter = adapter
    }

    fun getValues(view: View){
        Toast.makeText(this, "Spinner 1 " + preference1.selectedItem.toString() +
                "\nSpinner 2 " + preference2.selectedItem.toString(), Toast.LENGTH_LONG).show()
    }
}
