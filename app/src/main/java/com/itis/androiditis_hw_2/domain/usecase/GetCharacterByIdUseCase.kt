package com.itis.androiditis_hw_2.domain.usecase

import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.domain.repository.CharacterRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetCharacterByIdUseCase @Inject constructor(
    private val repository: CharacterRepository
) {
    operator fun invoke (
        id: Int
    ): Single<Person> = repository.getCharacterById(id)
        .subscribeOn(Schedulers.io())
}
