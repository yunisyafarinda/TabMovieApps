package com.idn.tabmovie.fragment


import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.idn.tabmovie.AdapterFragment
import com.idn.tabmovie.DetailActivity
import com.idn.tabmovie.Model
import com.idn.tabmovie.R
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private lateinit var movieNames: Array<String>
    private lateinit var movieDescriptions: Array<String>
    private lateinit var movieYears: Array<String>
    private lateinit var movieThumbs: TypedArray
    private var movies = arrayListOf<Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    private fun loadData(){
        movieNames = resources.getStringArray(R.array.movie_names)
        movieDescriptions = resources.getStringArray(R.array.movie_descriptions)
        movieYears = resources.getStringArray(R.array.movie_years)
        movieThumbs = resources.obtainTypedArray(R.array.movie_thumbs)

        for (position in movieNames.indices){
            val movie = Model(position,
                movieNames[position],
                movieDescriptions[position],
                movieYears[position],
                movieThumbs.getResourceId(position, -1))
            movies.add(movie)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
    }

    private fun showRecyclerList(){
        recycler_movie.setHasFixedSize(true)
        recycler_movie.layoutManager = LinearLayoutManager(context)
        recycler_movie.addItemDecoration(DividerItemDecoration(recycler_movie.context, DividerItemDecoration.VERTICAL))
        val movieAdapter = AdapterFragment(movies){
            showSelectedItem(it)
        }
        recycler_movie.adapter = movieAdapter
    }

    private fun showSelectedItem(it: Model){
        val movePage = Intent(context, DetailActivity::class.java)
        movePage.putExtra(DetailActivity.KEY_MOVIE, it)
        startActivity(movePage)
    }


}
