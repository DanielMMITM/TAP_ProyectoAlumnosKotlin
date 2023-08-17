package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.Materia
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyAdapter
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject
import java.lang.Exception

class HorarioAlumno : AppCompatActivity() {

    var semestre = 0
    private lateinit var usuario : String
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horario_alumno)

        recyclerView = findViewById(R.id.recyclerMaterias)

        usuario = intent.getStringExtra("usuario").toString()

        GlobalScope.launch {
            try{
                networkCall1()
                networkCall2()
            }
            catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this@HorarioAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
            }
        }
    }

    suspend fun networkCall1(){
        delay(100L)
        try {
            val url = "${resources.getString(R.string.ws)}findUsuarioAlumno.php?noControl=$usuario"

            object : MyUtils() {
                override fun formatResponse(response: String) {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")
                    val jsonP = output.getJSONObject(0)
                    semestre = jsonP.getString("id_semestre").toInt()
                }
            }.consumeGet(this, url)
        } catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }

    }

    suspend fun networkCall2(){
        delay(200L)
        try {
            val url = "${resources.getString(R.string.ws)}consultarMaterias.php"

            object : MyUtils() {
                override fun formatResponse(response: String) {
                    try {
                        var horaInicio = 7
                        var horaFin = 8
                        val json = JSONObject(response)
                        val output = json.getJSONArray("output")
                        val mate = ArrayList<Materia>()

                        for (i in 0..output.length() - 1) {
                            val json2 = output.getJSONObject(i)
                            val idsemestre = json2.getString("id_semestre").toInt()

                            if (idsemestre == semestre) {
                                val mat = Materia(
                                    "${json2.getString("nombre_materia")}",
                                    "$horaInicio:00 - $horaFin:00"
                                )
                                horaInicio++
                                horaFin++
                                mate.add(mat)
                            } else {
                                if (idsemestre > semestre) {
                                    break
                                }
                            }

                        }
                        recyclerView.adapter =
                            MyAdapter(this@HorarioAlumno, R.layout.row_horario, mate)
                        recyclerView.layoutManager = LinearLayoutManager(this@HorarioAlumno)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(
                            this@HorarioAlumno,
                            "Error, intente mas tarde",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

            }.consumeGet(this, url)
        }
        catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(
                this@HorarioAlumno,
                "Error, intente mas tarde",
                Toast.LENGTH_LONG
            ).show()
        }
    }

}