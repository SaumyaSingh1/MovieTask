package com.example.movietask

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.eachrow.view.*

class MovieAdapter(val context: Context, val list: ArrayList<ResultsItem>) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieHolder {
        return MovieHolder(LayoutInflater.from(context).inflate(R.layout.eachrow, p0, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: MovieHolder, p1: Int) {
        val current = list[p1]
        try {
            with(p0.itemView) {
                movietitle.text = current.title.toString()
                hindi.text = current.originalTitle.toString()
                rate.text = current.popularity.toString()
                textoverview.text = current.overview.toString()
                Picasso.get().load(current.backdropPath).placeholder(R.drawable.ic_photo_black_24dp).into(poster)
            }
        }catch ( ex : Exception){
            ex.printStackTrace()
        }
    }
    class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }


}