package tn.esprit.mamassist.Register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import tn.esprit.mamassist.Authentification.inscrire.SignUpUiState
import tn.esprit.mamassist.data.network.SignUpResponse
import tn.esprit.mamassist.data.repository.UserRepository


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
            Log.d("RegisterViewModel", "Start: Sign-up for $email")

            try {
                val response = userRepository.signUp(name, email, password, bio, imageUri, role)

                if (response.isSuccessful) {
                    val userResponse = response.body()
                    if (userResponse != null) {
                        Log.d("RegisterViewModel", "Sign-up successful: ${userResponse.message}")
                        _signUpUiState.value = SignUpUiState(
                            isSignedUp = true,
                            successMessage = "Registration Successful!",
                            user = userResponse
                        )
                    } else {
                        Log.e("RegisterViewModel", "Empty response body")
                        _signUpUiState.value = SignUpUiState(errorMessage = "Empty response from server")
                    }
                } else {
                    Log.e("RegisterViewModel", "Failed: ${response.message()}")
                    _signUpUiState.value = SignUpUiState(errorMessage = "Error: ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("RegisterViewModel", "Exception: ${e.message}")
                _signUpUiState.value = SignUpUiState(errorMessage = "Exception: ${e.message}")
            }
        }
    }
}
