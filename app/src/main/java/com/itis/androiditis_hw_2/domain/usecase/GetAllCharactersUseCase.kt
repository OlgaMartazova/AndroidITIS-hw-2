package com.itis.androiditis_hw_2.domain.usecase

import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.domain.repository.CharacterRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke(): Single<List<Person>> =
        repository.getAllCharacters()
        .subscribeOn(Schedulers.io())
}
