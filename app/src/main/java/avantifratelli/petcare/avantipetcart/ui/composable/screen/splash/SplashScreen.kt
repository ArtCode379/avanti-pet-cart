package avantifratelli.petcare.avantipetcart.ui.composable.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import avantifratelli.petcare.avantipetcart.R
import avantifratelli.petcare.avantipetcart.ui.theme.GradientEnd
import avantifratelli.petcare.avantipetcart.ui.theme.GradientStart
import avantifratelli.petcare.avantipetcart.ui.viewmodel.LKZMASplashVM
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: LKZMASplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    var visible by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(if (visible) 1f else 0f, tween(800), label = "splash")

    LaunchedEffect(Unit) {
        visible = true
        delay(1500)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(GradientStart, GradientEnd))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.icon),
            contentDescription = null,
            modifier = Modifier
                .size(156.dp)
                .scale(0.8f + progress * 0.2f)
                .alpha(progress)
        )
        Text(
            text = stringResource(R.string.lkzma_app_name),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.alpha(progress)
        )
    }
}
