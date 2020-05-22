package com.android.myjetpackcomponent.livedata.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.myjetpackcomponent.livedata.db.WordDatabase
import com.android.myjetpackcomponent.livedata.model.Word
import com.android.myjetpackcomponent.livedata.repo.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordViewModel (application: Application) : AndroidViewModel(application) {

    var allWords   : LiveData<List<Word>>
    private val repository : WordRepository

    init{

        val wordDao = WordDatabase.getDatabase(application, viewModelScope).wordDao()

        repository = WordRepository(wordDao)

        allWords = repository.allWords

    }

    fun insertWord(word: Word) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertWord(word)
    }

}