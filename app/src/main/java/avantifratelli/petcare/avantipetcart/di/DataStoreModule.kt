package avantifratelli.petcare.avantipetcart.di

import avantifratelli.petcare.avantipetcart.data.datastore.LKZMAOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { LKZMAOnboardingPrefs(androidContext()) }
}