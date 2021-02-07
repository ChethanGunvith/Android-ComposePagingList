package com.chethan.pagingSample.di

import com.chethan.pagingSample.API_REST_URL
import com.chethan.pagingSample.NETWORK_TIME_OUT
import com.chethan.pagingSample.api.NetWorkApi
import com.chethan.pagingSample.repository.VenueRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun provideGithubService(): NetWorkApi {


        val builder = OkHttpClient().newBuilder()
        builder.readTimeout(NETWORK_TIME_OUT.toLong(), TimeUnit.SECONDS)
        builder.connectTimeout(NETWORK_TIME_OUT.toLong(), TimeUnit.SECONDS)

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(loggingInterceptor)


        val client = builder.build()

        val gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(API_REST_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(NetWorkApi::class.java)
    }


    @Singleton
    @Provides
    fun providesVenueRepository(
        service: NetWorkApi
    ): VenueRepository = VenueRepository(service)
}
