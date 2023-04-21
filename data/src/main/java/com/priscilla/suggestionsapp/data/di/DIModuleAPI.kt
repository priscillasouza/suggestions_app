package com.priscilla.suggestionsapp.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.priscilla.suggestionsapp.data.BuildConfig
import com.priscilla.suggestionsapp.data.infrastruture.remote.api.SuggestionsActivityAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {

    single { providerServiceSuggestionsActivityApi(get()) }

    single { provideGson() }

    single { provideOkHttpCliente() }

    single { provideConverterFactory(get()) }

    single { provideRetrofit(BuildConfig.API_URL, get(), get()) }

}

private fun provideGson(): Gson {
    return GsonBuilder()
        .create()
}

private fun provideOkHttpCliente(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()

    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideConverterFactory(gson: Gson): GsonConverterFactory {
    return GsonConverterFactory.create(gson)
}

private fun provideRetrofit(
    url: String,
    client: OkHttpClient,
    converterFactory: GsonConverterFactory,
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(converterFactory)
        .client(client)
        .build()
}

private fun providerServiceSuggestionsActivityApi(retrofit: Retrofit): SuggestionsActivityAPI {
    return retrofit.create(SuggestionsActivityAPI::class.java)
}




