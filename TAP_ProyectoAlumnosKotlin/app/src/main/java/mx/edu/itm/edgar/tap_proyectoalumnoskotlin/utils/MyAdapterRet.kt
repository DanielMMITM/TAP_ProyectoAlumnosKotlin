package mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.R

class MyAdapterRet(val context : Context, val res : Int, val list : ArrayList<MateriaRet>)  : RecyclerView.Adapter<MyAdapterRet.MyViewHolder>() {

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

        fun bind(c: MateriaRet) {
            val textMateria = itemView.findViewById<TextView>(R.id.textRowMateria)
            val textSemestre = itemView.findViewById<TextView>(R.id.textRowSemestre)
            val textEstado = itemView.findViewById<TextView>(R.id.textRowEstado)

            textMateria.text = "${c.nombre_materia}"
            textSemestre.text = c.id_semestre
            textEstado.text = c.estado
        }
    }
}