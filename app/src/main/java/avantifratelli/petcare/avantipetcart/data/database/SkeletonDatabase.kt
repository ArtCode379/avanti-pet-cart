package avantifratelli.petcare.avantipetcart.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import avantifratelli.petcare.avantipetcart.data.dao.CartItemDao
import avantifratelli.petcare.avantipetcart.data.dao.OrderDao
import avantifratelli.petcare.avantipetcart.data.database.converter.Converters
import avantifratelli.petcare.avantipetcart.data.entity.CartItemEntity
import avantifratelli.petcare.avantipetcart.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LKZMADatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}