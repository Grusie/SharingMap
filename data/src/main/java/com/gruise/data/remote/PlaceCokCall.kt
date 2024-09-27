package com.gruise.data.remote

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketException
import java.net.UnknownHostException


class PlaceCokCall<T>(
    private val callDelegate: Call<T>,
) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        return callDelegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callback.onResponse(
                            this@PlaceCokCall,
                            Response.success(Result.success(it)),
                        )
                    } ?: run {
                        if (response.code() == 204) {
                            callback.onResponse(
                                this@PlaceCokCall,
                                Response.success(Result.success(Unit as T)),
                            )
                        } else {
                            callback.onResponse(
                                this@PlaceCokCall,
                                Response.success(
                                    Result.failure(
                                        RemoteError.NullError(
                                            RESPONSE_BODY_IS_NULL
                                        )
                                    )
                                ),
                            )
                        }
                    }
                } else {
                    callback.onResponse(
                        this@PlaceCokCall,
                        Response.success(
                            Result.failure(
                                RemoteError.ApiError(
                                    response.code(),
                                    response.errorBody()?.toString()
                                )
                            )
                        ),
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val fetchState = when (t) {
                    is SocketException -> FetchState.BadInternet
                    is HttpException -> FetchState.ParseError
                    is UnknownHostException -> FetchState.WrongConnection
                    else -> FetchState.Fail
                }
                callback.onResponse(
                    this@PlaceCokCall,
                    Response.success(Result.failure(RemoteError.NetworkError(fetchState))),
                )
            }
        })
    }

    override fun clone(): Call<Result<T>> = PlaceCokCall(callDelegate.clone())
    override fun execute(): Response<Result<T>> =
        throw UnsupportedOperationException("ResponseCall does not support execute.")

    override fun isExecuted(): Boolean = callDelegate.isExecuted
    override fun cancel() = callDelegate.cancel()
    override fun isCanceled(): Boolean = callDelegate.isCanceled
    override fun request(): Request = callDelegate.request()
    override fun timeout(): Timeout = callDelegate.timeout()

    companion object {
        private const val RESPONSE_BODY_IS_NULL = "응답이 비어있습니다."
    }

}
