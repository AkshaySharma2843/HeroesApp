package com.set.heroesapp.ui.main.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.set.heroesapp.data.model.MarvelHeroes
import com.set.heroesapp.ui.PostClickListener
import kotlinx.android.synthetic.main.row_marvel.view.*

class MainViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    fun bindData(marvelHeroes: MarvelHeroes, postClickListener: PostClickListener) {
        itemView.tv_heroName.text = marvelHeroes.nameOfHero
        itemView.tv_hero_from.text = marvelHeroes.heroFrom
        itemView.tv_heroType.text = marvelHeroes.heroType.toString()
        Glide.with(itemView.context)
                .load(marvelHeroes.imageOfHero)
                .into(itemView.iv_heroImage)

        itemView.setOnClickListener(View.OnClickListener {
            postClickListener.clickedPost(marvelHeroes)
        })
    }

}

