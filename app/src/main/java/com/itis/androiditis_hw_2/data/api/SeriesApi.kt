package com.itis.androiditis_hw_2.data.api

import com.itis.androiditis_hw_2.data.api.model.CharacterResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface SeriesApi {
    @GET("characters/{id}")
    fun getCharacterById(@Path("id") id: Int): Single<CharacterResponse>

    @GET("characters")
    fun getAllCharacters(): Single<CharacterResponse>
}
