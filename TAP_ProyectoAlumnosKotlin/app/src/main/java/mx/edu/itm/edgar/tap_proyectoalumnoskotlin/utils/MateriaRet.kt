package mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MateriaRet (
    @SerializedName("nombre_materia")
    val nombre_materia : String,
    @SerializedName("id_semestre")
    val id_semestre : String,
    @SerializedName("estado")
    val estado : String) : Serializable