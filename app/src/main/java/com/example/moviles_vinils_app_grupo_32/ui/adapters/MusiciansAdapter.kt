package com.example.moviles_vinils_app_grupo_32.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviles_vinils_app_grupo_32.R
import com.example.moviles_vinils_app_grupo_32.databinding.MusicianItemBinding
import com.example.moviles_vinils_app_grupo_32.models.Musician

class MusiciansAdapter: RecyclerView.Adapter<MusiciansAdapter.MusicianViewHolder>() {
    var musicians :List<Musician> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianViewHolder {
        val withDataBinding: MusicianItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusicianViewHolder.LAYOUT,
            parent,
            false)
        return MusicianViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusicianViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.musician = musicians[position]
        }
//        holder.viewDataBinding.root.setOnClickListener {
//            val action = AlbumFragmentDirections.actionAlbumFragmentToCommentFragment(albums[position].albumId)
//            // Navigate using that action
//            holder.viewDataBinding.root.findNavController().navigate(action)
//        }
    }

    override fun getItemCount(): Int {
        return musicians.size
    }


    class MusicianViewHolder(val viewDataBinding: MusicianItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.musician_item
        }
    }
}