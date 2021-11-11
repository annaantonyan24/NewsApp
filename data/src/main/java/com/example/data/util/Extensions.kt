package com.example.data.util

import com.example.core.CallException
import com.example.core.Result
import retrofit2.Response

suspend fun <R> makeApiCall(
    call: suspend () -> Result<R>,
    errorMessage: Int = 4567
) = try {
    call()
} catch (e: Exception) {
    Result.Error(CallException<Nothing>(errorMessage))
}

private const val HTTP_CODE_OK = 200

fun <R> analyzeResponse(response: Response<R>): Result<R> {

    return when(response.code()){
        HTTP_CODE_OK -> Result.Success(response.body())
        else -> Result.Error(CallException(response.code()))
    }

}