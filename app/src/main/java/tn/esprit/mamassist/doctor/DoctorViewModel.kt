package tn.esprit.mamassist.doctor
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tn.esprit.mamassist.data.network.ApiClient
import tn.esprit.mamassist.data.network.DoctorResponse

class DoctorViewModel : ViewModel() {
    private val apiService = ApiClient.apiService

    val doctorsByCategory = MutableLiveData<List<DoctorResponse>>()
    val favoriteDoctors = MutableLiveData<List<DoctorResponse>>()

    fun fetchDoctorsByCategory(bio: String) {
        viewModelScope.launch {
            val response = apiService.getDoctorsByCategory(bio).execute()
            if (response.isSuccessful) {
                doctorsByCategory.postValue(response.body())
            }
        }
    }

    fun fetchFavoriteDoctors() {
        viewModelScope.launch {
            val response = apiService.getFavoriteDoctors().execute()
            if (response.isSuccessful) {
                favoriteDoctors.postValue(response.body())
            }
        }
    }

    fun addDoctorToFavorites(id: String) {
        viewModelScope.launch {
            val response = apiService.addDoctorToFavorites(id).execute()
            if (response.isSuccessful) {
                fetchFavoriteDoctors() // Rafraîchir la liste des favoris
            }
        }
    }}
