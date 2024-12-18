package tn.esprit.mamassist.data.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import tn.esprit.mamassist.data.repository.UserRepository

data class PredictionRequest(
    val age: Int,
    val systolicBP: Float,
    val diastolicBP: Float,
    val bs: Float,
    val bodyTemp: Float,
    val heartRate: Int
)

data class PredictionResponse(
    val riskLevel: String
)

data class ResponseRequest(
    val query: String
)

// Réponse de l'API
data class ResponseResponse(
    val query: String,
    val response: String,
    val category: String
)

data class SymptomsRequest(
    val userId: String,
    val symptoms: List<String>
)

data class SymptomsResponse(
    val success: Boolean,
    val message: String
)
data class Baby(
    var name: String = "",
    var age: String = "",
    var weight: String = "",
    var height: String = "",
    var healthIssues: String = "",
    var behavior: String = ""
)

data class LoginRequest(
    val email: String,
    val password: String
)


data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
data class DoctorRequest(
    val name: String,
    val specialization: String,
    val experience: Int,
    val contact: String,
    val location: String,
    val availableTimes: List<String>
)

data class DoctorResponse(
    val name: String,
    val specialization: String,
    val experience: Int,
    val contact: String,
    val location: String,
    val availableTimes: List<String>
)
data class DoctorSelectionRequest(
    val userId: String,
    val doctorName: String,
    val category: String
)

data class DoctorRecommendationResponse(
    val doctorName: String,
    val category: String
)
data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String,
    val bio: String,
    val imageUri: String,
    val role: String
)

data class SignUpResponse(
    val success: Boolean,
    val message: String
)

data class ForgotPasswordRequest(val email: String)
data class ForgotPasswordResponse(val message: String, val resetCode: String)

data class VerifyCodeRequest(val email: String ,val code: String)

data class VerifyCodeResponse(val message: String)

data class ResetPasswordRequest(
    val email: String,
    val code: String,
    val newPassword: String
)
data class CheckInRequest(
    val userId: String,
    val date: String,
    val mood: String, // Change le type de Int à String pour accepter les descriptions
    val discomforts: List<String> = emptyList(),
    val elaboration: String? = null
)


object Routes {
    val Login = Route("login")
    val ConfirmCode = Route("confirmCode")
    val ForgetPassword = Route("forgetPassword")
    val SignUp = Route("signUp")
    val ResetPass = Route("resetPass")
}

data class Route(val route: String)

interface ApiService {
    @POST("auth/signup")
    suspend fun signUp(@Body signupRequest: UserRepository.SignUpRequest): Response<SignUpResponse>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("auth/forgot-password")
    fun forgotPassword(@Body request: ForgotPasswordRequest): Call<ForgotPasswordResponse>

    @POST("auth/verify-reset-code")
    fun verifyResetCode(@Body request: VerifyCodeRequest): Call<VerifyCodeResponse>

    @POST("auth/reset-password")
    fun resetPassword(@Body request: ResetPasswordRequest): Call<Void>

    @POST("symptoms/save")
    suspend fun saveSymptoms(@Body request: SymptomsRequest): Response<SymptomsResponse>

    @POST("doctors/create")
    suspend fun createDoctor(@Body request: DoctorRequest): Response<DoctorResponse>

    @GET("doctors/all")
    suspend fun getAllDoctors(): Response<List<DoctorResponse>>

    @POST("users/save-selection")
    suspend fun saveDoctorSelection(@Body request: DoctorSelectionRequest): Response<Unit>

    @GET("users/recommendations/{userId}")
    suspend fun getDoctorRecommendations(@Path("userId") userId: String): Response<List<DoctorRecommendationResponse>>

    @POST("checkin")
    fun createCheckIn(@Body checkInRequest: CheckInRequest): Call<Void>

    @POST("response")
    suspend fun sendQuestion(@Body request: ResponseRequest): Response<ResponseResponse>

    @POST("predictions")
    suspend fun makePrediction(@Body request: PredictionRequest): Response<PredictionResponse>
}


