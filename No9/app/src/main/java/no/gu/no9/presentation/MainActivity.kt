package no.gu.no9.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import no.gu.no9.presentation.feature.RecruitmentRequestsScreen
import no.gu.no9.presentation.feature.SignInScreen
import no.gu.no9.presentation.theme.No9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            No9Theme {
                Box(Modifier.fillMaxSize()) {
                    BaseApp()
                }
            }
        }
    }

    @Composable
    private fun BaseApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = AppNavigationItem.SignIn.route) {
            composable(AppNavigationItem.SignIn.route) {
                SignInScreen(navController = navController)
            }
            composable(AppNavigationItem.SignUp.route) {

            }
            composable(AppNavigationItem.RecruitmentRequests.route) {
                RecruitmentRequestsScreen()
            }
        }
    }
}