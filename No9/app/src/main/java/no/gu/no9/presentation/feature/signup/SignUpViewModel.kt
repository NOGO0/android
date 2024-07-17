package no.gu.no9.presentation.feature.signup

import androidx.lifecycle.ViewModel
import no.gu.no9.Area

class SignUpViewModel: ViewModel() {

    companion object {
        var name: String = ""
        var phone: String = ""
        var area = Area.SEOUL
        var age = 0
        var lst = listOf("")
    }
}