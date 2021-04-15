package com.set.heroesapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HeroDetailsResponse(

    @SerializedName("heroes_details")
    @Expose
    var heroDetails : MutableList<HeroDetails>

)
data class HeroDetails(

    @SerializedName("id")
    @Expose
    var id : Int? = null,

    @SerializedName("name")
    @Expose
    var heroName : String? = null,

    @SerializedName("hero_image")
    @Expose
    var heroImage : String? = null,

    @SerializedName("hero_details")
    @Expose
    var heroDetails : String? = null,

    ): Serializable
