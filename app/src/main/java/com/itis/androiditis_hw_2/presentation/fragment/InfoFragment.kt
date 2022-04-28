package com.itis.androiditis_hw_2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.google.android.material.snackbar.Snackbar
import com.itis.androiditis_hw_2.R
import com.itis.androiditis_hw_2.databinding.FragmentInfoBinding
import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.presentation.presenter.info.InfoPresenter
import com.itis.androiditis_hw_2.presentation.presenter.info.InfoView
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class InfoFragment : MvpAppCompatFragment(), InfoView {
    private lateinit var binding: FragmentInfoBinding
    private lateinit var glide: RequestManager

    @Inject
    @InjectPresenter
    lateinit var presenter: InfoPresenter

    @ProvidePresenter
    fun providePresenter(): InfoPresenter = presenter

    private val personId: Int by lazy {
        arguments?.getInt("personId") ?: 0
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        glide = Glide.with(this)
        presenter.onGetCharacterClick(personId)
    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(
            binding.root,
            "Error. Check the Internet connection",
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun showLoading() {
        with(binding) {
            information.isVisible = false
            progress.isVisible = true
        }
    }

    override fun hideLoading() {
        with(binding) {
            progress.isVisible = false
            information.isVisible = true
        }
    }

    override fun showPersonInfo(person: Person) {
        with(binding) {
            glide.load(person.img).into(ivPhoto)
            tvFullName.text = "Name: ${person.name}"
            tvNickname.text = "Nickname: ${person.nickname}"
            tvStatus.text = "Status: ${person.status}"
            tvPortrayed.text = "Portrayed by ${person.portrayed}"
            tvOccupation.text = "Occupation: \n\t•\t${person.occupation.joinToString(separator = "\n\t•\t")}"
            tvAppearance.text = "Appeared in ${person.appearance.joinToString()} seasons"
        }
    }
}
