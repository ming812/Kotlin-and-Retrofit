package forfun.testing

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.listlayout.view.*

class ListAdapter(val context : Context, val info : List<ApiObject>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val layoutInflater = LayoutInflater.from(parent?.context)
        val listlayout = layoutInflater.inflate(R.layout.listlayout,parent,false)
        return ViewHolder(listlayout)
    }

    override fun getItemCount(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return info.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder?.itemView?.adpaterText?.text ="Success"
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){}
}