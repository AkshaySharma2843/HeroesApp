package com.set.heroesapp.data.network

import androidx.lifecycle.LiveData
import com.set.heroesapp.data.model.HeroDetailsResponse
import com.set.heroesapp.data.model.HeroResponse
import com.set.heroesapp.utils.Resource
import retrofit2.http.GET

interface HeroServices {

    @GET(Endpoint.MARVEL_HEROES)
    fun getMarvelHeroes() : LiveData<Resource<HeroResponse>>

    @GET(Endpoint.HEROES_DETAILS)
    fun getHeroesDetails() : LiveData<Resource<HeroDetailsResponse>>

}