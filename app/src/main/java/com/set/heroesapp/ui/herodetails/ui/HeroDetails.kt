package com.set.heroesapp.ui.herodetails.ui

import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.set.heroesapp.R
import com.set.heroesapp.data.model.HeroDetails
import com.set.heroesapp.data.model.MarvelHeroes
import com.set.heroesapp.ui.base.BaseActivity
import com.set.heroesapp.ui.herodetails.viewModel.HeroDetailsViewModel
import kotlinx.android.synthetic.main.activity_hero_details.*


class HeroDetails : BaseActivity() {
    private var heroObj = MarvelHeroes()
    private lateinit var viewModel : HeroDetailsViewModel

    override fun setupView() {
        heroObj = (intent.getSerializableExtra("DETAILS") as? MarvelHeroes)!!
        viewModel = ViewModelProviders.of(this).get(HeroDetailsViewModel::class.java)

        viewModel.status.observe(this, Observer {
            when(it){
                1->{
                    progress_bar2.visibility = View.VISIBLE
                }
                2->{
                    Toast.makeText(this,"Some Error Occurs",Toast.LENGTH_LONG).show()
                }
                3->{
                    progress_bar2.visibility = View.GONE
                }
            }
        })

        viewModel.getHeroDetailsOnly().observe(this, Observer {
            if(it!=null){
                it?.let {
                    for(i in 0 until it.size){

                        if(it[i].id == heroObj.heroId){
                            drawUI(it[i])
                        }
                    }
                }
            }
        })

       /* viewModel.getHeroDetails().observe(this,{ heroDetailsResponse ->

            when(heroDetailsResponse.status){

                Status.ERROR ->{
                    progress_bar2.visibility = View.GONE
                    Toast.makeText(this,heroDetailsResponse.errorMessage,Toast.LENGTH_LONG).show()  9589417231
                }
                Status.LOADING ->{
                    progress_bar2.visibility = View.VISIBLE
                }
                Status.SUCCESS ->{
                    progress_bar2.visibility = View.GONE
                    heroDetailsResponse.data?.let {
                        for(i in 0 until it.heroDetails.size){

                            if(heroDetailsResponse.data!!.heroDetails[i].id == heroObj.heroId){
                                drawUI(heroDetailsResponse.data!!.heroDetails[i])
                            }
                        }
                    }
                }
            }

        })*/
       /*viewModel.getHeroDetails().observe(this, { heroDetailsResponse->
            heroDetailsResponse?.let { heroDetailsResponseNonNull->
                when(heroDetailsResponseNonNull.status){
                    ERROR -> {
                        Toast.makeText(this,heroDetailsResponseNonNull.message,Toast.LENGTH_LONG).show()
                    }
                    LOADING -> {
                        Toast.makeText(this,"Data Loading",Toast.LENGTH_LONG).show()
                    }
                    SUCCESS -> {
                        heroDetailsResponse.data?.let {
                            for(i in 0 until it.heroDetails.size){
                            if (heroDetailsResponse.data.heroDetails[i].id==heroObj.heroId){
                                drawUI(heroDetailsResponse.data.heroDetails[i])
                            }
                            }
                        }
                    }
                }

            }
        })*/
    }

    private fun drawUI(find: HeroDetails) {
        tv_hero_details_name.text = find.heroName
        hero_description.text = find.heroDetails
        Glide.with(this)
                .load(find.heroImage)
                .into(iv_image_heroes)


    }

    override fun getLayoutId(): Int = R.layout.activity_hero_details

}