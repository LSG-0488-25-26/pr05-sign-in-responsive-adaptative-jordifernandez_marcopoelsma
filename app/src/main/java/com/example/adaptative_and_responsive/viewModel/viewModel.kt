package com.example.adaptative_and_responsive.viewmodel

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.adaptative_and_responsive.model.User

class viewModel : ViewModel() {

    // -----------------------
    // Estado del usuario
    // -----------------------
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    // -----------------------
    // Fecha de nacimiento con cursor controlado
    // -----------------------
    private val _birthDateValue = MutableStateFlow(TextFieldValue(""))
    val birthDateValue: StateFlow<TextFieldValue> = _birthDateValue

    fun onBirthDateChange(input: TextFieldValue) {
        val numbersOnly = input.text.filter { it.isDigit() }
        val builder = StringBuilder()

        for (i in numbersOnly.indices) {
            builder.append(numbersOnly[i])
            if (i == 1 || i == 3) builder.append('/')
            if (builder.length >= 10) break
        }

        val newText = builder.toString()
        _birthDateValue.value = TextFieldValue(
            text = newText,
            selection = TextRange(newText.length)
        )

        _user.value = _user.value.copy(birthDate = newText)
    }

    // -----------------------
    // Navegación a Home
    // -----------------------
    private val _showHome = MutableStateFlow(false)
    val showHome: StateFlow<Boolean> = _showHome

    fun goToHome() { _showHome.value = true }
    fun backToInitial() { _showHome.value = false }

    // -----------------------
    // Mensaje de error
    // -----------------------
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    // -----------------------
    // Actualización de campos
    // -----------------------
    fun onFullNameChange(value: String) { _user.value = _user.value.copy(fullName = value) }
    fun onEmailChange(value: String) { _user.value = _user.value.copy(email = value) }
    fun onPhoneChange(value: String) { _user.value = _user.value.copy(phone = value) }
    fun onUsernameChange(value: String) { _user.value = _user.value.copy(username = value) }
    fun onPasswordChange(value: String) { _user.value = _user.value.copy(password = value) }
    fun onConfirmPasswordChange(value: String) { _user.value = _user.value.copy(confirmPassword = value) }
    fun onTermsAcceptedChange(value: Boolean) { _user.value = _user.value.copy(termsAccepted = value) }

    // -----------------------
    // Validación de login
    // -----------------------
    fun isLoginValid(inputUsername: String, inputPassword: String): Boolean {
        val u = _user.value
        _errorMessage.value = ""

        return if (inputUsername == u.username && inputPassword == u.password) {
            true
        } else {
            _errorMessage.value = "Usuari o password incorrectes"
            false
        }
    }

    // -----------------------
    // Validación de registro
    // -----------------------
    fun isRegisterValid(): Boolean {
        val u = _user.value
        _errorMessage.value = ""

        // Nombre completo
        if (u.fullName.isBlank() || !u.fullName.matches(Regex("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$"))) {
            _errorMessage.value = "Nom complet invàlid"
            return false
        }

        // Fecha de nacimiento
        if (!u.birthDate.matches(Regex("^\\d{2}/\\d{2}/\\d{4}\$"))) {
            _errorMessage.value = "Data de naixement invàlida (dd/mm/yyyy)"
            return false
        }

        // Email
        if (!u.email.matches(Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"))) {
            _errorMessage.value = "Email invàlid"
            return false
        }

        // Teléfono
        if (!u.phone.matches(Regex("^\\d{9,}\$"))) {
            _errorMessage.value = "Telèfon invàlid"
            return false
        }

        // Usuario
        if (u.username.length < 4) {
            _errorMessage.value = "Nom d'usuari ha de tenir al menys 4 caràcters"
            return false
        }

        // Password
        if (u.password.length < 6) {
            _errorMessage.value = "Password ha de tenir al menys 6 caràcters"
            return false
        }

        // Confirmación password
        if (u.password != u.confirmPassword) {
            _errorMessage.value = "Les passwords no coincideixen"
            return false
        }

        // Términos
        if (!u.termsAccepted) {
            _errorMessage.value = "Has de acceptar els termes i condicions"
            return false
        }

        return true
    }
}
