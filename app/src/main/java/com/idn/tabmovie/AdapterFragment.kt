package com.idn.tabmovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_recycler.view.*

class AdapterFragment(
    private val listMovie: ArrayList<Model>,
    private val listener: (Model) -> Unit
): RecyclerView.Adapter<AdapterFragment.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMovie[position], listener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(model: Model, listener: (Model) -> Unit) {
            with(itemView){
                Glide.with(itemView.context).load(model.thumb)
                    .apply(
                        RequestOptions()
                            .override(
                                resources.getDimensionPixelSize(R.dimen.width_thumb_movie_list),
                                resources.getDimensionPixelSize(R.dimen.height_thumb_movie_list)
                            )
                    ).into(iv_poster)
                txt_name.text = model.title
                txt_description.text = model.description

                itemView.setOnClickListener{listener(model)}
            }
        }

    }

}