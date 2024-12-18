package tn.esprit.mamassist.Home.Chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.network.ResponseRequest

class AskQuestionViewModel : ViewModel() {
    fun sendQuestion(userQuestion: String, onResult: (String, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.sendQuestion(ResponseRequest(userQuestion))
                if (response.isSuccessful && response.body() != null) {
                    val data = response.body()!!
                    onResult(data.response, data.category)
                } else {
                    onResult("Erreur API : ${response.code()} - ${response.message()}", "Erreur")
                }
            } catch (e: Exception) {
                onResult("Erreur de connexion : ${e.message}", "Erreur")
            }
        }
    }
}
