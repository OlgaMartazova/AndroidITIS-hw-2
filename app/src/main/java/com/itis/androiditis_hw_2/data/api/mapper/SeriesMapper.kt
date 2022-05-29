package com.itis.androiditis_hw_2.data.api.mapper

import com.itis.androiditis_hw_2.data.api.model.CharacterResponse
import com.itis.androiditis_hw_2.data.api.model.CharacterResponseItem
import com.itis.androiditis_hw_2.domain.entity.Character
import javax.inject.Inject

class SeriesMapper @Inject constructor() {
    fun map(response: CharacterResponse): Character = mapToCharacter(response[0])

    fun mapToList(response: CharacterResponse): List<Character> =
        response.map { item -> mapToCharacter(item) }

    private fun mapToCharacter(responseItem: CharacterResponseItem): Character = Character(
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
