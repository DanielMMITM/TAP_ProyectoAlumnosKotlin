package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Bienvenida : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bienvenida)

        val btnAlumnoLogin = findViewById<Button>(R.id.btnAlumnoLogin)
        val btnProfesorLogin = findViewById<Button>(R.id.btnProfesorLogin)


        btnAlumnoLogin.setOnClickListener{
            val intent = Intent(this, LoginAlumno::class.java)
            startActivity(intent)
        }

        btnProfesorLogin.setOnClickListener{
            val intent = Intent(this, LoginProfesor::class.java)
            startActivity(intent)
        }
    }
}