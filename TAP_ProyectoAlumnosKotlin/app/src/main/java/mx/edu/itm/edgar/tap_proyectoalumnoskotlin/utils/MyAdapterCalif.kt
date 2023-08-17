package mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.R

class MyAdapterCalif(val context : Context, val res : Int, val list : ArrayList<CalificacionM>)  : RecyclerView.Adapter<MyAdapterCalif.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(res, null)
        val vh = MyViewHolder(view)
        return vh
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val obj = list[position]
        holder.bind(obj)
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(c: CalificacionM) {
            val textMateria = itemView.findViewById<TextView>(R.id.textRowMateria)
            val textCalif = itemView.findViewById<TextView>(R.id.textRowCalif)

            textMateria.text = "${c.nombre_materia}"
            textCalif.text = c.calif
        }
    }
}