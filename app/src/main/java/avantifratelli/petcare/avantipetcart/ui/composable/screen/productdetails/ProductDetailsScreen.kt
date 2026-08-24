package avantifratelli.petcare.avantipetcart.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import avantifratelli.petcare.avantipetcart.R
import avantifratelli.petcare.avantipetcart.data.model.Product
import avantifratelli.petcare.avantipetcart.ui.composable.shared.LKZMAContentWrapper
import avantifratelli.petcare.avantipetcart.ui.state.DataUiState
import avantifratelli.petcare.avantipetcart.ui.viewmodel.ProductDetailsViewModel
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel()
) {
    val state by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    LKZMAContentWrapper(
        dataState = state,
        dataPopulated = { ProductDetail((state as DataUiState.Populated).data, viewModel::addProductToCart, modifier) },
        dataEmpty = { Box(Modifier.fillMaxSize()) { Text(stringResource(R.string.lkzma_product_details_state_empty_primary_text)) } }
    )
}

@Composable
private fun ProductDetail(product: Product, onAdd: () -> Unit, modifier: Modifier = Modifier) {
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier.fillMaxSize()) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            AsyncImage(
                product.imageUrl,
                product.title,
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(bottomStart = 28.dp, bottomEnd = 28.dp)),
                contentScale = ContentScale.Crop
            )
            Column(Modifier.padding(20.dp)) {
                Text(product.title, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Surface(color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(50)) {
                    Text(stringResource(product.category.titleRes), Modifier.padding(horizontal = 12.dp, vertical = 6.dp), color = MaterialTheme.colorScheme.onPrimaryContainer)
                }
                Spacer(Modifier.height(16.dp))
                Text("£${"%.2f".format(product.price)}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                Spacer(Modifier.height(12.dp))
                Text(product.description, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(28.dp))
                Button(
                    onClick = {
                        onAdd()
                        cartAdded = true
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.lkzma_button_add_to_cart_label))
                }
                Spacer(Modifier.height(60.dp))
            }
        }
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier
                .fillMaxWidth()
                .align(androidx.compose.ui.Alignment.BottomCenter)
        ) {
            Text(
                "✓ Added to cart",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
