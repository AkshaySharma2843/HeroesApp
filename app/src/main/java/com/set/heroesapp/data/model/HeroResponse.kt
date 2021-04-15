package com.set.heroesapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HeroResponse(

    @SerializedName("heroes")
    @Expose
    var marvelHeroes : MutableList<MarvelHeroes>
)

data class MarvelHeroes (

    @SerializedName("id")
    @Expose
    var heroId : Int? = null,

    @SerializedName("name")
    @Expose
    var nameOfHero : String? = null,

    @SerializedName("hero_image")
    @Expose
    var imageOfHero : String? = null,

    @SerializedName("hero_from")
    @Expose
    var heroFrom : String? = null,

    @SerializedName("type")
    @Expose
    var heroType : Int? = null
) : Serializable


