package com.example.temp

class DataInsert{

    var names : String = ""
    var phno : Long = 0
    var email1 : String = ""
    var password : String = ""
    var jee : Int = 0
    var cet : Int = 0
    var pref1 : String = ""
    var pref2 : String = ""

    constructor() {}

    fun registrationInsert(names : String, phno : Long, email : String, password : String){
        this.names = names
        this.phno = phno
        this.email1 = email
        this.password = password
    }

    fun qualificationInsert(jee : Int, cet : Int){
        this.jee = jee
        this.cet = cet
    }

    fun preferenceInsert(pref1 : String, pref2 : String){
        this.pref1 = pref1
        this.pref2 = pref2
    }


}