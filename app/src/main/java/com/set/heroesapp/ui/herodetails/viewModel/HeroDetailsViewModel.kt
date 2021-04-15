package com.set.heroesapp.ui.herodetails.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.set.heroesapp.data.local.DetailsRepository
import com.set.heroesapp.data.model.HeroDetails
import com.set.heroesapp.data.model.HeroDetailsResponse
import com.set.heroesapp.utils.Resource
import com.set.heroesapp.utils.Status

class HeroDetailsViewModel : ViewModel() {
     private var detailsRepository: DetailsRepository = DetailsRepository()
      var status  = MutableLiveData<Int>()

    fun getHeroDetails(): LiveData<Resource<HeroDetailsResponse>>{
       return detailsRepository.getHeroesDetailsData()
    }


    fun getHeroDetailsOnly(): LiveData<MutableList<HeroDetails>> {
        val heroDetailsLiveData = detailsRepository.getHeroesDetailsData()
       return Transformations.switchMap(heroDetailsLiveData){
            extractHeroDetailFromResponse(it)
        }
    }


    private fun extractHeroDetailFromResponse(resource: Resource<HeroDetailsResponse>): MutableLiveData<MutableList<HeroDetails>> {
        val liveData = MutableLiveData<MutableList<HeroDetails>>()
        when(resource.status){
            Status.LOADING -> {
                liveData.postValue(null)
                status.postValue(1)

            }
            Status.ERROR -> {
                liveData.postValue(null)
                status.postValue(2)


            }
            Status.SUCCESS -> {
                liveData.postValue(resource.data?.heroDetails)
                status.postValue(3)


            }
        }
        return liveData
    }
}