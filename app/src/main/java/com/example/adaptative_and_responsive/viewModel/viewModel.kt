package com.example.adaptative_and_responsive.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.adaptative_and_responsive.model.User

class RegisterViewModel : ViewModel() {

    // Estado del usuario
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    // Estado para HomeScreen
    private val _showHome = MutableStateFlow(false)
    val showHome: StateFlow<Boolean> = _showHome

    // -----------------------
    // Actualizar datos
    // -----------------------
    fun onFullNameChange(value: String) { _user.value = _user.value.copy(fullName = value) }
    fun onUsernameChange(value: String) { _user.value = _user.value.copy(username = value) }
    fun onPasswordChange(value: String) { _user.value = _user.value.copy(password = value) }
    fun onTermsAcceptedChange(value: Boolean) { _user.value = _user.value.copy(termsAccepted = value) }

    // -----------------------
    // Navegación
    // -----------------------
    fun goToHome() { _showHome.value = true }
    fun backToInitial() { _showHome.value = false }

    // -----------------------
    // Validaciones simples
    // -----------------------
    fun isLoginValid() = _user.value.username.isNotEmpty() && _user.value.password.isNotEmpty()
    fun isRegisterValid() = _user.value.fullName.isNotEmpty() &&
            _user.value.username.isNotEmpty() &&
            _user.value.password.isNotEmpty() &&
            _user.value.termsAccepted
}
