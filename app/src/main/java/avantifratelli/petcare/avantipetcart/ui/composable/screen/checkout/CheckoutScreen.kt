package avantifratelli.petcare.avantipetcart.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import avantifratelli.petcare.avantipetcart.ui.state.DataUiState
import avantifratelli.petcare.avantipetcart.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit
) {
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val enabled by remember {
        derivedStateOf { viewModel.customerFirstName.isNotBlank() && viewModel.customerLastName.isNotBlank() && viewModel.customerEmail.isNotBlank() }
    }
    if (orderState is DataUiState.Populated) CheckoutDialog(onConfirm = onNavigateToOrdersScreen)
    CheckoutContent(
        name = viewModel.customerFirstName,
        address = viewModel.customerLastName,
        phone = viewModel.customerEmail,
        invalid = emailInvalid,
        enabled = enabled,
        focusManager = LocalFocusManager.current,
        onName = viewModel::updateCustomerFirstName,
        onAddress = viewModel::updateCustomerLastName,
        onPhone = viewModel::updateCustomerEmail,
        onPlace = viewModel::placeOrder,
        modifier = modifier
    )
}

@Composable
private fun CheckoutContent(
    name: String,
    address: String,
    phone: String,
    invalid: Boolean,
    enabled: Boolean,
    focusManager: FocusManager,
    onName: (String) -> Unit,
    onAddress: (String) -> Unit,
    onPhone: (String) -> Unit,
    onPlace: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        Text("Reserve your order", style = MaterialTheme.typography.headlineMedium)
        Text("Enter your collection details. We will hold your order in store for 24 hours.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        CheckoutTextField(name, onName, "Name", Modifier.fillMaxWidth())
        CheckoutTextField(address, onAddress, "Address", Modifier.fillMaxWidth())
        CheckoutTextField(
            phone,
            onPhone,
            "Phone or email",
            Modifier.fillMaxWidth(),
            isError = invalid,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("Order summary", style = MaterialTheme.typography.titleMedium)
                Text("Your reserved items and total will be confirmed with your order number.")
            }
        }
        Button(
            onClick = {
                focusManager.clearFocus()
                onPlace()
            },
            enabled = enabled,
            modifier = Modifier.fillMaxWidth()
        ) { Text("Place Order") }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier,
        enabled = enabled,
        label = { Text(labelText) },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true
    )
}
