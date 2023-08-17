package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject
import org.w3c.dom.Text
import java.lang.Exception

class MenuAlumnoPrincipal : AppCompatActivity() {

    private lateinit var textNombre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_alumno_principal)

        textNombre = findViewById(R.id.textNombre)
        val usuario = intent.getStringExtra("usuario")

        textNombre.setText("Bienvenido $usuario")

        val datosAlumno = findViewById<TextView>(R.id.datosAlumno)
        datosAlumno.setOnClickListener {
            var intent = Intent(this, DatosPersonalesAlumno::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }

        val horarioAlumno = findViewById<TextView>(R.id.horarioAlumno)
        horarioAlumno.setOnClickListener{
            var intent = Intent(this, HorarioAlumno::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }

        val calificacionesAlumno = findViewById<TextView>(R.id.calificacionesAlumno)
        calificacionesAlumno.setOnClickListener{
            var intent = Intent(this, CalificacionesAlumno::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }

        val reticulaAlumno = findViewById<TextView>(R.id.reticulaAlumno)
        reticulaAlumno.setOnClickListener{
            var intent = Intent(this, ReticulaAlumno::class.java)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }

        val evaluacionDocenteAl = findViewById<TextView>(R.id.evaluacionDocenteAlumno)
        evaluacionDocenteAl.setOnClickListener{
            try {
                val url = "${resources.getString(R.string.ws)}findUsuarioAlumno.php?noControl=$usuario"

                object : MyUtils() {
                    override fun formatResponse(response: String) {
                        val json = JSONObject(response)
                        val output = json.getJSONArray("output")
                        val jsonP = output.getJSONObject(0)
                        val evalDoc = jsonP.getString("evaluacionDoc").toString()
                        if(evalDoc == "0"){
                            var intent = Intent(this@MenuAlumnoPrincipal, EvaluacionDocente::class.java)
                            intent.putExtra("usuario", usuario)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this@MenuAlumnoPrincipal, "Ya realizaste tu evaluacion docente y/o " +
                                    "se acabo el periodo de tiempo para realizarla", Toast.LENGTH_LONG).show()
                        }
                    }
                }.consumeGet(this, url)
            }
            catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
            }
        }

    }

}


