package com.android.myjetpackcomponent.livedata.db

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.android.myjetpackcomponent.livedata.dao.WordDao
import com.android.myjetpackcomponent.livedata.model.Word
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Word::class], version = 1)
abstract class WordDatabase : RoomDatabase() {

    abstract fun wordDao() : WordDao

    companion object {

        @Volatile
        private var INSTANCE: WordDatabase? = null


        fun getDatabase(context: Context, scope: CoroutineScope) : WordDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, WordDatabase::class.java, "word_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(WordDatabaseCallback(scope))
                    .build()

                INSTANCE = instance

                instance
            }

        }

        private class WordDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            /**
             * Override the onOpen method to populate the database.
             * For this sample, we clear the database every time it is created or opened.
             */
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.wordDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        fun populateDatabase(wordDao: WordDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            wordDao.deleteAll()

            var word = Word("Hello")
            wordDao.insertWord(word)
            word = Word("World!")
            wordDao.insertWord(word)

        }

    }

}