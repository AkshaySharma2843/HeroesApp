package com.set.heroesapp.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.set.heroesapp.data.model.MarvelHeroes
import com.set.heroesapp.ui.PostClickListener
import kotlinx.android.synthetic.main.row_dc.view.*

class DCViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(marvelHeroes: MarvelHeroes, postClickListener: PostClickListener) {

        itemView.tv_name.text = marvelHeroes.nameOfHero
        itemView.tv_heroFrom.text  = marvelHeroes.heroFrom
        itemView.tv_type.text = marvelHeroes.heroType.toString()
        Glide.with(itemView.context)
                .load(marvelHeroes.imageOfHero)
                .into(itemView.iv_DcImage)

        itemView.setOnClickListener {
            postClickListener.clickedPost(marvelHeroes)
        }

    }
}