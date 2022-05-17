package com.itis.androiditis_hw_2.presentation.presenter.info

import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.domain.usecase.GetCharacterByIdUseCase
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before

import org.junit.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class InfoPresenterTest {

    @MockK
    lateinit var useCase: GetCharacterByIdUseCase

    @MockK(relaxUnitFun = true)
    lateinit var viewState: `InfoView$$State`

    lateinit var presenter: InfoPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setInitNewThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = spyk(InfoPresenter(useCase))
        presenter.setViewState(viewState)
    }

    @Test
    fun onGetCharacterClick() {
        //arrange
        val expectedId = 1
        val expectedName = "nickname"
        val expectedCharacter = mockk<Person> { every { nickname } returns expectedName }
        every { useCase.invoke(expectedId) } returns Single.just(expectedCharacter)

        //act
        presenter.onGetCharacterClick(expectedId)
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        //assert
        verify {viewState.showPersonInfo(expectedCharacter)}
    }

    @Test
    fun onGetCharacterClickException() {
        //arrange
        val expectedId = 150
        val error = mockk<Throwable>()
        every { useCase.invoke(expectedId) } returns Single.error(error)

        //act
        presenter.onGetCharacterClick(expectedId)
        verifyOrder {
            viewState.showLoading()
            viewState.hideLoading()
        }

        //assert
        verify(inverse = true) {viewState.showPersonInfo(any())}
        verify {viewState.showError(error)}
    }
}
