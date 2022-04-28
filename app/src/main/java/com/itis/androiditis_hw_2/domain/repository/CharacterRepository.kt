package com.itis.androiditis_hw_2.domain.repository

import com.itis.androiditis_hw_2.domain.entity.Person
import io.reactivex.rxjava3.core.Single

interface CharacterRepository {

    fun getCharacterById(id: Int): Single<Person>

    fun getAllCharacters(): Single<List<Person>>
}
