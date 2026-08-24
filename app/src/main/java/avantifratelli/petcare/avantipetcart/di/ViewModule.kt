package avantifratelli.petcare.avantipetcart.di

import avantifratelli.petcare.avantipetcart.ui.viewmodel.AppViewModel
import avantifratelli.petcare.avantipetcart.ui.viewmodel.CartViewModel
import avantifratelli.petcare.avantipetcart.ui.viewmodel.CheckoutViewModel
import avantifratelli.petcare.avantipetcart.ui.viewmodel.LKZMAOnboardingVM
import avantifratelli.petcare.avantipetcart.ui.viewmodel.OrderViewModel
import avantifratelli.petcare.avantipetcart.ui.viewmodel.ProductDetailsViewModel
import avantifratelli.petcare.avantipetcart.ui.viewmodel.ProductViewModel
import avantifratelli.petcare.avantipetcart.ui.viewmodel.LKZMASplashVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        LKZMASplashVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        LKZMAOnboardingVM(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}