package com.example.adaptative_and_responsive.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.adaptative_and_responsive.model.User

class RegisterViewModel : ViewModel() {

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    fun onFullNameChange(value: String) {
        _user.value = _user.value.copy(fullName = value)
    }

    fun onBirthDateChange(value: String) {
        _user.value = _user.value.copy(birthDate = value)
    }

    fun onEmailChange(value: String) {
        _user.value = _user.value.copy(email = value)
    }

    fun onPhoneChange(value: String) {
        _user.value = _user.value.copy(phone = value)
    }

    fun onUsernameChange(value: String) {
        _user.value = _user.value.copy(username = value)
    }

    fun onPasswordChange(value: String) {
        _user.value = _user.value.copy(password = value)
    }

    fun onTermsAcceptedChange(value: Boolean) {
        _user.value = _user.value.copy(termsAccepted = value)
    }
}
