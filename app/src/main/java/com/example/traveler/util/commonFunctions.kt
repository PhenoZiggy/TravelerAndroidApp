package com.example.traveler.util

import okhttp3.MediaType
import org.json.JSONObject

fun loginJsonObject(email : String , password : String): JSONObject {
    val jsonObject = JSONObject()
    jsonObject.put("nic", email)
    jsonObject.put("password", password)
    return jsonObject
}

val JSON_MEDIA_TYPE = MediaType.get("application/json")
