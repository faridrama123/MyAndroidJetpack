package com.faridrama123.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.faridrama123.database.Note
import com.faridrama123.database.NoteDao
import com.faridrama123.database.NoteRoomDatabase
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Executors.newSingleThreadExecutor

class NoteRepository ( application: Application) {
    private  val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()


    init{

        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()

    }

    fun getAllNotes(): DataSource.Factory<Int, Note> {
        return mNotesDao.getAllNotes()
    }

    fun insert(note: Note) {
        executorService.execute { mNotesDao.insert(note) }
    }
    fun delete(note: Note) {
        executorService.execute { mNotesDao.delete(note) }
    }
    fun update(note: Note) {
        executorService.execute { mNotesDao.update(note) }
    }

}