package no.gu.no9.presentation

sealed class AppNavigationItem(val route: String) {
    data object Login : AppNavigationItem("login")
}