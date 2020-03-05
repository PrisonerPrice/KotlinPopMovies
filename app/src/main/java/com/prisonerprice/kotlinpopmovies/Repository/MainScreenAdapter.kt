package com.prisonerprice.kotlinpopmovies.Repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.prisonerprice.kotlinpopmovies.Database.Movie
import com.prisonerprice.kotlinpopmovies.R
import com.prisonerprice.kotlinpopmovies.Repository.MainScreenAdapter.MainScreenViewHolder
import com.prisonerprice.kotlinpopmovies.Utils.ConstantVars
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.main_screen_view_holder.view.*

class MainScreenAdapter(val myClickListener: MyClickListener) : RecyclerView.Adapter<MainScreenViewHolder>() {

    private lateinit var data: ArrayList<Movie>
    private var currState = ConstantVars.STATE_POP

    fun setCurrState(newState: Int) {
        this.currState = newState
    }

    fun setData(data: ArrayList<Movie>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getDetailMovie(position: Int): Movie {
        return data.get(position)
    }

    fun setMovieFavorite(position: Int, movie: Movie) {
        if (currState == ConstantVars.STATE_HIGH || currState == ConstantVars.STATE_POP) {
            data[position].isLiked = movie.isLiked
            notifyItemChanged(position)
        }
        if (currState == ConstantVars.STATE_FAV) {
            val prevMovie = data.get(position)
            if (movie.isLiked == 0) {
                data.remove(prevMovie)
            }
            notifyDataSetChanged()
        }
    }

    fun getCurrState(): Int {
        return currState
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainScreenViewHolder {
        val context = parent.context
        val layoutIdForSingleView = R.layout.main_screen_view_holder
        val inflater = LayoutInflater.from(context)
        val shouldAttachToParentImmediately = false

        val view = inflater.inflate(layoutIdForSingleView, parent, shouldAttachToParentImmediately)
        val mainScreenViewHolder = MainScreenViewHolder(view)

        return mainScreenViewHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MainScreenViewHolder, position: Int) {
        if (data.size > 0) {
            val movie = data.get(position)
            val url = movie.posterUrl
            val title = movie.title
            holder.movieTitle.setText(title)

            Picasso.get().load(url).centerCrop().resize(600, 960)
                .placeholder(R.mipmap.place_holder_image_foreground).centerCrop().resize(600, 960)
                .into(holder.moviePoster)
        } else {
            Picasso.get().load("https://google.com").placeholder(R.mipmap.ic_launcher).placeholder(R.mipmap.ic_launcher).
                into(holder.moviePoster)
        }
    }

    inner class MainScreenViewHolder : RecyclerView.ViewHolder, View.OnClickListener {

        lateinit var moviePoster: ImageView
        lateinit var movieTitle: TextView

        constructor(itemView: View) : super(itemView) {
            moviePoster = itemView.findViewById(R.id.main_screen_image_view)
            movieTitle = itemView.findViewById(R.id.main_screen_text_view)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            myClickListener.onItemClick(adapterPosition)
        }
    }

    interface MyClickListener {
        fun onItemClick(position: Int)
    }
}

