package avantifratelli.petcare.avantipetcart

//[FIREBASE|APPSFLYER][import_Intent]
//[FIREBASE][import_URI]
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
//[FIREBASE][imports_workmanager_settings]
import avantifratelli.petcare.avantipetcart.ui.composable.approot.AppRoot
import avantifratelli.petcare.avantipetcart.ui.theme.ProductAppLKZMATheme
//[FIREBASE][import_VisitRequestWorker]

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProductAppLKZMATheme {
                AppRoot()
            }
        }

        //[FIREBASE][onCreate_handleNotificationIntent]
    }

    //[FIREBASE|APPSFLYER][onNewIntent]

    //[FIREBASE][handleNotificationIntent]

    //[FIREBASE][scheduleClickTracking]

    //[FIREBASE][openExternalBrowser]
}