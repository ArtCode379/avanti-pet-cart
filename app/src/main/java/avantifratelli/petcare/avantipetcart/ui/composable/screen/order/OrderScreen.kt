package avantifratelli.petcare.avantipetcart.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import avantifratelli.petcare.avantipetcart.data.entity.OrderEntity
import avantifratelli.petcare.avantipetcart.ui.composable.shared.LKZMAContentWrapper
import avantifratelli.petcare.avantipetcart.ui.state.DataUiState
import avantifratelli.petcare.avantipetcart.ui.theme.Success
import avantifratelli.petcare.avantipetcart.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.format.DateTimeFormatter

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    LKZMAContentWrapper(
        dataState = state,
        dataPopulated = { OrderList((state as DataUiState.Populated).data.sortedByDescending { it.timestamp }, modifier) },
        dataEmpty = {
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("No orders yet", style = MaterialTheme.typography.titleLarge)
                Text("Your reservation history will appear here.", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    )
}

@Composable
private fun OrderList(orders: List<OrderEntity>, modifier: Modifier = Modifier) {
    LazyColumn(modifier.fillMaxSize(), contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(orders, key = { it.orderNumber }) { order ->
            Card(shape = RoundedCornerShape(18.dp)) {
                Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Order #${order.orderNumber}", style = MaterialTheme.typography.titleMedium)
                        Surface(color = Success.copy(alpha = 0.14f), shape = RoundedCornerShape(50)) {
                            Text("Completed", Modifier.padding(horizontal = 10.dp, vertical = 4.dp), color = Success)
                        }
                    }
                    Text(order.timestamp.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")), color = MaterialTheme.colorScheme.onSurfaceVariant)
                    Text(order.description)
                    Text("£${"%.2f".format(order.price)}", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                    Text("Reserved for collection for 24 hours", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
    }
}
