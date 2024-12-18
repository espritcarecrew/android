package tn.esprit.mamassist.Authentification.connecte

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import tn.esprit.mamassist.data.network.LoginResponse
import tn.esprit.mamassist.data.repository.UserRepository


// Define the UI state
data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val userId: String? = null,

    val token: String? = null,
    val errorMessage: String? = null,
    val hasNavigated: Boolean = false
)

open class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _loginUiState: MutableLiveData<LoginUiState> = MutableLiveData(LoginUiState())
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState // Expose as LiveData


    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply() // Clear all preferences
    }
    // Function to handle user login
    fun loginUser(email: String, password: String,  onError: (String) -> Unit) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState(isLoading = true) // Loading

            try {
                val response = userRepository.login(email, password)

                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        val isLoggedIn = true
                        val userId = loginResponse.userId
                        val accessToken = loginResponse.accessToken

                        _loginUiState.value = LoginUiState(isLoggedIn = true, token = accessToken)
                    } else {
                        onError("Invalid response from server")
                    }
                } else {
                    onError("Login failed: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Error: ${e.message}")
            } finally {
                _loginUiState.value = LoginUiState(isLoading = false)
            }
        }
    }

}