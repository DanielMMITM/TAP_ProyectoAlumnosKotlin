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
import org.json.JSONObject

class RegistroProfe : AppCompatActivity() {

    var params = HashMap<String, String>()
    private lateinit var nombreRegistro: EditText
    private lateinit var usuarioRegistro: EditText
    private lateinit var passwordRegistro: EditText
    private lateinit var materiaRegistro: EditText
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro_profesor)

        nombreRegistro = findViewById(R.id.nombreRegistro)
        usuarioRegistro = findViewById(R.id.usuarioRegistro)
        passwordRegistro = findViewById(R.id.passwordRegistro)
        materiaRegistro = findViewById(R.id.materiaRegistro)
        btnGuardar = findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            if(guardar() == true){
                GlobalScope.launch(Dispatchers.IO){
                    val answer1 = networkCall1()
                    val answer2 = networkCall2()
                }
            }
        }

        btnCancelar.setOnClickListener { finish() }
    }

    suspend fun networkCall1(){
        delay(500L)
        try {
            val nombre = nombreRegistro.text.toString()
            val usuario = usuarioRegistro.text.toString()
            val pass = passwordRegistro.text.toString()
            val materia = materiaRegistro.text.toString()

            val url = "${resources.getString(R.string.ws)}consultaIDMateria.php?materia=$materia"

            params.put("nombre", nombre)
            params.put("usuario", usuario)
            params.put("password", pass)

            object : MyUtils () {
                override fun formatResponse(response: String) {
                    try {
                        println("responseGET: $response")
                        val json = JSONObject(response)
                        val output = json.getJSONArray("output")
                        if(output.getJSONObject(0).toString() != "" || output.getJSONObject(0) != null){
                            val jsonP = output.getJSONObject(0)
                            val idMateria = jsonP.getString("id_materia").toString()
                            params.put("id_materia",idMateria)
                        }
                    }
                    catch (e : java.lang.Exception){
                        e.printStackTrace()
                        Toast.makeText(this@RegistroProfe, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            }.consumeGet(this, url)
        }
        catch (e : java.lang.Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }
    }

    suspend fun networkCall2(){
        delay(500L)
        try {
            val url2 = "${resources.getString(R.string.ws)}agregaM.php"
            object : MyUtils(){
                override fun formatResponse(response: String) {
                    try{
                        println("responsePOST: $response")
                        Toast.makeText(this@RegistroProfe, "Registrado correctamente", Toast.LENGTH_LONG).show()
                        finish()
                    }
                    catch (e : java.lang.Exception){
                        e.printStackTrace()
                        Toast.makeText(this@RegistroProfe, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                    }
                }

            }.consumePost(this, url2, params)
        }
        catch (e : java.lang.Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }
    }

    private fun guardar(): Boolean{
        if(nombreRegistro.text.isEmpty()){
            nombreRegistro.error = "El nombre no debe ser vacio"
            return false
        }
        else{
            if(usuarioRegistro.text.isEmpty()){
                usuarioRegistro.error = "El usuario no debe ser vacio"
                return false
            }
            else{
                if(passwordRegistro.text.isEmpty()){
                    passwordRegistro.error = "La contraseña no debe ser vacia"
                    return false
                }
                else{
                    if(materiaRegistro.text.isEmpty()){
                        materiaRegistro.error = "La materia no debe ser vacia"
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