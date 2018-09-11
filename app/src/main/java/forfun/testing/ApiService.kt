package forfun.testing

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.logging.Logger



class ApiService   {

    var callback : ApiDataCallBack? = null
    private val log = Logger.getLogger(MainActivity::class.java.name)

    //WorkFlow    application  -> retrofit -> okhttp -> http
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor)
            .build()

    val retrofit = Retrofit.Builder()
            .baseUrl("https://t5u9lyu8j2.execute-api.ap-northeast-1.amazonaws.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service = retrofit.create(ApiClient::class.java)
    val call : Call<List<ApiObject>> = service.getUser("getcategory")


    fun CallApi() : List<ApiObject>?{
        var result : List<ApiObject>? = null
//
//        result = call.execute().body()
//        log.info("CallApi reulst ="+result.toString())

        call.enqueue(object : Callback<List<ApiObject>> {
            override fun onResponse(call: Call<List<ApiObject>>?, response: Response<List<ApiObject>>?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                print(response)
//                var receiveObject : MutableList<JSONObject>? = null
//                var name : List<String>? = null
//                var style : List<String>? = null
//
//                fun convertToJSON(arrayToJSON : JSONArray){
//                    for (i in 0..arrayToJSON.length()){
//                        receiveObject?.add(arrayToJSON.get(i) as JSONObject)
//                    }
//                }
//                log.info("return message = "+convertToJSON(response?.body()?.receiveArray!!))
                val nameValue = response?.body()
                log.info("first name = "+ nameValue?.get(0)?.name)
                result = response?.body()
                MainActivity.apiData = result
                callback?.CallApiData()
            }

            override fun onFailure(call: Call<List<ApiObject>>?, t: Throwable?) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                log.info("response fail = " + call?.request()?.url())
                log.info("response fail message = "+ t.toString())
            }

        })
        return result
    }

    fun setCallBack(callbackObject : ApiDataCallBack){
        callback = callbackObject
    }

    fun setCallbacker(listener : ApiDataCallBack.() -> Unit){
        var ca = MainActivity()
        ca.listener()
        setCallBack(ca)
    }

}