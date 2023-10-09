package com.example.traveler.util

import okhttp3.MediaType
import org.json.JSONObject

fun loginJsonObject(email : String , password : String): JSONObject {
    val jsonObject = JSONObject()
    jsonObject.put("nic", email)
    jsonObject.put("password", password)
    return jsonObject
}

fun updateProfileObject(name : String , age : Int , gender : String): JSONObject {
    val jsonObject = JSONObject()
    jsonObject.put("name", name)
    jsonObject.put("age", age)
    jsonObject.put("userType", "Customer")
    jsonObject.put("userGender", gender)
    return jsonObject
}

fun createUser(name : String , age : Int , gender :String , nic :String): JSONObject {
    val jsonObject = JSONObject()
    jsonObject.put("name", name)
    jsonObject.put("age", age)
    jsonObject.put("nic", nic)
    jsonObject.put("userType", "Customer")
    jsonObject.put("userGender", gender)
    return jsonObject
}

val JSON_MEDIA_TYPE = MediaType.get("application/json")
