package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject

class LoginProfesor : AppCompatActivity() {

    private lateinit var loginProfeUsuario: EditText
    private lateinit var loginProfeContra: EditText
    private lateinit var btnInicioProfe: Button
    private lateinit var btnRegistroProfe: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_profesor)

        Toast.makeText(this, "Menu profesor", Toast.LENGTH_LONG).show()

        loginProfeUsuario = findViewById(R.id.loginProfeUsuario)
        loginProfeContra = findViewById(R.id.loginProfeContra)
        btnInicioProfe = findViewById(R.id.btnInicioProfe)
        btnRegistroProfe = findViewById(R.id.btnRegistroProfe)

        btnInicioProfe.setOnClickListener { login() }

        btnRegistroProfe.setOnClickListener {
            val intent = Intent(this@LoginProfesor, RegistroProfe::class.java )
            startActivity(intent)
            loginProfeUsuario.setText("")
            loginProfeContra.setText("")
            loginProfeUsuario.requestFocus()
        }
    }

    private fun login() {
        if(loginProfeUsuario.text.isEmpty()){
            loginProfeUsuario.setError("Escriba su usuario")
            return
        }
        if(loginProfeContra.text.isEmpty()){
            loginProfeContra.setError("Escriba su contraseña")
            return
        }

        try {
            val usuario = loginProfeUsuario.text.toString()
            val pass = loginProfeContra.text.toString()

            val url = "${resources.getString(R.string.ws)}findUsuarioMaestro.php?usuario=$usuario"

            val params = HashMap<String, String>()
            params.put("usuario", usuario)

            object : MyUtils() {
                override fun formatResponse(response: String) {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")
                    val jsonP = output.getJSONObject(0)
                    val contra = jsonP.getString("password")

                    if(contra.equals(pass)){
                        val intent = Intent(this@LoginProfesor, MenuProfesorPrincipal::class.java)
                        intent.putExtra("usuario", usuario)
                        loginProfeUsuario.text.clear()
                        loginProfeContra.text.clear()
                        startActivity(intent)
                    } else{
                        Toast.makeText(this@LoginProfesor, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show()
                    }
                }
            }.consumePost(this, url, params)
        } catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }
    }
}