package com.itis.androiditis_hw_2.presentation.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.androiditis_hw_2.databinding.ItemPersonBinding
import com.itis.androiditis_hw_2.domain.entity.Person

class CharacterHolder(
    private val binding: ItemPersonBinding,
    private val glide: RequestManager,
    private val selectItem: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    private var person: Person? = null

    init {
        itemView.setOnClickListener {
            person?.id?.also(selectItem)
        }
    }

    fun bind(item: Person) {
        person = item
        with(binding) {
            glide.load(item.img).into(ivPhoto)
            tvFullName.text = item.name
            tvNickname.text = item.nickname
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            glide: RequestManager,
            selectItem: (Int) -> Unit
        ) = CharacterHolder(
            ItemPersonBinding.inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            ),
            glide,
            selectItem
        )
    }
}
