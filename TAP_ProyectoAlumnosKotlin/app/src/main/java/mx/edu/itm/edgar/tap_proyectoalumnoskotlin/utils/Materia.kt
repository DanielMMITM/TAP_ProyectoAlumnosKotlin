package mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Materia (
    @SerializedName("nombre_materia")
    val nombre_materia : String,
    @SerializedName("hora")
    val hora : String) : Serializable