package com.set.heroesapp.data.local

import androidx.lifecycle.LiveData
import com.set.heroesapp.data.model.HeroResponse
import com.set.heroesapp.data.network.HeroServices
import com.set.heroesapp.data.network.RetrofitInstance
import com.set.heroesapp.utils.NetworkBoundResource
import com.set.heroesapp.utils.Resource

class HeroRepository() {

    private val heroServices: HeroServices = RetrofitInstance.createRetrofitInstance().create(HeroServices::class.java)

    fun getMarvelHeroData(): LiveData<Resource<HeroResponse>> {
       return  object : NetworkBoundResource<HeroResponse>(){
             override fun createCall(): LiveData<Resource<HeroResponse>> {
                 return heroServices.getMarvelHeroes()
             }

         }.asLiveData()
    }
}