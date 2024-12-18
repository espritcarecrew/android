package tn.esprit.mamassist.Authentification.inscrire

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import tn.esprit.mamassist.data.network.SignUpResponse
import tn.esprit.mamassist.data.repository.UserRepository

// Define the UI state for SignUp
data class SignUpUiState(
    val isLoading: Boolean = false,
    val isSignedUp: Boolean = false,
    val user: SignUpResponse? = null, // Add user data here
    val successMessage: String? = null,
    val errorMessage: String? = null,
    val hasNavigated: Boolean = false
)

class RegisterViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _signUpUiState: MutableLiveData<SignUpUiState> = MutableLiveData(SignUpUiState())
    val signUpUiState: LiveData<SignUpUiState> get() = _signUpUiState

    fun signUpUser(
        name: String,
        email: String,
        password: String,
        bio: String,
        imageUri: String,
        role: String
    ) {
        viewModelScope.launch {
            _signUpUiState.value = SignUpUiState(isLoading = true)
            try {
                val response = userRepository.signUp(name, email, password, bio, imageUri, role)
                Log.d("RegisterViewModel", "Réponse reçue: ${response.body()} - Code: ${response.code()}")

                if (response.isSuccessful) {
                    Log.d("RegisterViewModel", "Succès de l'inscription : ${response.body()}")
                    _signUpUiState.value = SignUpUiState(
                        isSignedUp = true,
                        successMessage = "Sign-up successful!"
                    )
                } else {
                    Log.e("RegisterViewModel", "Erreur d'inscription : ${response.errorBody()?.string()}")
                    _signUpUiState.value = SignUpUiState(errorMessage = "Sign-up failed: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Exception lors de l'inscription: ${e.message}")
                _signUpUiState.value = SignUpUiState(errorMessage = "Exception: ${e.message}")
            }
        }
    }
}