package no.gu.no9.presentation.feature.signup

import androidx.lifecycle.ViewModel

class SignUpViewModel: ViewModel() {

    companion object {
        var name: String = ""
        var phone: String = ""
        var area = ""
        var age = 0
        var lst = listOf("")
    }
}