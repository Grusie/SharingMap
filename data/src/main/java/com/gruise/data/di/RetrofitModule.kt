package com.gruise.data.di

import com.gruise.data.remote.PlaceCokCallAdapterFactory
import com.gruise.data.service.client.AccessTokenInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val BASE_URL = "https://40.82.145.61/"
    private val CONTENT_TYPE = "application/json".toMediaType()

    private const val NAVER_BASE_URL = "https://naveropenapi.apigw.ntruss.com/"
    private const val KAKAO_BASE_URL = "https://dapi.kakao.com/"

    @Singleton
    @Provides
    @Qualifiers.DefaultRetrofit
    fun provideRetrofit(
        @Qualifiers.DefaultInterceptor okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(PlaceCokCallAdapterFactory())
            .addConverterFactory(Json { ignoreUnknownKeys = true }.asConverterFactory(CONTENT_TYPE))
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Qualifiers.NaverRetrofit
    fun provideNaverRetrofit(@Qualifiers.NaverInterceptor okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(NAVER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    @Qualifiers.KakaoRetrofit
    fun provideKakaoRetrofit(@Qualifiers.KakaoInterceptor okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(KAKAO_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    /* @Singleton
     @Provides
     fun provideOkhttpClient(): OkHttpClient {
         return OkHttpClient.Builder()
             .run {
                 addInterceptor(
                     HttpLoggingInterceptor().apply {
                         level = HttpLoggingInterceptor.Level.BODY
                     },
                 )
                 addInterceptor(AccessTokenInterceptor)
                 build()
             }
     }*/
    @Singleton
    @Provides
    @Qualifiers.DefaultInterceptor
    fun unsafeOkhttpClient(): OkHttpClient {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<out java.security.cert.X509Certificate>?,
                authType: String?
            ) {

            }

            override fun checkServerTrusted(
                chain: Array<out java.security.cert.X509Certificate>?,
                authType: String?
            ) {

            }

            override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate>? {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val sslSocketFactory = sslContext.socketFactory

        val builder = OkHttpClient.Builder().run {
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                },
            )
            addInterceptor(AccessTokenInterceptor)
            sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            hostnameVerifier { hostname, session -> true }
            build()
        }

        return builder
    }

    @Singleton
    @Provides
    @Qualifiers.NaverInterceptor
    fun provideNaverOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .run {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    },
                )
                build()
            }
    }

    @Singleton
    @Provides
    @Qualifiers.KakaoInterceptor
    fun provideKakaoOkhttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .run {
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    },
                )
                build()
            }
    }
}