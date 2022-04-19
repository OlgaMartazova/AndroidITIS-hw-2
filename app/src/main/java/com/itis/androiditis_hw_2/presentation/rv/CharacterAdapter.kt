package com.itis.androiditis_hw_2.presentation.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.itis.androiditis_hw_2.domain.entity.Character

class CharacterAdapter (
    private val list: ArrayList<Character>,
    private val glide: RequestManager,
    private val selectItem: (Int) -> Unit
) : RecyclerView.Adapter<CharacterHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterHolder = CharacterHolder.create(parent, glide, selectItem)

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
