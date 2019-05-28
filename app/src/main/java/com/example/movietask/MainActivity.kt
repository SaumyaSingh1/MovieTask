package com.example.movietask

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fragmentlist = arrayListOf<Fragment>()
        fragmentlist.add(TopRatedfragment())
        fragmentlist.add(LatestMovieFragment())

        val pagerAdapter = PagerAdapter(fragmentlist, supportFragmentManager)

        viewpager.adapter = com.example.movietask.PagerAdapter(fragmentlist, supportFragmentManager)
          tablayout.setupWithViewPager(viewpager)

    }
}

