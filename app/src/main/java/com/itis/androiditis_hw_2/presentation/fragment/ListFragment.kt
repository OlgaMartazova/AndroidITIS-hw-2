package com.itis.androiditis_hw_2.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.itis.androiditis_hw_2.R
import com.itis.androiditis_hw_2.databinding.FragmentListBinding
import com.itis.androiditis_hw_2.domain.entity.Person
import com.itis.androiditis_hw_2.presentation.presenter.list.ListPresenter
import com.itis.androiditis_hw_2.presentation.presenter.list.ListView
import com.itis.androiditis_hw_2.presentation.rv.CharacterAdapter
import com.itis.androiditis_hw_2.presentation.rv.SpaceItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject

@AndroidEntryPoint
class ListFragment : MvpAppCompatFragment(), ListView {
    private lateinit var binding: FragmentListBinding
    private lateinit var characterAdapter: CharacterAdapter

    @Inject
    @InjectPresenter
    lateinit var presenter: ListPresenter

    @ProvidePresenter
    fun providePresenter(): ListPresenter = presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.onGetCharactersClick()
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
            rvCharacters.isVisible = false
            progress.isVisible = true
        }
    }

    override fun hideLoading() {
        with(binding) {
            progress.isVisible = false
            rvCharacters.isVisible = true
        }
    }

    override fun showListOfCharacters(characters: List<Person>) {
        context?.let { context ->
            val decorator = DividerItemDecoration(requireContext(), RecyclerView.VERTICAL)
            val spacing = SpaceItemDecorator(requireContext())
            characterAdapter =
                CharacterAdapter(characters.take(57) as ArrayList<Person>, Glide.with(context)) { person ->
                    navigateToInfoScreen(person)
                }
            binding.rvCharacters.run {
                adapter = characterAdapter
                addItemDecoration(decorator)
                addItemDecoration(spacing)
            }
        }
    }

    private fun navigateToInfoScreen(personId: Int) {
        val bundle = Bundle().apply {
            putInt("personId", personId)
        }
        findNavController().navigate(R.id.action_listFragment_to_infoFragment, bundle)
    }
}
