package avantifratelli.petcare.avantipetcart.di

import avantifratelli.petcare.avantipetcart.data.repository.CartRepository
import avantifratelli.petcare.avantipetcart.data.repository.LKZMAOnboardingRepo
import avantifratelli.petcare.avantipetcart.data.repository.OrderRepository
import avantifratelli.petcare.avantipetcart.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        LKZMAOnboardingRepo(
            lkzmaOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}