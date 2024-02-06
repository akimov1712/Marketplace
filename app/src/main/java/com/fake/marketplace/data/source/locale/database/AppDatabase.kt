package com.fake.marketplace.data.source.locale.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fake.marketplace.data.source.locale.database.dao.AccountDao
import com.fake.marketplace.data.source.locale.database.dao.ProductDao
import com.fake.marketplace.data.source.locale.database.entities.account.AccountDbEntity
import com.fake.marketplace.data.source.locale.database.entities.product.ProductDbEntity

@Database(
    entities = [
        AccountDbEntity::class,
        ProductDbEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao
    abstract fun accountDao(): AccountDao

    companion object{

        private var instance: AppDatabase? = null
        private const val DB_NAME = "marketplace.db"

        fun getInstance(application: Application) = instance ?: synchronized(this){
            instance ?: buildDatabase(application).also { instance = it }
        }

        private fun buildDatabase(application: Application) =
            Room.databaseBuilder(
                application,
                AppDatabase::class.java,
                DB_NAME
            ).build()

    }

}