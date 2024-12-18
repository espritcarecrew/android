package tn.esprit.mamassist.Home.pression

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.network.PredictionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PredictionViewModel : ViewModel() {
    private val _predictionResult = MutableStateFlow<String?>(null)
    val predictionResult: StateFlow<String?> get() = _predictionResult

    fun getPrediction(data: PredictionRequest) {
        viewModelScope.launch {
            try {
                val response = ApiClient.apiService.makePrediction(data)
                if (response.isSuccessful) {
                    _predictionResult.value = response.body()?.riskLevel
                } else {
                    _predictionResult.value = "Erreur: ${response.code()} - ${response.message()}"
                }
            } catch (e: Exception) {
                _predictionResult.value = "Erreur de connexion: ${e.message}"
            }
        }
    }
}
