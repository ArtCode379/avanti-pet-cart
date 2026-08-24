package avantifratelli.petcare.avantipetcart.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import avantifratelli.petcare.avantipetcart.R
import avantifratelli.petcare.avantipetcart.data.model.Product
import avantifratelli.petcare.avantipetcart.data.model.ProductCategory
import avantifratelli.petcare.avantipetcart.ui.composable.shared.LKZMAContentWrapper
import avantifratelli.petcare.avantipetcart.ui.state.DataUiState
import avantifratelli.petcare.avantipetcart.ui.viewmodel.ProductViewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit
) {
    val state by viewModel.productsState.collectAsState()
    var selected by remember { mutableStateOf<ProductCategory?>(null) }
    LKZMAContentWrapper(
        dataState = state,
        dataPopulated = {
            val products = (state as DataUiState.Populated).data
            HomeProducts(
                products = selected?.let { category -> products.filter { it.category == category } } ?: products,
                featured = products.take(4),
                selected = selected,
                onSelect = { selected = if (selected == it) null else it },
                onOpen = onNavigateToProductDetails,
                modifier = modifier
            )
        },
        dataEmpty = { Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(stringResource(R.string.lkzma_products_state_empty_primary_text)) } }
    )
}

@Composable
private fun HomeProducts(
    products: List<Product>,
    featured: List<Product>,
    selected: ProductCategory?,
    onSelect: (ProductCategory) -> Unit,
    onOpen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pager = rememberPagerState(pageCount = { featured.size })
    LaunchedEffect(featured.size) {
        while (featured.isNotEmpty()) {
            delay(4000)
            pager.animateScrollToPage((pager.currentPage + 1) % featured.size)
        }
    }
    Column(modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(Modifier.weight(1f)) {
                Text("Good care starts here", style = MaterialTheme.typography.headlineMedium)
                Text("Everyday favourites for happy pets", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            IconButton(onClick = { selected?.let { onSelect(it) } }) {
                Icon(Icons.Rounded.Search, contentDescription = "Clear category filter")
            }
        }
        HorizontalPager(state = pager, modifier = Modifier.fillMaxWidth()) { page ->
            val item = featured[page]
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .height(190.dp)
                    .clickable { onOpen(item.id) },
                shape = RoundedCornerShape(18.dp)
            ) {
                Box {
                    AsyncImage(item.imageUrl, null, Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                    Text(
                        "${item.title}  •  £${"%.2f".format(item.price)}",
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .padding(16.dp),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
        LazyRow(
            modifier = Modifier.padding(vertical = 12.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(ProductCategory.entries) { category ->
                FilterChip(
                    selected = selected == category,
                    onClick = { onSelect(category) },
                    label = { Text(stringResource(category.titleRes)) }
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(products, key = { it.id }) { item -> ProductCard(item, onOpen) }
        }
    }
}

@Composable
private fun ProductCard(item: Product, onOpen: (Int) -> Unit) {
    Card(
        modifier = Modifier.clickable { onOpen(item.id) },
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(12.dp)) {
                Text(item.title, style = MaterialTheme.typography.titleMedium, maxLines = 2)
                Text(stringResource(item.category.titleRes), style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("£${"%.2f".format(item.price)}", color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
            }
        }
    }
}
