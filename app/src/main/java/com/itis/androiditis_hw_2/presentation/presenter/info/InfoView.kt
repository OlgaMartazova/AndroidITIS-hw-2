package com.itis.androiditis_hw_2.presentation.presenter.info

import com.itis.androiditis_hw_2.domain.entity.Person
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface InfoView : MvpView {
    @Skip
    fun showError(throwable: Throwable)

    fun showLoading()

    fun hideLoading()

    fun showPersonInfo(person: Person)
}
