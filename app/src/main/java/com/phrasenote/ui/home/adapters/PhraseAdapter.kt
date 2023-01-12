package com.phrasenote.ui.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.phrasenote.R
import com.phrasenote.core.BaseViewHolder
import com.phrasenote.core.DataMapper
import com.phrasenote.data.model.Phrase
import com.phrasenote.data.model.PhraseList
import com.phrasenote.databinding.PhraseItemBinding

class PhraseAdapter(
    private val phraseList: List<Phrase>,
    private val itemClickListener: OnPhraseClickListener
) :
    RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPhraseClickListener {
        fun onPhraseClick(phrase: Phrase)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = PhraseItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        val holder = PhraseViewHolder(parent.context, itemBinding)

        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf {
                it != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onPhraseClick(phraseList[position])
        }

        return holder
    }


    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PhraseViewHolder -> holder.bind(phraseList[position])
        }
    }

    override fun getItemCount(): Int = phraseList.size

    private inner class PhraseViewHolder(val context: Context, val binding: PhraseItemBinding) :
        BaseViewHolder<Phrase>(binding.root) {
        override fun bind(item: Phrase) {
            binding.tvTitle.text = item.title
            binding.tvPhrase.text = item.phrase
            binding.tvPhraseExample.text = item.phrase_example
            if (item.resourceImg.length > 10) {
                binding.imgResource.setImageBitmap(DataMapper.decodeImage(item.resourceImg!!))
            }
        }
    }

}
