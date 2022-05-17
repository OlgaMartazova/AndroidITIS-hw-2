package com.itis.androiditis_hw_2.domain.usecase

import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.domain.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.rxjava3.core.Single
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.DisplayName

class GetCharacterByIdUseCaseTest {
    @MockK
    lateinit var repository: CharacterRepository

    lateinit var useCase: GetCharacterByIdUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = spyk(GetCharacterByIdUseCase(repository))
    }

    @Test
    @DisplayName("Test the value nickname by the key as id")
    operator fun invoke() {
        //arrange
        val expectedId = 1
        val expectedName = "nickname"
        val expectedCharacter = mockk<Person> { every { nickname } returns expectedName }

        //act
        every { repository.getCharacterById(expectedId) } returns Single.just(expectedCharacter)
        val result = useCase.invoke(expectedId)

        //assert
        assertEquals(
            expectedName,
            result.blockingGet().nickname
        )
    }
}
