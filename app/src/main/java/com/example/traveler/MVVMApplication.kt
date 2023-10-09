package com.example.traveler

import android.app.Application
import com.example.traveler.data.db.AppDatabase
import com.example.traveler.data.network.MyApi
import com.example.traveler.data.network.responses.NetworkConnectionInterceptor
import com.example.traveler.data.repositories.TrainRepository
import com.example.traveler.data.repositories.UserRepository
import com.example.traveler.uis.activate.ActivateViewModel
import com.example.traveler.uis.auth.AuthViewModel
import com.example.traveler.uis.auth.AuthViewModelFactory
import com.example.traveler.uis.home.profile.ActivateViewModelFactory
import com.example.traveler.uis.home.profile.ProfileViewModelFactory
import com.example.traveler.uis.home.trains.TrainViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        // bind all classes
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { TrainRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
        bind() from provider { ProfileViewModelFactory(instance()) }
        bind() from provider { ActivateViewModelFactory(instance()) }
        bind() from provider { TrainViewModelFactory(instance()) }
    }


}