package avantifratelli.petcare.avantipetcart.data.repository

import avantifratelli.petcare.avantipetcart.data.datastore.LKZMAOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LKZMAOnboardingRepo(
    private val lkzmaOnboardingStoreManager: LKZMAOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return lkzmaOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            lkzmaOnboardingStoreManager.setOnboardedState(state)
        }
    }
}