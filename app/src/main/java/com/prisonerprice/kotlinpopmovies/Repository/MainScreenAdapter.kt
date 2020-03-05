package com.prisonerprice.kotlinpopmovies.Repository

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.prisonerprice.kotlinpopmovies.Database.Movie
import com.prisonerprice.kotlinpopmovies.Repository.MainScreenAdapter.MainScreenViewHolder
import kotlinx.android.synthetic.main.main_screen_view_holder.view.*

class MainScreenAdapter(var data: ArrayList<Movie>) : RecyclerView.Adapter<MainScreenViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class MainScreenViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val moviePoster = itemView.main_screen_image_view
        val movieTitle = itemView.main_screen_text_view
        

        override fun onClick(v: View?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }

    interface MyClickListener {
        fun onItemClick(position: Int)
    }
}