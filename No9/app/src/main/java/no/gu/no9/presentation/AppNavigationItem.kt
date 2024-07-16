package no.gu.no9.presentation

sealed class AppNavigationItem(val route: String) {
    data object SignIn : AppNavigationItem("signIn")

    data object SignUp : AppNavigationItem("signUp")

    data object RecruitmentRequests : AppNavigationItem("recruitmentRequests")
}