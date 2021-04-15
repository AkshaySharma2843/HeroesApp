package com.set.heroesapp.data.local

import androidx.lifecycle.LiveData
import com.set.heroesapp.data.model.HeroDetailsResponse
import com.set.heroesapp.data.network.HeroServices
import com.set.heroesapp.data.network.RetrofitInstance
import com.set.heroesapp.utils.NetworkBoundResource
import com.set.heroesapp.utils.Resource

class DetailsRepository() {

    private val heroDetailsService : HeroServices = RetrofitInstance.createRetrofitInstance().create(HeroServices::class.java)

    fun getHeroesDetailsData() : LiveData<Resource<HeroDetailsResponse>>{

        return object : NetworkBoundResource<HeroDetailsResponse>() {
            override fun createCall(): LiveData<Resource<HeroDetailsResponse>> {
                return heroDetailsService.getHeroesDetails()
            }

        }.asLiveData()


    }
}