package com.android.myjetpackcomponent.livedata.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.android.myjetpackcomponent.livedata.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words ORDER BY word ASC")
    fun getAllWords() : LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertWord(word: Word)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun updateWord(word: Word)

    @Delete()
    fun deleteAWord(word: Word)

    @Query("DELETE FROM words")
    fun deleteAll()

}