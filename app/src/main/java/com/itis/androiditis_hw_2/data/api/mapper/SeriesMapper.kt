package com.itis.androiditis_hw_2.data.api.mapper

import com.itis.androiditis_hw_2.data.api.model.CharacterResponse
import com.itis.androiditis_hw_2.data.api.model.CharacterResponseItem
import com.itis.androiditis_hw_2.domain.entity.Person
import javax.inject.Inject

class SeriesMapper @Inject constructor() {
    fun map(response: CharacterResponse): Person = mapToPerson(response[0])

    fun mapToList(response: CharacterResponse): List<Person> =
        response.map { item -> mapToPerson(item) }

    private fun mapToPerson(responseItem: CharacterResponseItem): Person = Person(
        id = responseItem.charId,
        name = responseItem.name,
        nickname = responseItem.nickname,
        img = responseItem.img,
        occupation = responseItem.occupation,
        status = responseItem.status,
        appearance = responseItem.appearance,
        portrayed = responseItem.portrayed
    )
}
