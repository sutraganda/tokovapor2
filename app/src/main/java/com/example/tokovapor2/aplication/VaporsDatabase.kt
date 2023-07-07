package com.example.tokovapor2.aplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tokovapor2.dao.VaporsDao
import com.example.tokovapor2.model.Vapors

@Database(entities = [Vapors::class], version = 1, exportSchema = false)
abstract class VaporsDatabase: RoomDatabase() {
    abstract fun vaporsDao(): VaporsDao

    companion object {
        private var INSTANCE: VaporsDatabase? = null

        fun getDatabase(context: Context): VaporsDatabase {
            return  INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VaporsDatabase::class.java,
                    "vapors_database_2"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE= instance
                instance
            }

        }
    }
}