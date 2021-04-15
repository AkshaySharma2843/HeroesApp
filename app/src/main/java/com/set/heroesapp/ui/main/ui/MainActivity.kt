package com.set.heroesapp.ui.main.ui

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.set.heroesapp.R
import com.set.heroesapp.data.model.MarvelHeroes
import com.set.heroesapp.ui.PostClickListener
import com.set.heroesapp.ui.base.BaseActivity
import com.set.heroesapp.ui.herodetails.ui.HeroDetails
import com.set.heroesapp.ui.main.adapter.MainAdapter
import com.set.heroesapp.ui.main.viewmodel.MainViewModel
import com.set.heroesapp.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), PostClickListener {

    private lateinit var viewModel : MainViewModel

    override fun setupView() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.status.observe(this, Observer {
            when(it){
                1 ->{
                    progress_bar.visibility = View.VISIBLE
                }

                2 ->{
                    progress_bar.visibility = View.GONE
                }

                3 ->{
                    progress_bar.visibility = View.GONE
                }

            }

        })

        viewModel.getHeroListOnly().observe(this, Observer {

            if(it!=null){
                rec_hero.apply {
                    layoutManager = LinearLayoutManager(this@MainActivity)
                    adapter = MainAdapter(it,this@MainActivity)
                }

            }
        })
       /* viewModel.getMarvelHeroes().observe(this,{heroResponse->

            when(heroResponse.status){
                Status.ERROR ->{
                    progress_bar.visibility = View.GONE
                    Toast.makeText(this,heroResponse.errorMessage,Toast.LENGTH_LONG).show()
                }

                Status.LOADING ->{
                    progress_bar.visibility = View.VISIBLE
                }

                Status.SUCCESS ->{
                    progress_bar.visibility = View.GONE
                    if(heroResponse.data!=null){
                        rec_hero.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = MainAdapter(heroResponse.data!!.marvelHeroes,this@MainActivity)
                        }
                    }

                }
            }

        })*/
        /*viewModel.getMarvelHeroes().observe(this,{heroResponse->
            when(heroResponse.status){
                Status.ERROR->{
                    Toast.makeText(this,heroResponse.message,Toast.LENGTH_LONG).show()
                }
                Status.LOADING->{
                    Toast.makeText(this,"Data Loading",Toast.LENGTH_LONG).show()

                }
                Status.SUCCESS->{
                    if(heroResponse.data!=null){
                        rec_hero.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = MainAdapter(heroResponse.data.marvelHeroes,this@MainActivity)
                        }
                    }

                }
            }

        })*/    }

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun clickedPost(marvelHeroes: MarvelHeroes) {
       val intent = Intent(this,HeroDetails::class.java)
        intent.putExtra("DETAILS",marvelHeroes)
        startActivity(intent)
    }


}