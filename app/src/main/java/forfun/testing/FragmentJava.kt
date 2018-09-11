package forfun.testing

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_fragment_java.*
import java.util.logging.Logger
import java.lang.reflect.AccessibleObject.setAccessible




class FragmentJava : Fragment() {
    lateinit var log: Logger
    private var fragmentContext : Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentContext = container?.context
        var view : View = inflater.inflate(R.layout.fragment_fragment_java, container, false)
        // Inflate the layout for this fragment
        var recyclerview = view.findViewById<View>(R.id.recyclerview) as RecyclerView
        log = MainActivity().log
        if(container?.context == null){
            log.info("Container = null")
        }else if(MainActivity.apiData == null){
            log.info("apiData = null")
        }else{
            log.info("FragmentJava Accessed")
        }
        recyclerview.layoutManager = LinearLayoutManager(container?.context)
        recyclerview.adapter = ListAdapter(container!!.context, MainActivity.apiData!!)
        return view

    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDetach() {
        super.onDetach()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            try {
                val childFragmentManager = Fragment::class.java!!.getDeclaredField("mChildFragmentManager")
                childFragmentManager.setAccessible(true)
                childFragmentManager.set(this, null)

            } catch (e: NoSuchFieldException) {
                throw RuntimeException(e)
            } catch (e: IllegalAccessException) {
                throw RuntimeException(e)
            }
        }
    }


}
