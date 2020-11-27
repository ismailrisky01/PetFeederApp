package com.example.petfeederapp.model

class Device(val id: String="", val jam:Jam){
    constructor() : this("", Jam())
}

class Jam(
    val jam1: String = "",
    val jam2: String = "",
    val jam3: String = "",
    val jam4: String = "",
    val jam5: String = "",
    val jam6: String = "",
    val jamNow: Boolean=false,
    val statusjam1:Boolean=false,
    val statusjam2:Boolean=false,
    val statusjam3:Boolean=false,
    val statusjam4:Boolean=false,
    val statusjam5:Boolean=false,
    val statusjam6:Boolean=false
){
    constructor():this("","","","","","",true,true,true,true,true,true,true)
}
