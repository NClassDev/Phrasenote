package com.phrasenote.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.phrasenote.core.BaseViewHolder
import com.phrasenote.core.DataMapper
import com.phrasenote.data.model.Resource
import com.phrasenote.databinding.ResourceItemBinding

class ResourceAdapter(
    private val resourceList: List<Resource>,
    private val itemClickListener: OnResourceClickListener
): RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnResourceClickListener {
        fun onResourceClick(resource: Resource)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = ResourceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        val holder = ResourceViewHolder(parent.context, itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onResourceClick(resourceList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder) {
            is ResourceViewHolder -> holder.bind(resourceList[position])
        }
    }

    override fun getItemCount(): Int = resourceList.size

    private inner class ResourceViewHolder(val context: Context, val binding: ResourceItemBinding): BaseViewHolder<Resource>(binding.root) {
        override fun bind(item: Resource) {
            binding.tvTitle.text = item.title
            binding.tvAuthor.text = item.author
            binding.imgResource.setImageBitmap(DataMapper.decodeImage(item.resource_image))
        }
    }


}