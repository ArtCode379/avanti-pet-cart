package avantifratelli.petcare.avantipetcart.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import avantifratelli.petcare.avantipetcart.R
import avantifratelli.petcare.avantipetcart.ui.composable.shared.LKZMAContentWrapper
import avantifratelli.petcare.avantipetcart.ui.state.CartItemUiState
import avantifratelli.petcare.avantipetcart.ui.state.DataUiState
import avantifratelli.petcare.avantipetcart.ui.viewmodel.CartViewModel
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    LKZMAContentWrapper(
        dataState = state,
        dataPopulated = {
            CartList(
                items = (state as DataUiState.Populated).data,
                total = total,
                onPlus = viewModel::incrementProductInCart,
                onMinus = viewModel::decrementItemInCart,
                onDelete = viewModel::deleteFromCart,
                onCheckout = onNavigateToCheckoutScreen,
                modifier = modifier
            )
        },
        dataEmpty = {
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("🐾", style = MaterialTheme.typography.headlineLarge)
                Text(stringResource(R.string.lkzma_cart_state_empty_primary_text), style = MaterialTheme.typography.titleLarge)
                Text("Start Shopping", color = MaterialTheme.colorScheme.primary)
            }
        }
    )
}

@Composable
private fun CartList(
    items: List<CartItemUiState>,
    total: Double,
    onPlus: (Int) -> Unit,
    onMinus: (Int) -> Unit,
    onDelete: (Int) -> Unit,
    onCheckout: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(items, key = { it.productId }) { item ->
                Card(shape = RoundedCornerShape(18.dp)) {
                    Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(item.productImageUrl, item.productTitle, Modifier.size(60.dp), contentScale = ContentScale.Crop)
                        Column(Modifier.padding(start = 12.dp).weight(1f)) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text("£${"%.2f".format(item.productPrice)}", color = MaterialTheme.colorScheme.primary)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                OutlinedButton(onClick = { if (item.quantity == 1) onDelete(item.productId) else onMinus(item.productId) }) { Text("−") }
                                Text(item.quantity.toString(), Modifier.padding(horizontal = 12.dp))
                                OutlinedButton(onClick = { onPlus(item.productId) }) { Text("+") }
                            }
                        }
                        IconButton(onClick = { onDelete(item.productId) }) {
                            Icon(Icons.Rounded.Delete, stringResource(R.string.lkzma_delete_item_icon_description))
                        }
                    }
                }
            }
        }
        Text("Subtotal  £${"%.2f".format(total)}", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.bodyLarge)
        Text("Total  £${"%.2f".format(total)}", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.size(12.dp))
        Button(onClick = onCheckout, Modifier.fillMaxWidth()) { Text("Proceed to Checkout") }
    }
}
