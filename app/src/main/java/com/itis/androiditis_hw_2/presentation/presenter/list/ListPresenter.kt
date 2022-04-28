package com.itis.androiditis_hw_2.presentation.presenter.list

import com.itis.androiditis_hw_2.domain.usecase.GetAllCharactersUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
) : MvpPresenter<ListView>() {

    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun attachView(view: ListView?) {
        super.attachView(view)
    }

    override fun detachView(view: ListView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun onGetCharactersClick() {
        disposables += getAllCharactersUseCase()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showListOfCharacters(it)
            }, onError = {
                viewState.showError(it)
            })
    }
}
