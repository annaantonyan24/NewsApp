package com.example.data.util

import com.example.core.CallException
import com.example.core.ActionResult
import retrofit2.Response

suspend fun <R> makeApiCall(
    call: suspend () -> ActionResult<R>,
    errorMessage: Int = 4567
) = try {
    call()
} catch (e: Exception) {
    ActionResult.Error(CallException<Nothing>(errorMessage))
}

private const val HTTP_CODE_OK = 200

fun <R> analyzeResponse(response: Response<R>): ActionResult<R> {

    return when(response.code()){
        HTTP_CODE_OK -> ActionResult.Success(response.body())
        else -> ActionResult.Error(CallException(response.code()))
    }

}