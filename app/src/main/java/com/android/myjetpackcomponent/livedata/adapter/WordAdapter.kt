package com.android.myjetpackcomponent.livedata.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.livedata.model.Word
import kotlinx.android.synthetic.main.item_livedata_word.view.*

class WordAdapter internal constructor(val mContext: Context) : RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    private var wordsList = emptyList<Word>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onDataBind(wordsList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_livedata_word,parent, false))

    override fun getItemCount() = wordsList.size

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun onDataBind(mWord : Word) {

            itemView.tvLiveWords.text = mWord.word

        }

    }

    internal fun setWords(allWords : List<Word>) {
        wordsList = allWords
        notifyDataSetChanged()
    }

}