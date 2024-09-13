package com.gruise.data.remote

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class PlaceCokCallAdapterFactory: CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        check(returnType is ParameterizedType) { "Return type must be parameterized as Call<CustomResult<T>>" }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != Result::class.java) {
            return null
        }

        check(responseType is ParameterizedType) { "Response type must be parameterized as CustomResult<T>" }

        val successType = getParameterUpperBound(0, responseType)
        return PlaceCokCallAdapter<Any>(successType)
    }
}