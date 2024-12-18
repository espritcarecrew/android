package tn.esprit.mamassist.data.repository

import retrofit2.Response
import tn.esprit.mamassist.data.network.ApiService
import tn.esprit.mamassist.data.network.LoginRequest
import tn.esprit.mamassist.data.network.LoginResponse
import tn.esprit.mamassist.data.network.SignUpResponse

class UserRepository(private val apiService: ApiService) {
    data class SignUpRequest(
        val username: String, // Remplacer "name" par "username"
        val email: String,
        val password: String,
        val bio: String,
        val imageUri: String?,
        val role: String
    )
    // Function to create a new user using the API with error handling
    suspend fun signUp(
        username: String, // Modifier ici aussi
        email: String,
        password: String,
        bio: String,
        imageUri: String?,
        role: String
    ): Response<SignUpResponse> {
        val request = SignUpRequest(username, email, password, bio, imageUri, role)
        return apiService.signUp(request)
    }

    // Function to login a user
    suspend fun login(email: String, password: String): Response<LoginResponse> {
        val loginRequest = LoginRequest(email, password)
        return try {
            // Call the API to authenticate the user
            val response = apiService.login(loginRequest)

            // Handle successful response
            if (response.isSuccessful) {
                response
            } else {
                // Return error response in case of failure
                Response.error(response.code(), response.errorBody() ?: throw Exception("Login failed"))
            }
        } catch (exception: Exception) {
            throw Exception("Login failed: ${exception.message}")
        }
    }
}
