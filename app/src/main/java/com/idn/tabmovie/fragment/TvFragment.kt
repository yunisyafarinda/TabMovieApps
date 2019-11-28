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
import com.bumptech.glide.load.model.Model
import com.idn.tabmovie.AdapterFragment
import com.idn.tabmovie.DetailActivity
import com.idn.tabmovie.R
import kotlinx.android.synthetic.main.fragment_tv.*

/**
 * A simple [Fragment] subclass.
 */
class TvFragment : Fragment() {
    private lateinit var tvShowNames: Array<String>
    private lateinit var tvShowDescriptions: Array<String>
    private lateinit var tvShowYears: Array<String>
    private lateinit var tvShowThumbs:TypedArray
    private var tvShows = arrayListOf<com.idn.tabmovie.Model>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRecyclerList()
    }
    private fun showRecyclerList(){
        recycler_tv.setHasFixedSize(true)
        recycler_tv.layoutManager = LinearLayoutManager(context)
        recycler_tv.addItemDecoration(
            DividerItemDecoration(
                recycler_tv.context,
                DividerItemDecoration.VERTICAL
            )
        )
        val tvAdapter = AdapterFragment(tvShows){
            selectedItem(it)
        }
        recycler_tv.adapter = tvAdapter
    }
    private fun selectedItem(it: com.idn.tabmovie.Model){
        val movePage = Intent(context, DetailActivity::class.java)
        movePage.putExtra(DetailActivity.KEY_MOVIE, it)
        startActivity(movePage)
    }

    private fun loadData() {
        tvShowNames = resources.getStringArray(R.array.tv_show_names)
        tvShowDescriptions = resources.getStringArray(R.array.tv_show_descriptions)
        tvShowYears = resources.getStringArray(R.array.tv_show_years)
        tvShowThumbs = resources.obtainTypedArray(R.array.tv_show_thumbs)

        for (position in tvShowNames.indices){
            val tvShow = com.idn.tabmovie.Model(
                position,
                tvShowNames[position],
                tvShowDescriptions[position],
                tvShowYears[position],
                tvShowThumbs.getResourceId(position, -1)
            )
            tvShows.add(tvShow)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

}
