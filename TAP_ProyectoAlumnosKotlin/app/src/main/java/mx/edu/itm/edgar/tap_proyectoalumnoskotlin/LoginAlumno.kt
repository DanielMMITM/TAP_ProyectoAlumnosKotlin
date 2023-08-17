package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject

class LoginAlumno : AppCompatActivity() {

    private lateinit var loginAlumnoControl: EditText
    private lateinit var loginAlumnoContra: EditText
    private lateinit var btnInicioAlumno: Button
    private lateinit var btnRegistroAlumno: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_alumno)

        Toast.makeText(this, "Menu alumno ", Toast.LENGTH_LONG).show()

        loginAlumnoControl = findViewById(R.id.loginAlumnoControl)
        loginAlumnoContra = findViewById(R.id.loginAlumnoContra)
        btnInicioAlumno = findViewById(R.id.btnInicioAlumno)
        btnRegistroAlumno = findViewById(R.id.btnRegistroAlumno)

        btnInicioAlumno.setOnClickListener { login() }

        btnRegistroAlumno.setOnClickListener {
            val intent = Intent(this@LoginAlumno, RegistroAlumno::class.java )
            startActivity(intent)
            loginAlumnoControl.setText("")
            loginAlumnoContra.setText("")
            loginAlumnoContra.requestFocus()
        }
    }

    private fun login() {
        if(loginAlumnoControl.text.isEmpty()){
            loginAlumnoControl.setError("Escriba su número de control")
            return
        }
        if(loginAlumnoContra.text.isEmpty()){
            loginAlumnoContra.setError("Escriba su contraseña")
            return
        }

        try {
            val usuario = loginAlumnoControl.text.toString()
            val pass = loginAlumnoContra.text.toString()

            val url = "${resources.getString(R.string.ws)}findUsuarioAlumno.php?noControl=$usuario"

            val params = HashMap<String, String>()
            params.put("usuario", usuario)

            object : MyUtils() {
                override fun formatResponse(response: String) {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")
                    val jsonP = output.getJSONObject(0)
                    val contra = jsonP.getString("password")

                    if(contra.equals(pass)){
                        val intent = Intent(this@LoginAlumno, MenuAlumnoPrincipal::class.java)
                        intent.putExtra("usuario", usuario)
                        loginAlumnoControl.text.clear()
                        loginAlumnoContra.text.clear()
                        startActivity(intent)
                    } else{
                        Toast.makeText(this@LoginAlumno, "Usuario y/o contraseña incorrectos", Toast.LENGTH_LONG).show()
                    }
                }
            }.consumePost(this, url, params)
        } catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }
    }
}