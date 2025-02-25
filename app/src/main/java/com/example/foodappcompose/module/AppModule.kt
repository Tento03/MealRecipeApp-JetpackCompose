package com.example.foodappcompose.module

import android.content.Context
import androidx.room.Room
import com.example.foodappcompose.api.MealApi
import com.example.foodappcompose.favorite.FavoriteDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesRetrofit():Retrofit=Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun providesMealApi(retrofit: Retrofit)=retrofit.create(MealApi::class.java)

    @Singleton
    @Provides
    fun providesFavoriteDB(@ApplicationContext app:Context):FavoriteDB=Room.databaseBuilder(
        app.applicationContext,FavoriteDB::class.java,"MealComposer"
    ).build()

    @Singleton
    @Provides
    fun providesFavoriteDao(db: FavoriteDB)=db.FavoriteDao()
}