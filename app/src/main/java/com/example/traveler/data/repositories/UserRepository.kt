package com.example.traveler.data.repositories
import com.example.traveler.data.db.AppDatabase
import com.example.traveler.data.db.entities.User
import com.example.traveler.data.network.MyApi
import com.example.traveler.data.network.responses.AuthResponse
import com.example.traveler.data.network.responses.SafeApiRequest
import com.example.traveler.util.JSON_MEDIA_TYPE
import com.example.traveler.util.loginJsonObject
import com.example.traveler.util.updateProfileObject
import okhttp3.RequestBody


class UserRepository(
    private val api :MyApi,
    private val db : AppDatabase
) : SafeApiRequest() {

    suspend fun UserLogin(email : String , password : String): AuthResponse{
        val jsonObject = loginJsonObject(email , password)
        val requestBody = RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString())
        return apiRequest { api.userLogin(requestBody)}
    }

    suspend fun UserSignup(nic : String , password: String):AuthResponse{
        val jsonObject = loginJsonObject(nic , password)
        val requestBody = RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString())
        return apiRequest { api.userSignup(requestBody) }
    }

    suspend fun UserUpdate(name :String , age : Int , nic: String): AuthResponse {
        val jsonObject = updateProfileObject(name,age)
        val requestBody = RequestBody.create(JSON_MEDIA_TYPE, jsonObject.toString())
        return apiRequest { api.userUpdate(nic,requestBody) }
    }
    suspend fun saveUser(user: User)= db.getUserDao().upsert(user)
    fun getUser() = db.getUserDao().getUser()
}