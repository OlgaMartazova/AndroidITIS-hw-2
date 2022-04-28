package com.itis.androiditis_hw_2.data.impl

import com.itis.androiditis_hw_2.data.api.SeriesApi
import com.itis.androiditis_hw_2.data.api.mapper.SeriesMapper
import com.itis.androiditis_hw_2.domain.repository.CharacterRepository
import com.itis.androiditis_hw_2.domain.entity.Person
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: SeriesApi,
    private val mapper: SeriesMapper,
) : CharacterRepository {
    override fun getCharacterById(
        id: Int
    ): Single<Person> = api.getCharacterById(id)
        .map {
            mapper.map(it)
        }

    override fun getAllCharacters(): Single<List<Person>> = api.getAllCharacters()
        .map {
            mapper.mapToList(it)
        }
}
