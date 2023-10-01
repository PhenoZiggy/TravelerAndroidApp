package com.example.traveler.data.network.responses

import com.example.traveler.util.ApiException
import retrofit2.Response

abstract class SafeApiRequest {
    //using suspend functions for async
    suspend fun<T : Any>apiRequest(call : suspend ()-> Response<T>) : T{
        val response = call.invoke()
        val message = StringBuilder()

        if (response.isSuccessful){
            return response.body()!!
        }else {
            val err = response.errorBody()?.string()
            err?.let{
                try {
                    message.append(err)
                }catch (e:Exception){}
                message.append("\n")
            }
            message.append("Error Code: ${response.code()}")
            throw ApiException(message.toString())
        }
    }
}