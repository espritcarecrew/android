package tn.esprit.mamassist.ID

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tn.esprit.mamassist.LoginViewModel
import tn.esprit.mamassist.data.network.ApiService
import tn.esprit.mamassist.data.repository.UserRepository

class ViewModelFactory(
    private val userRepository: UserRepository,
    private val apiService: ApiService // Ajout d'ApiService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(userRepository, apiService) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private var factory: ViewModelFactory? = null

        fun getInstance(userRepository: UserRepository, apiService: ApiService): ViewModelFactory {
            if (factory == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (factory == null) {
                        factory = ViewModelFactory(userRepository, apiService)
                    }
                }
            }
            return factory!!
        }
    }
}
