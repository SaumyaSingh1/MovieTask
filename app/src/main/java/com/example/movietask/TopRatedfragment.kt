package com.example.movietask


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_top_ratedfragment.*
import okhttp3.*
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedfragment : Fragment() {

    private val topmovielist = arrayListOf<ResultsItem>()
    private val okHttpClient = OkHttpClient.Builder().build()
    private val gson = Gson()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_ratedfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://api.themoviedb.org/3/movie/top_rated?page=1&language=en-US&api_key=${resources.getString(
            R.string.api_key
        )}".trimIndent()
        recycle.layoutManager = LinearLayoutManager(context)
        val movieadapter = MovieAdapter(requireContext(), topmovielist)
        recycle.adapter = movieadapter

        val request = Request.Builder()
            .url(url)
            .build()
        val response = okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body()
                val responseString = responseBody?.string()
                val result = gson.fromJson(responseString, ResultsItem::class.java)
                topmovielist.add(result)
                activity?.runOnUiThread {
                    movieadapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                call.enqueue(this)
            }
        })
    }
}


