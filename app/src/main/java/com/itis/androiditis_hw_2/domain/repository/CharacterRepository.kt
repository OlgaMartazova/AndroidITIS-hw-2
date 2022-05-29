package com.itis.androiditis_hw_2.domain.repository

import com.itis.androiditis_hw_2.domain.entity.Character
import io.reactivex.rxjava3.core.Single

interface CharacterRepository {

    fun getCharacterById(id: Int): Single<Character>

    fun getAllCharacters(): Single<List<Character>>
}
