package com.example.moviles_vinils_app_grupo_32.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviles_vinils_app_grupo_32.R
import com.example.moviles_vinils_app_grupo_32.databinding.AddTrackItemBinding
import com.example.moviles_vinils_app_grupo_32.models.Track

class TracksAdapter: RecyclerView.Adapter<TracksAdapter.TrackViewHolder>() {
    var tracks : List<Track> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val withDataBinding: AddTrackItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            TrackViewHolder.LAYOUT,
            parent,
            false)
        return  TrackViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder:TrackViewHolder, position: Int){
        holder.viewDataBinding.also {
            it.track = tracks[position]
        }
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

        class TrackViewHolder(val viewDataBinding: AddTrackItemBinding):
        RecyclerView.ViewHolder(viewDataBinding.root){
            companion object{
                @LayoutRes
                val LAYOUT = R.layout.add_track_item
            }
        }
}