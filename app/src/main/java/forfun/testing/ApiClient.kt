package forfun.testing

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*

interface ApiClient {
    @GET("ReleaseGetCategory/{action}")
    fun getUser(@Path("action") path: String): Call<List<ApiObject>>
}