package com.example.temp

class DataInsert{

    var names : String = ""
    var phno : Long = 0
    var email : String = ""
    var password : String = ""
    var jee : Int = 0
    var cet : Int = 0
    var pref1 : String = ""
    var pref2 : String = ""

    constructor(names : String, phno : Long, email : String, password : String, jee : Int, cet : Int, pref1 : String, pref2 : String){
        this.names = names
        this.phno = phno
        this.email = email
        this.password = password
        this.jee = jee
        this.cet = cet
        this.pref1 = pref1
        this.pref2 = pref2
    }
}