package tn.esprit.mamassist

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import tn.esprit.mamassist.data.network.ApiService
import tn.esprit.mamassist.data.network.LoginResponse
import tn.esprit.mamassist.data.repository.UserRepository

data class LoginUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val token: String? = null,
    val errorMessage: String? = null
)

class LoginViewModel(
    private val userRepository: UserRepository,
    private val apiService: ApiService
) : ViewModel() {

    private val _loginUiState: MutableLiveData<LoginUiState> = MutableLiveData(LoginUiState())
    val loginUiState: LiveData<LoginUiState> get() = _loginUiState

    fun googleLogin(context: Context) {
        viewModelScope.launch {
            try {
                val response = apiService.getGoogleLoginUrl()
                if (response.isSuccessful) {
                    val loginUrl = response.body()
                    loginUrl?.let {
                        Log.d("LoginViewModel", "Google login URL: $it")
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                        context.startActivity(intent)
                    }
                } else {
                    Log.e("LoginViewModel", "Failed to fetch Google login URL")
                }
            } catch (e: Exception) {
                Log.e("LoginViewModel", "Error in Google login", e)
            }
        }
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            _loginUiState.value = LoginUiState(isLoading = true)

            try {
                val response: Response<LoginResponse> = userRepository.login(email, password)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _loginUiState.value = LoginUiState(isLoggedIn = true, token = it.accessToken)
                    }
                } else {
                    _loginUiState.value = LoginUiState(errorMessage = response.message())
                }
            } catch (e: Exception) {
                _loginUiState.value = LoginUiState(errorMessage = e.message)
            }
        }
    }
}