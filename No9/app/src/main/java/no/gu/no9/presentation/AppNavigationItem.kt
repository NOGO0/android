package no.gu.no9.presentation

sealed class AppNavigationItem(val route: String) {
    data object SignIn : AppNavigationItem("signIn")

    data object SignUp1 : AppNavigationItem("signUp1")

    data object SignUp2 : AppNavigationItem("signUp2")

    data object SignUp3 : AppNavigationItem("signUp3")

    data object SignUp4 : AppNavigationItem("signUp4")

    data object RecruitmentRequests : AppNavigationItem("recruitmentRequests")

    data object Filter : AppNavigationItem("filter")

    data object Detail : AppNavigationItem("recruitmentDetail")
}