package com.example.temp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.temp.DataInsert as DataInsert

const val dbname = "DTE_user_data"
const val table = "Registration"
const val names = "Name"
const val phno = "Phone_Number"
const val email = "Email"
const val password = "Password"
const val jee = "JEE_Marks"
const val cet = "MHTCET_Marks"
const val pref1 = "Preference_1"
const val pref2 = "Preference_2"


class DatabaseHelper(var context: Context):SQLiteOpenHelper(context, dbname, null, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE "+ table +" (" +
                names + " VARCHAR(256)," +
                phno + " LONG," +
                email + " VARCHAR(256)," +
                password + " VARCHAR(256)," +
                jee + " INTEGER," +
                cet + " INTEGER," +
                pref1 + " VARCHAR(256)," +
                pref2 + " VARCHAR(256))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists Registration")
    }

    fun insertRegistrationData(user : DataInsert) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(names, user.names)
        cv.put(phno, user.phno)
        cv.put(email, user.email1)
        cv.put(password, user.password)
        var result = db.insert(table, null, cv)
        db.close()
//        if(result == -1.toLong()){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
//        }
    }

    fun insertQualificationData(user : DataInsert) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(jee, user.jee)
        cv.put(cet, user.cet)
        var result = db.insert(table, null, cv)
        db.close()
//        if(result == -1.toLong()){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
//        }
    }

    fun insertPreferenceData(user : DataInsert) {
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(pref1, user.pref1)
        cv.put(pref2, user.pref2)
        var result = db.insert(table, null, cv)
        db.close()
//        if(result == -1.toLong()){
//            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
//        }
//        else{
//            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
//        }
    }

    fun checkLogin(phno : Long, password : String): Boolean {
        val db = this.writableDatabase
        val query = "select * from Registration where Phone_Number='$phno' and Password='$password'"
        val cursor = db.rawQuery(query, null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        else{
            cursor.close()
            return true
        }
    }

}
