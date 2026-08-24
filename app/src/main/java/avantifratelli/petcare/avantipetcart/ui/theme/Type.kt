package avantifratelli.petcare.avantipetcart.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import avantifratelli.petcare.avantipetcart.R

private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)
private val headingFamily = FontFamily(Font(GoogleFont("DM Sans"), provider))
private val bodyFamily = FontFamily(Font(GoogleFont("Nunito"), provider))

val Typography = Typography(
    headlineLarge = TextStyle(fontFamily = headingFamily, fontWeight = FontWeight.Bold, fontSize = 30.sp),
    headlineMedium = TextStyle(fontFamily = headingFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp),
    titleLarge = TextStyle(fontFamily = headingFamily, fontWeight = FontWeight.Bold, fontSize = 22.sp),
    titleMedium = TextStyle(fontFamily = headingFamily, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    bodyLarge = TextStyle(fontFamily = bodyFamily, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = bodyFamily, fontSize = 14.sp, lineHeight = 20.sp),
    labelLarge = TextStyle(fontFamily = bodyFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp)
)
