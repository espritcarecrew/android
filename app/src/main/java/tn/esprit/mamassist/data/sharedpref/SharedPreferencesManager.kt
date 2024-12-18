import android.content.Context

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    // Sauvegarder le token et "rememberMe"
    fun saveToken(token: String, rememberMe: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.putBoolean("rememberMe", rememberMe)
        editor.apply()
    }

    // Récupérer le token
    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    // Récupérer l'état "rememberMe"
    fun getRememberMe(): Boolean {
        return sharedPreferences.getBoolean("rememberMe", false)
    }

    // Supprimer le token
    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.remove("rememberMe")
        editor.apply()
    }

    // Sauvegarder l'userId
    fun saveUserId(userId: String) {
        val editor = sharedPreferences.edit()
        editor.putString("userId", userId)
        editor.apply()
    }

    // Récupérer l'userId
    fun getUserId(): String? {
        return sharedPreferences.getString("userId", null)
    }

    // Effacer l'userId
    fun clearUserId() {
        val editor = sharedPreferences.edit()
        editor.remove("userId")
        editor.apply()
    }
}
