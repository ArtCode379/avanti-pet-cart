package avantifratelli.petcare.avantipetcart.data.model

import androidx.annotation.StringRes
import avantifratelli.petcare.avantipetcart.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    FOOD(R.string.lkzma_category_food),
    TOYS(R.string.lkzma_category_toys),
    BEDS(R.string.lkzma_category_beds),
    TRAVEL(R.string.lkzma_category_travel),
    COLLARS(R.string.lkzma_category_collars),
    CARE(R.string.lkzma_category_care)
}
