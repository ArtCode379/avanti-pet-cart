package avantifratelli.petcare.avantipetcart.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val supportUrl = "https://avantifratelli.casa"
    Column(modifier.fillMaxSize().padding(20.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text("About", style = MaterialTheme.typography.headlineMedium)
        Card(shape = RoundedCornerShape(18.dp)) {
            Column(Modifier.fillMaxWidth().padding(16.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                SettingRow("Company", "AVANTI FRATELLI LTD")
                SettingRow("App", "Avanti Pet Cart")
                SettingRow("Version", "1.0")
            }
        }
        Text("Legal & support", style = MaterialTheme.typography.titleLarge)
        Button(
            onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(supportUrl))) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(Icons.Rounded.Language, contentDescription = null)
            Text("  Customer Support")
        }
        Text("Support opens the official company website.", color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Composable
private fun SettingRow(label: String, value: String) {
    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Icon(Icons.Rounded.Info, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
        Column(Modifier.padding(start = 12.dp)) {
            Text(label, style = MaterialTheme.typography.labelLarge)
            Text(value, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
