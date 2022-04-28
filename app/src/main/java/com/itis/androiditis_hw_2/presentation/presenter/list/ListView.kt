package com.itis.androiditis_hw_2.presentation.presenter.list

import com.itis.androiditis_hw_2.domain.entity.Person
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface ListView : MvpView {

    @Skip
    fun showError(throwable: Throwable)

    fun showLoading()

    fun hideLoading()

    fun showListOfCharacters(characters: List<Person>)
}
