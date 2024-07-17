package no.gu.no9.presentation.feature.signin

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import no.gu.no9.data.api.ApiProvider
import no.gu.no9.data.request.SignInRequest
import no.gu.no9.presentation.AppNavigationItem

class SignInViewModel : ViewModel() {

    fun signIn(
        id: String,
        password: String,
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                ApiProvider.authApi().signIn(SignInRequest(accountId = id, password = password))
            }.onSuccess {
                println(it)
            }.onFailure {
                println(it)
            }
        }
    }
}