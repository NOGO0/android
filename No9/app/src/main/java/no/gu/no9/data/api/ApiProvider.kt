package no.gu.no9.data.api

import android.content.Context
import android.content.SharedPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiProvider {
    private var BASE_URL = "https://af01-106-101-11-34.ngrok-free.app"

    private lateinit var sharedPreferences: SharedPreferences

    /**
     * this function save token with shared Preference
     * @param context
     */
    fun initialize(context: Context) {
        sharedPreferences = context.getSharedPreferences("my_shared_prefs", Context.MODE_PRIVATE)
    }


    /**
     * this function show http logs
     */
    private fun getLoggingInterceptor() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    /**
     * Returns a configured Retrofit instance for network requests with logging, token authentication,
     * and Gson serialization/deserialization
     * @return Configured Retrofit instance
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .addInterceptor(getTokenInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun noTokenRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(getLoggingInterceptor())
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    /**
     * this function puts a token in the header
     * @return interceptor that include header token
     */
    private fun getTokenInterceptor(): Interceptor {
        return Interceptor { chain ->
            val token = sharedPreferences.getString("token", "") ?: ""
            val request = chain.request().newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer $token"
                )
                .build()
            chain.proceed(request)
        }
    }

    fun authApi(): AuthApi = noTokenRetrofit().create(AuthApi::class.java)

    fun smsSend(): SmsApi = noTokenRetrofit().create(SmsApi::class.java)

    fun imageApi(): ImageApi = getRetrofit().create(ImageApi::class.java)

    fun feedApi(): FeedApi = getRetrofit().create(FeedApi::class.java)

    fun applyApi(): ApplyApi = getRetrofit().create(ApplyApi::class.java)

    fun gptApi(): GptApi = getRetrofit().create(GptApi::class.java)
}