package avantifratelli.petcare.avantipetcart.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import avantifratelli.petcare.avantipetcart.R
import avantifratelli.petcare.avantipetcart.ui.viewmodel.LKZMAOnboardingVM
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int
)

private val pages = listOf(
    OnboardingContent(R.string.lkzma_page_1_title, R.string.lkzma_page_1_description, R.drawable.onboarding_1),
    OnboardingContent(R.string.lkzma_page_2_title, R.string.lkzma_page_2_description, R.drawable.onboarding_2),
    OnboardingContent(R.string.lkzma_page_3_title, R.string.lkzma_page_3_description, R.drawable.onboarding_3)
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: LKZMAOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    LaunchedEffect(completed) {
        if (completed) onNavigateToHomeScreen()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(state = pagerState, modifier = Modifier.weight(1f)) { page ->
            val item = pages[page]
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Spacer(Modifier.height(36.dp))
                Image(
                    painter = painterResource(item.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(24.dp))
                )
                Spacer(Modifier.height(36.dp))
                Text(stringResource(item.titleRes), style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(12.dp))
                Text(
                    stringResource(item.descriptionRes),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Box(
                    Modifier
                        .size(if (index == pagerState.currentPage) 10.dp else 8.dp)
                        .background(
                            if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                            CircleShape
                        )
                )
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                if (pagerState.currentPage == pages.lastIndex) {
                    viewModel.setOnboarded()
                } else {
                    scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(if (pagerState.currentPage == pages.lastIndex) R.string.lkzma_start_button_title else R.string.lkzma_next_button_title))
        }
    }
}
