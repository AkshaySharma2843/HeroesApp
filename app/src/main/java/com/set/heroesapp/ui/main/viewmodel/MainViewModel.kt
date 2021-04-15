package com.set.heroesapp.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.set.heroesapp.data.local.DetailsRepository
import com.set.heroesapp.data.local.HeroRepository
import com.set.heroesapp.data.model.HeroResponse
import com.set.heroesapp.data.model.MarvelHeroes
import com.set.heroesapp.utils.Resource
import com.set.heroesapp.utils.Status

class MainViewModel : ViewModel() {

     private var heroRepository : HeroRepository = HeroRepository()
    var status  = MutableLiveData<Int>()

    fun getMarvelHeroes(): LiveData<Resource<HeroResponse>> = heroRepository.getMarvelHeroData()


    fun getHeroListOnly(): LiveData<MutableList<MarvelHeroes>>{
        val heroListLiveData = heroRepository.getMarvelHeroData()
        return Transformations.switchMap(heroListLiveData){
            extractHeroList(it)
        }
    }

    private fun extractHeroList(resource: Resource<HeroResponse>): MutableLiveData<MutableList<MarvelHeroes>>  {
        val liveData = MutableLiveData<MutableList<MarvelHeroes>>()
        when(resource.status){

            Status.LOADING ->{
                liveData.postValue(null)
                status.postValue(1)
            }
            Status.ERROR ->{
                liveData.postValue(null)
                status.postValue(2)
            }
            Status.SUCCESS ->{

                liveData.postValue(resource.data?.marvelHeroes)
                status.postValue(3)
            }
        }

        return liveData
    }

}