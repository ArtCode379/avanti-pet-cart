package avantifratelli.petcare.avantipetcart.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AvantiColors = lightColorScheme(
    primary = CoralPrimary,
    onPrimary = WarmSurface,
    primaryContainer = ChipBackground,
    onPrimaryContainer = ChipContent,
    secondary = TealAccent,
    onSecondary = WarmSurface,
    background = CreamBackground,
    onBackground = Ink,
    surface = WarmSurface,
    onSurface = Ink,
    surfaceVariant = ChipBackground,
    onSurfaceVariant = Muted,
    outline = Border,
    error = CoralDark
)

@Composable
fun ProductAppLKZMATheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(colorScheme = AvantiColors, typography = Typography, content = content)
}
