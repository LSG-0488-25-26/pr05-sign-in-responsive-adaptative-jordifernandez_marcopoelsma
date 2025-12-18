package com.example.adaptative_and_responsive.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.adaptative_and_responsive.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class viewModel : ViewModel() {

    // -----------------------
    // Usuari actual (formulari)
    // -----------------------
    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    // -----------------------
    // Llista d'usuaris registrats
    // -----------------------
    private val registeredUsers = mutableListOf<User>()

    // -----------------------
    // TextFieldValue per la data amb cursor controlat
    // -----------------------
    private val _birthDateValue = MutableStateFlow(TextFieldValue(""))
    val birthDateValue: StateFlow<TextFieldValue> = _birthDateValue

    fun onBirthDateChange(input: TextFieldValue) {
        val oldValue = _birthDateValue.value
        val oldText = oldValue.text
        val newText = input.text

        val isDeleting = newText.length < oldText.length
        val digits = newText.filter { it.isDigit() }

        val builder = StringBuilder()
        for (i in digits.indices) {
            builder.append(digits[i])
            if (!isDeleting && (i == 1 || i == 3)) builder.append('/')
        }

        val finalText = if (builder.length > 10) builder.substring(0, 10) else builder.toString()
        var cursor = input.selection.start
        if (!isDeleting) {
            if (cursor == 2 || cursor == 5) cursor += 1
        }
        if (cursor > finalText.length) cursor = finalText.length

        _birthDateValue.value = TextFieldValue(finalText, TextRange(cursor))
        _user.value = _user.value.copy(birthDate = finalText)
    }

    // -----------------------
    // Navegació a Home
    // -----------------------
    private val _showHome = MutableStateFlow(false)
    val showHome: StateFlow<Boolean> = _showHome

    fun goToHome() { _showHome.value = true }
    fun backToInitial() { _showHome.value = false }

    // -----------------------
    // Missatge d'error
    // -----------------------
    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    // -----------------------
    // Actualitzar camps
    // -----------------------
    fun onFullNameChange(value: String) { _user.value = _user.value.copy(fullName = value) }
    fun onEmailChange(value: String) { _user.value = _user.value.copy(email = value) }
    fun onPhoneChange(value: String) { _user.value = _user.value.copy(phone = value) }
    fun onUsernameChange(value: String) { _user.value = _user.value.copy(username = value) }
    fun onPasswordChange(value: String) { _user.value = _user.value.copy(password = value) }
    fun onConfirmPasswordChange(value: String) { _user.value = _user.value.copy(confirmPassword = value) }
    fun onTermsAcceptedChange(value: Boolean) { _user.value = _user.value.copy(termsAccepted = value) }

    // -----------------------
    // Login amb validació contra usuaris registrats
    // -----------------------
    fun login(inputUsername: String, inputPassword: String): Boolean {
        _errorMessage.value = ""

        val foundUser = registeredUsers.find { it.username == inputUsername && it.password == inputPassword }

        return if (foundUser != null) {
            _user.value = foundUser
            _showHome.value = true
            true
        } else {
            _errorMessage.value = "Usuari o password incorrectes"
            false
        }
    }

    // -----------------------
    // Validació registre
    // -----------------------
    @RequiresApi(Build.VERSION_CODES.O)
    fun isRegisterValid(): Boolean {
        val u = _user.value
        _errorMessage.value = ""

        // Nom complet
        if (u.fullName.isBlank() || !u.fullName.matches(Regex("^[A-Za-zÀ-ÖØ-öø-ÿ ]+$"))) {
            _errorMessage.value = "Nom complet invàlid"
            return false
        }

        // Data de naixement
        if (!u.birthDate.matches(Regex("^\\d{2}/\\d{2}/\\d{4}\$"))) {
            _errorMessage.value = "Data de naixement invàlida (dd/mm/yyyy)"
            return false
        } else {
            try {
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val birthDate = LocalDate.parse(u.birthDate, formatter)
                val today = LocalDate.now()
                if (!birthDate.isBefore(today)) {
                    _errorMessage.value = "La data ha de ser anterior al dia d’avui"
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

        // Telèfon
        if (!u.phone.matches(Regex("^\\d{9,}\$"))) {
            _errorMessage.value = "Telèfon invàlid"
            return false
        }

        // Usuari
        if (u.username.length < 4) {
            _errorMessage.value = "Nom d'usuari ha de tenir al menys 4 caràcters"
            return false
        }

        // Comprovar si ja existeix username
        if (registeredUsers.any { it.username == u.username }) {
            _errorMessage.value = "Aquest nom d’usuari ja està registrat"
            return false
        }

        // Password
        if (u.password.length < 6) {
            _errorMessage.value = "Password ha de tenir al menys 6 caràcters"
            return false
        }

        // Confirmar password
        if (u.password != u.confirmPassword) {
            _errorMessage.value = "Les passwords no coincideixen"
            return false
        }

        // Termes
        if (!u.termsAccepted) {
            _errorMessage.value = "Has de acceptar els termes i condicions"
            return false
        }

        // Tot correcte, afegim l'usuari a la llista de registrats
        registeredUsers.add(u.copy())
        return true
    }
}
