package com.android.myjetpackcomponent.livedata.repo

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.android.myjetpackcomponent.livedata.dao.WordDao
import com.android.myjetpackcomponent.livedata.model.Word

class WordRepository (private val wordDao: WordDao) {

    val allWords : LiveData<List<Word>> = wordDao.getAllWords()

    @Suppress("SuspendModifier")
    @WorkerThread
    suspend fun insertWord(word: Word) {
        wordDao.insertWord(word)
    }

}