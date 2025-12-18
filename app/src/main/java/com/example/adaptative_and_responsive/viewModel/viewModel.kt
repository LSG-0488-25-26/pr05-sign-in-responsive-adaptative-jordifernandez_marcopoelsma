package com.example.adaptative_and_responsive.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
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
        val oldValue = _birthDateValue.value
        val oldText = oldValue.text
        val newText = input.text

        // Detectar si se está borrando
        val isDeleting = newText.length < oldText.length

        // Extraer solo números
        val digits = newText.filter { it.isDigit() }

        // Construir la fecha agregando '/' solo si es necesario
        val builder = StringBuilder()
        for (i in digits.indices) {
            builder.append(digits[i])
            if (!isDeleting) {
                if (i == 1 || i == 3) builder.append('/')
            }
        }

        // Limitar a 10 caracteres
        val finalText = if (builder.length > 10) builder.substring(0, 10) else builder.toString()

        // Calcular nueva posición del cursor
        var cursor = input.selection.start
        if (!isDeleting) {
            // Ajustar cursor si se ha insertado un '/'
            if (cursor == 2 || cursor == 5) cursor += 1
        }
        if (cursor > finalText.length) cursor = finalText.length

        // Actualizar el TextFieldValue y el User
        _birthDateValue.value = TextFieldValue(
            text = finalText,
            selection = TextRange(cursor)
        )
        _user.value = _user.value.copy(birthDate = finalText)
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
    @RequiresApi(Build.VERSION_CODES.O)
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
        } else {
            try {
                val formatter = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val birthDate = java.time.LocalDate.parse(u.birthDate, formatter)
                val today = java.time.LocalDate.now()
                if (!birthDate.isBefore(today)) {
                    _errorMessage.value = "La data de naixement ha de ser anterior al dia d’avui"
                    return false
                }
            } catch (e: Exception) {
                _errorMessage.value = "Data de naixement invàlida"
                return false
            }
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
