package avantifratelli.petcare.avantipetcart.data.repository

import avantifratelli.petcare.avantipetcart.data.model.Product
import avantifratelli.petcare.avantipetcart.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products = listOf(
        Product(
            1,
            "Salmon & Oat Dog Food",
            "A complete salmon and oat recipe, naturally rich in omega oils for a glossy coat.",
            ProductCategory.FOOD,
            24.90,
            "https://images.unsplash.com/photo-1589924691995-400dc9ecc119?w=1200"
        ),
        Product(2, "Chicken Kitten Bites", "Tender bites with chicken and balanced vitamins.", ProductCategory.FOOD, 12.50,
            "https://images.unsplash.com/photo-1601758228041-f3b2795255f1?w=1200"),
        Product(3, "Braided Rope Tug", "Sturdy cotton rope for energetic play and healthy teeth.", ProductCategory.TOYS, 8.95,
            "https://images.unsplash.com/photo-1591946614720-90a587da4a36?w=1200"),
        Product(4, "Feather Chase Wand", "A teaser wand that encourages indoor exercise.", ProductCategory.TOYS, 6.75,
            "https://images.unsplash.com/photo-1545249390-6bdfa286032f?w=1200"),
        Product(5, "Cloud Nest Pet Bed", "A supportive washable bed with a cosy raised edge.", ProductCategory.BEDS, 39.00,
            "https://images.unsplash.com/photo-1541599540903-216a46ca1dc0?w=1200"),
        Product(6, "Linen Lounger", "A breathable lounger with dense cushioning.", ProductCategory.BEDS, 46.50,
            "https://images.unsplash.com/photo-1583337130417-3346a1be7dee?w=1200"),
        Product(7, "Airflow Travel Carrier", "Secure carrier with mesh ventilation and easy-clean mat.", ProductCategory.TRAVEL, 54.00,
            "https://images.unsplash.com/photo-1598133894008-61f7fdb8cc3a?w=1200"),
        Product(8, "Foldable Travel Bowl", "A food-safe silicone bowl that folds flat.", ProductCategory.TRAVEL, 9.40,
            "https://images.unsplash.com/photo-1601758174114-e711c0cbaa69?w=1200"),
        Product(9, "Sage Everyday Collar", "An adjustable collar with reinforced stitching.", ProductCategory.COLLARS, 14.25,
            "https://images.unsplash.com/photo-1583511655857-d19b40a7a54e?w=1200"),
        Product(10, "Hands-Free Walking Lead", "A strong lead with padded handle and reflective stitching.", ProductCategory.COLLARS, 22.00,
            "https://images.unsplash.com/photo-1558788353-f76d92427f16?w=1200"),
        Product(11, "Gentle Coat Shampoo", "A pH-balanced oat wash for sensitive coats.", ProductCategory.CARE, 11.80,
            "https://images.unsplash.com/photo-1612536057832-2ff7ead58194?w=1200"),
        Product(12, "Bamboo Grooming Brush", "Rounded pins lift loose fur without scratching.", ProductCategory.CARE, 16.60,
            "https://images.unsplash.com/photo-1516734212186-a967f81ad0d7?w=1200")
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)
}
