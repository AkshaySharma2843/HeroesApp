package com.set.heroesapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.set.heroesapp.R
import com.set.heroesapp.data.model.MarvelHeroes
import com.set.heroesapp.ui.PostClickListener


class MainAdapter(private val arrayList: MutableList<MarvelHeroes>, private val postClickListener: PostClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{

        const val MARVEL = 1
        const val DC = 2

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType== MARVEL){
            MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_marvel,parent,false))
        }
        else {
            DCViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_dc, parent, false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(arrayList[position].heroType){
            1->
            {
                val marvelHolder  = holder as MainViewHolder
                marvelHolder.bindData(arrayList[position],postClickListener)
            }
            2 ->
            {
                val dcHolder = holder as DCViewHolder
                dcHolder.bindData(arrayList[position],postClickListener)
            }

        }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemViewType(position: Int): Int {
         return when(arrayList[position].heroType){
            1->{
                MARVEL
            }
            else -> {
                DC
            }
        }
    }

}
