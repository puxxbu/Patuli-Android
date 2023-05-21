package com.puxxbu.PatuliApp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.puxxbu.PatuliApp.data.api.config.ApiConfig
import com.puxxbu.PatuliApp.data.database.SessionDataPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class PatuliApp : Application() {
    private val Context.data: DataStore<Preferences> by preferencesDataStore("token")


    override fun onCreate() {
        super.onCreate()

        context = applicationContext

        startKoin {
            androidLogger()
            androidContext(this@PatuliApp)
            modules(repoModule)
        }

    }

    private val repoModule = module {
        single { ApiConfig.getApiService(context) }
        single { SessionDataPreferences.getInstance(context.data) }
    }

    companion object {
        lateinit var context: Context
    }
}