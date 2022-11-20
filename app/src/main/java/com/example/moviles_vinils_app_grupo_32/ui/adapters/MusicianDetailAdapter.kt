package com.example.moviles_vinils_app_grupo_32.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.moviles_vinils_app_grupo_32.R
import com.example.moviles_vinils_app_grupo_32.databinding.MusicianDetailItemBinding
import com.example.moviles_vinils_app_grupo_32.models.Musician

class MusicianDetailAdapter : RecyclerView.Adapter<MusicianDetailAdapter.MusicianDetailViewHolder>() {

    var musician : Musician? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicianDetailViewHolder {
        val withDataBinding: MusicianDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            MusicianDetailViewHolder.LAYOUT,
            parent,
            false)
        return MusicianDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: MusicianDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.musician = musician
        }
    }

    class MusicianDetailViewHolder(val viewDataBinding: MusicianDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.musician_detail_item
        }
    }

    override fun getItemCount(): Int {
        return 1
    }
}