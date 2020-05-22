package com.android.myjetpackcomponent.livedata

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myjetpackcomponent.R
import com.android.myjetpackcomponent.livedata.adapter.WordAdapter
import com.android.myjetpackcomponent.livedata.model.Word
import com.android.myjetpackcomponent.livedata.viewmodel.WordViewModel
import com.android.myjetpackcomponent.views.BaseActivity
import kotlinx.android.synthetic.main.activity_livedata.*


class LiveDataActivity : BaseActivity() {

    private val newWordActivityRequestCode = 1
    private lateinit var wordAdapter : WordAdapter
    private lateinit var wordViewModel: WordViewModel

    override fun contentView() = R.layout.activity_livedata

    override fun setScreenTitle() = getString(R.string.livedata)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wordAdapter = WordAdapter(applicationContext)
        recyclerLiveData.adapter = wordAdapter
        recyclerLiveData.layoutManager = LinearLayoutManager(applicationContext)

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

        wordViewModel.allWords.observe(this, Observer {
            wordAdapter.setWords(it)
        })

        fab.setOnClickListener {
            addWords()
        }

    }

    private fun addWords() {

        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView: View = inflater.inflate(R.layout.alert_add_words, null)
        dialogBuilder.setView(dialogView)
        val alertDialog: AlertDialog = dialogBuilder.create()
        alertDialog.show()

        val edText = alertDialog.findViewById<EditText>(R.id.edNewWord)
        val btnAdd = alertDialog.findViewById<Button>(R.id.btnAdd)

        btnAdd.setOnClickListener {
            if(edText.text.toString().isNotBlank()){
                wordViewModel.insertWord(Word( edText.text.toString()))
                alertDialog.dismiss()
            } else {
                Toast.makeText(applicationContext, "Enter a Text", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun toolbarBackPressed() = onBackPressed()

}