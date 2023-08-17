package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject
import java.lang.Exception

class DatosPersonalesAlumno : AppCompatActivity() {

    private lateinit var alumnPass : String
    private lateinit var idalumno : String
    private lateinit var usuario : String
    private lateinit var nombreAlumno : TextView
    private lateinit var nombreNuevo : EditText
    private lateinit var btnNuevoNombre : Button
    private lateinit var numControl : TextView
    private lateinit var intSemestre : TextView
    private lateinit var carAlum : TextView
    private lateinit var ContraseniaAnterior : EditText
    private lateinit var ContraseniaNueva : EditText
    private lateinit var btnGuardarNuevaContrasenia : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos_personales_alumno)

        alumnPass = ""
        idalumno = ""
        nombreAlumno = findViewById(R.id.nombreAlumno)
        nombreNuevo = findViewById(R.id.nombreNuevo)
        btnNuevoNombre = findViewById(R.id.btnNuevoNombre)
        numControl = findViewById(R.id.numControl)
        intSemestre = findViewById(R.id.intSemestre)
        carAlum = findViewById(R.id.carAlum)
        ContraseniaAnterior = findViewById(R.id.ContraseniaAnterior)
        ContraseniaNueva = findViewById(R.id.ContraseniaNueva)
        btnGuardarNuevaContrasenia = findViewById(R.id.btnGuardarNuevaContrasenia)

        usuario = intent.getStringExtra("usuario").toString()

        try{
            val url = "${resources.getString(R.string.ws)}findUsuarioAlumno.php?noControl=$usuario"
            object : MyUtils(){
                override fun formatResponse(response: String) {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")
                    val jsonP = output.getJSONObject(0)
                    idalumno = jsonP.getString("id_alumno").toString()
                    nombreAlumno.text = jsonP.getString("nombre").toString()
                    numControl.text = jsonP.getString("noControl").toString()
                    intSemestre.text = jsonP.getString("id_semestre").toString()
                    carAlum.text = jsonP.getString("carrera").toString()
                    alumnPass = jsonP.getString("password").toString()
                }

            }.consumeGet(this, url)
        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }

        btnNuevoNombre.setOnClickListener { guardarName()}

        btnGuardarNuevaContrasenia.setOnClickListener { guardarPass() }
    }

    private fun guardarName(){
        if(nombreNuevo.text.isEmpty()){
            nombreNuevo.error = "Debes escribir el nombre nuevo"
            return
        }
        else{
            val url = "${resources.getString(R.string.ws)}actualizarName.php?id_alumno=$idalumno&nombre=${nombreNuevo.text}"
            try{
                object : MyUtils(){
                    override fun formatResponse(response: String) {
                        nombreAlumno.text = nombreNuevo.text
                        nombreNuevo.text.clear()
                        Toast.makeText(this@DatosPersonalesAlumno, "Se cambio el nombre correctamente", Toast.LENGTH_LONG).show()
                    }

                }.consumeGet(this, url)
            }
            catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun guardarPass(){
        if(ContraseniaAnterior.text.isEmpty()){
            ContraseniaAnterior.error = "No puedes dejar este campo vacio"
            return
        }
        else{
            if(ContraseniaNueva.text.isEmpty()){
                ContraseniaNueva.error = "No puedes dejar este campo vacio"
                return
            }
            else{
                if(alumnPass == ContraseniaAnterior.text.toString()){
                    val url = "${resources.getString(R.string.ws)}actualizarPass.php?id_alumno=$idalumno&password=${ContraseniaNueva.text}"
                    try{
                        object : MyUtils(){
                            override fun formatResponse(response: String) {
                                alumnPass = ContraseniaNueva.text.toString()
                                ContraseniaAnterior.text.clear()
                                ContraseniaNueva.text.clear()
                                Toast.makeText(this@DatosPersonalesAlumno, "Se cambio la contraseña correctamente", Toast.LENGTH_LONG).show()
                            }

                        }.consumeGet(this, url)
                    }
                    catch (e : Exception){
                        e.printStackTrace()
                        Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                    }
                }
                else{
                    Toast.makeText(this, "Tu contraseña actual no coincide", Toast.LENGTH_LONG).show()
                    return
                }
            }
        }
    }
}