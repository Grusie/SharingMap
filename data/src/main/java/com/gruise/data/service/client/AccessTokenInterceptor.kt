package com.gruise.data.service.client

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

object AccessTokenInterceptor : Interceptor {

    private const val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEsImNyZWF0ZWRBdCI6IjIwMjUtMDgtMTBUMDc6MDY6MzYuMDU2NzEzIiwiaWF0IjoxNzIzMjQxNjU3LCJyb2xlIjoiVVNFUiIsImV4cCI6MjgwOTY0MTY1N30.MaGzlBXmFZpOdlCjfTzYxxN4F1CDs7aEuFl3Xmq25_Q"

    override fun intercept(chain: Interceptor.Chain): Response  = with(chain){
        val newRequest: Request =
            request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()

        proceed(newRequest)
    }
}