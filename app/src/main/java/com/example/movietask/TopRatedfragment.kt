package com.example.movietask


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_top_ratedfragment.*
import okhttp3.*
import java.io.IOException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TopRatedfragment : Fragment() {
    private val TAG="TopRatedFragment:"
    private val topmovielist = arrayListOf<ResultsItem>()
    private val okHttpClient = OkHttpClient.Builder().build()
    private val gson = Gson()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_top_ratedfragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = "https://api.themoviedb.org/3/movie/top_rated?page=1&language=en-US&api_key=${resources.getString(
            R.string.api_key
        )}".trimIndent()

        recycle.layoutManager = LinearLayoutManager(context)
        val movieAdapter = MovieAdapter(requireContext(), topmovielist)
        recycle.adapter = movieAdapter

        val request = Request.Builder()
            .url(url)
            .build()
        val response = okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    val responseString = responseBody?.string()
                    Log.e(TAG, "onResponse:$responseString")
                    val result = gson.fromJson(responseString, TopModel::class.java)
                    Log.e(TAG, "RESULT$result")
                    val list = result.results
                    topmovielist.addAll(list)
                    Log.e(TAG, "OverView " + list[1].overview)
                    activity?.runOnUiThread {
                    movieAdapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                call.enqueue(this)
            }
        })
    }

}


