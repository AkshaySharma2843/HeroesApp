package com.set.heroesapp.ui

import com.set.heroesapp.data.model.MarvelHeroes

interface PostClickListener {

    fun clickedPost(marvelHeroes: MarvelHeroes)
}