package com.idn.tabmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val KEY_MOVIE = "movie"
    }
    var model: Model? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        model = intent.getParcelableExtra(KEY_MOVIE)
        supportActionBar?.title = model?.title?:"Title"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        txt_title_detail.text = model?.title
        txt_description_detail.text = model?.description
        txt_year_detail.text = "${"Year"}:${model?.year}"
        img_poster_detail.setImageResource(model?.thumb?:0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == android.R.id.home){
            finish()
            return false
        }
        return super.onOptionsItemSelected(item)
    }
}
