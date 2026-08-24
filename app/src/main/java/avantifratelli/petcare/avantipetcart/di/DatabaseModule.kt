package avantifratelli.petcare.avantipetcart.di

import androidx.room.Room
import avantifratelli.petcare.avantipetcart.data.database.LKZMADatabase
import org.koin.dsl.module

private const val DB_NAME = "lkzma_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = LKZMADatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<LKZMADatabase>().cartItemDao() }

    single { get<LKZMADatabase>().orderDao() }
}