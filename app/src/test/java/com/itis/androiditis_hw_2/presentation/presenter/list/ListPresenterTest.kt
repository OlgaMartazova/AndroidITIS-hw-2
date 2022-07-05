package com.itis.androiditis_hw_2.presentation.presenter.list

import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.domain.repository.CharacterRepository
import com.itis.androiditis_hw_2.domain.usecase.GetAllCharactersUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ListPresenterTest {
    @MockK
    lateinit var useCase: GetAllCharactersUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `ListView$$State`

    lateinit var presenter: ListPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = spyk( ListPresenter(useCase))
        presenter.setViewState(viewState)
    }

    @Test
    fun onGetCharactersClick() {
        //arrange
        val expectedList = arrayListOf<Person>(
            mockk { every { id } returns 1 },
            mockk { every { id } returns 2 },
        )
        every { useCase.invoke() } returns Single.just(expectedList)

        //act
        presenter.onGetCharactersClick()
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        //assert
        verify {viewState.showListOfCharacters(expectedList)}
    }

    @Test
    fun onError() {
        //arrange
        val expectedList = arrayListOf<Person>(
            mockk { every { id } returns 150 },
            mockk { every { id } returns 152 },
        )
        val error = mockk<Throwable>()
        every { useCase.invoke() } returns Single.error(error)

        //act
        presenter.onGetCharactersClick()
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        //assert
        verify(inverse = true) {viewState.showListOfCharacters(expectedList)}
        verify {viewState.showError(error)}
    }
}
