package forfun.testing

import android.app.Activity
import android.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.coroutines.experimental.launch
import java.util.logging.Logger

class MainActivity : AppCompatActivity() , ApiDataCallBack  {
    override fun CallApiData() {
        loadFragmentJava()
    }
    companion object {
         open var apiData : List<ApiObject>? = null
    }
    private var test : ApiService

    public val log = Logger.getLogger(MainActivity::class.java.name)
    private var manager : FragmentManager? = null
    private var navigation : BottomNavigationView? = null
    private val navigationItemListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId){
            R.id.navigation_java -> {
                loadFragmentJava()
                return@OnNavigationItemSelectedListener  true
            }

            R.id.navigation_kotlin -> {
                loadFragmentKotlin()
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_swift -> {
                loadFragmentSwift()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    init {
        test = ApiService()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        apiData=test.CallApiData()
//        test  = ApiService()
//        log.info("testing result = "+test.CallApi())
        navigation= findViewById(R.id.navigation)
        navigation?.setOnNavigationItemSelectedListener(navigationItemListener)
        log.info("Main Activity onCreate apiData = "+apiData)
//        loadFragmentTemp()
        test.apply { setCallbacker {
            apiData = CallApi()
        } }
    }



    fun loadFragmentJava(){
            manager = fragmentManager
            val transaction = manager!!.beginTransaction()
//        val transaction = FragmentJava().childFragmentManager.beginTransaction()
            val fragment = FragmentJava()
            transaction.replace(R.id.fragementholder, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

    }

    fun loadFragmentKotlin(){
        manager = fragmentManager
        val transaction = manager!!.beginTransaction()
//        val transaction = FragmentKotlin().childFragmentManager.beginTransaction()
        val fragment = FragmentKotlin()
        transaction.replace(R.id.fragementholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun loadFragmentSwift(){
        manager = fragmentManager
        val transaction = manager!!.beginTransaction()
//        val transaction = FragmentSwift().childFragmentManager.beginTransaction()
        val fragment = FragmentSwift()
        transaction.replace(R.id.fragementholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun loadFragmentTemp(){
//        val transaction = manager.beginTransaction()
        val transaction = FragmentTemp().childFragmentManager.beginTransaction()
        val fragment = FragmentTemp()
        transaction.replace(R.id.fragementholder,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
