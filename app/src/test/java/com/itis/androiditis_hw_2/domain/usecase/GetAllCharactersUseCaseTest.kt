package com.itis.androiditis_hw_2.domain.usecase

import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.domain.repository.CharacterRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.BeforeClass
import org.junit.jupiter.api.extension.ExtendWith

import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

//@ExtendWith(MockKExtension::class)
@RunWith(JUnit4::class)
class GetAllCharactersUseCaseTest {

    @MockK
    lateinit var repository: CharacterRepository

    lateinit var useCase: GetAllCharactersUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = spyk(GetAllCharactersUseCase(repository))
    }

    @Test
    operator fun invoke() {
        //arrange
        val expectedList = arrayListOf<Person>(
            mockk { every { id } returns 1 },
            mockk { every { id } returns 2 },
        )
        every { repository.getAllCharacters() } returns Single.just(expectedList)

        //act
        val result = useCase.invoke()

        //assert
        assertEquals(
            1,
            result.blockingGet()[0].id
        )
        assertEquals(
            2,
            result.blockingGet()[1].id
        )
    }
}
