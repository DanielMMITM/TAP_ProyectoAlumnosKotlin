package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class RegistroAlumno : AppCompatActivity() {

    private lateinit var array : JSONArray
    var numc = ""
    var sem = 0
    private lateinit var nombreRegistro : EditText
    private lateinit var nControlRegistro: EditText
    private lateinit var carreraRegistro: EditText
    private lateinit var semestreRegistro: EditText
    private lateinit var passwordAlumnoRegistro: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_alumno)

        array = JSONArray()
        nombreRegistro = findViewById(R.id.nombreRegistro)
        nControlRegistro = findViewById(R.id.nControlRegistro)
        carreraRegistro = findViewById(R.id.carreraRegistro)
        semestreRegistro = findViewById(R.id.semestreRegistro)
        passwordAlumnoRegistro = findViewById(R.id.passwordAlumnoRegistro)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancel)

        btnGuardar.setOnClickListener {
            if(registrar() == true){
                try {
                    GlobalScope.launch(Dispatchers.IO) {
                        networkCall1()
                        networkCall2()
                        networkCall3()
                    }
                    Toast.makeText(this, "Registrado correctamente", Toast.LENGTH_LONG).show()
                    finish()
                }
                catch (e : Exception){
                    e.printStackTrace()
                    Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                }
            }
        }

        btnCancelar.setOnClickListener { finish() }
    }

    suspend fun networkCall1(){
        delay(100L)
        try {
            val nombre = nombreRegistro.text.toString()
            val nControl = nControlRegistro.text.toString()
            numc = nControl
            val carrera = carreraRegistro.text.toString()
            val semestre = semestreRegistro.text.toString()
            sem = semestre.toInt()
            val pass = passwordAlumnoRegistro.text.toString()

            val url = "${resources.getString(R.string.ws)}agrega.php"

            val params = HashMap<String, String>()

            params.put("nombre", nombre)
            params.put("noControl", nControl)
            params.put("carrera", carrera)
            params.put("semestre", semestre)
            params.put("password", pass)
            params.put("evaluacionDoc", "0")

            object : MyUtils() {
                override fun formatResponse(response: String) {
                    try{
                        println("ResponsePOSTALUMNO: " + response)
                    }
                    catch (e : java.lang.Exception){
                        e.printStackTrace()
                        Toast.makeText(this@RegistroAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                    }
                }
            }.consumePost(this, url, params)
        }
        catch (e : java.lang.Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }
    }

    suspend fun networkCall2(){
        delay(150L)
        try {
            val url = "${resources.getString(R.string.ws)}consultarMaterias.php"
            object : MyUtils(){
                override fun formatResponse(response: String) {
                    println("ResponseGETMATERIAS: " + response)
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")
                    for (i in 0..output.length()-1) {
                        val jsonP = output.getJSONObject(i)
                        val semest = jsonP.getString("id_semestre").toInt()
                        if (semest < sem) {
                            array.put(jsonP.getString("id_materia").toString())
                        }
                    }
                }

            }.consumeGet(this, url)
        }
        catch (e : java.lang.Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }
    }

    suspend fun networkCall3(){
        println("entra 1")
        delay(500L)
        try {
            val url = "${resources.getString(R.string.ws)}asignarCalif.php"
            println("entra 2")

            for (i in 0..array.length()-1){
                delay(200L)
                var params2 = HashMap<String, String>()
                val calif = (70..100).random()
                params2.put("calif", calif.toString())
                params2.put("id_alumno", numc)
                params2.put("id_materia", array[i].toString())
                println("entra 3")

                object : MyUtils() {
                    override fun formatResponse(response: String) {
                        try{
                            println("entra 4")
                            println("ResponsePOSTCALIF: " + response)
                        }
                        catch (e : java.lang.Exception){
                            println("entra 5")
                            e.printStackTrace()
                            Toast.makeText(this@RegistroAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                        }
                    }
                }.consumePost(this, url, params2)
            }
        }
        catch (e : java.lang.Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }
    }

    private fun registrar() : Boolean{
        if(nombreRegistro.text.isEmpty()){
            nombreRegistro.error = "El nombre no debe ser vacio"
            return false
        }
        else{
            if(nControlRegistro.text.isEmpty()){
                nControlRegistro.error = "El numero de control no debe ser vacio"
                return false
            }
            else{
                if(carreraRegistro.text.isEmpty()){
                    carreraRegistro.error = "La carrera no debe ser vacia"
                    return false
                }
                else{
                    if(semestreRegistro.text.isEmpty()){
                        semestreRegistro.error = "El semestre no debe ser vacio"
                        return false
                    }
                    else{
                        if(passwordAlumnoRegistro.text.isEmpty()){
                            passwordAlumnoRegistro.error = "La contraseña no debe ser vacia"
                            return false
                        }
                        else{
                            return true
                        }
                    }
                }
            }
        }
    }
}