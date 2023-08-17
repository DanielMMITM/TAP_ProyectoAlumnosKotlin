package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MateriaRet
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyAdapterRet
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject
import java.lang.Exception

class ReticulaAlumno : AppCompatActivity() {

    var semestre = 0
    private lateinit var usuario : String
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reticula_alumno)

        recyclerView = findViewById(R.id.recyclerMaterias)

        usuario = intent.getStringExtra("usuario").toString()

        GlobalScope.launch {
            try{
                networkCall1()
                networkCall2()
            }
            catch (e : Exception){
                e.printStackTrace()
                Toast.makeText(this@ReticulaAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
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
        delay(500L)
        try{
            val url = "${resources.getString(R.string.ws)}consultarMaterias.php"

            object : MyUtils(){
                override fun formatResponse(response: String) {
                    try {
                        val json = JSONObject(response)
                        val output = json.getJSONArray("output")
                        val mate = ArrayList<MateriaRet>()

                        for (i in 0..output.length()-1){
                            val json2 = output.getJSONObject(i)
                            val idsemestre =  json2.getString("id_semestre").toInt()

                            if(idsemestre < semestre){
                                val mat= MateriaRet("${json2.getString("nombre_materia")}",
                                    "Semestre ${json2.getString("id_semestre")}", "Cursada")
                                mate.add(mat)
                            }
                            else{
                                if(idsemestre == semestre){
                                    val mat= MateriaRet("${json2.getString("nombre_materia")}",
                                        "Semestre ${json2.getString("id_semestre")}", "Cursando")
                                    mate.add(mat)
                                }
                                else{
                                    if(idsemestre > semestre){
                                        val mat= MateriaRet("${json2.getString("nombre_materia").toString()}",
                                            "Semestre ${json2.getString("id_semestre")}", "Sin cursar")
                                        mate.add(mat)
                                    }
                                }
                            }

                        }
                        recyclerView.adapter = MyAdapterRet(this@ReticulaAlumno, R.layout.row_reticula, mate)
                        recyclerView.layoutManager = LinearLayoutManager(this@ReticulaAlumno)
                    }
                    catch (e : Exception){
                        e.printStackTrace()
                        Toast.makeText(this@ReticulaAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
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