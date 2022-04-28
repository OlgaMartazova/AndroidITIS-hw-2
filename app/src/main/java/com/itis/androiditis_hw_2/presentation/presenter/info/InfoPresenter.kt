package com.itis.androiditis_hw_2.presentation.presenter.info

import com.itis.androiditis_hw_2.domain.usecase.GetCharacterByIdUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class InfoPresenter @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
) : MvpPresenter<InfoView>() {
    private val disposables = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
    }

    override fun attachView(view: InfoView?) {
        super.attachView(view)
    }

    override fun detachView(view: InfoView?) {
        super.detachView(view)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    fun onGetCharacterClick(personId: Int) {
        disposables += getCharacterByIdUseCase(personId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                viewState.showLoading()
            }
            .doAfterTerminate {
                viewState.hideLoading()
            }
            .subscribeBy(onSuccess = {
                viewState.showPersonInfo(it)
            }, onError = {
                viewState.showError(it)
            })
    }
}
