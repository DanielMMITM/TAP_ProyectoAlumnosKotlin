package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.CalificacionM
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyAdapterCalif
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject
import java.lang.Exception

class CalificacionesAlumno : AppCompatActivity() {

    var semestre = 0
    private lateinit var usuario : String
    private lateinit var recyclerView : RecyclerView
    private lateinit var editSemestre : EditText
    private lateinit var btnConsultar : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificaciones_alumno)

        recyclerView = findViewById(R.id.recyclerCalifs)
        editSemestre = findViewById(R.id.editSemestre)
        btnConsultar = findViewById(R.id.btnConsultar)

        usuario = intent.getStringExtra("usuario").toString()

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

        btnConsultar.setOnClickListener { consultarCalifSem() }

    }
    private fun consultarCalifSem(){
        if(editSemestre.text.isEmpty()){
            editSemestre.error = "No puede estar vacio"
            return
        }
        else{
            if(editSemestre.text.toString().toInt() > 9 || editSemestre.text.toString().toInt() < 1){
                Toast.makeText(this, "Ese semestre no existe", Toast.LENGTH_LONG).show()
                return
            }
            else{
                if(editSemestre.text.toString().toInt() > semestre){
                    Toast.makeText(this, "Parece que no has cursado ese semestre aun", Toast.LENGTH_LONG).show()
                    return
                }
                else{
                    if(editSemestre.text.toString().toInt() == 1){
                        val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${1}&ultima=${8}&id_alumno=${usuario}"

                        object : MyUtils(){
                            override fun formatResponse(response: String) {
                                try {
                                    println("response " + response)
                                    val json = JSONObject(response)
                                    val output = json.getJSONArray("output")
                                    val mate = ArrayList<CalificacionM>()
                                    var json2 = output.getJSONObject(0)
                                    var calif = json2.getString("calif").toString()
                                    var Ocalif = CalificacionM("Calculo Diferencial", "${calif}")
                                    mate.add(Ocalif)

                                    json2 = output.getJSONObject(1)
                                    calif = json2.getString("calif").toString()
                                    Ocalif = CalificacionM("Fundamentos de programacion", "${calif}")
                                    mate.add(Ocalif)

                                    json2 = output.getJSONObject(2)
                                    calif = json2.getString("calif").toString()
                                    Ocalif = CalificacionM("Taller de etica", "${calif}")
                                    mate.add(Ocalif)

                                    json2 = output.getJSONObject(3)
                                    calif = json2.getString("calif").toString()
                                    Ocalif = CalificacionM("Matematicas discretas", "${calif}")
                                    mate.add(Ocalif)

                                    json2 = output.getJSONObject(4)
                                    calif = json2.getString("calif").toString()
                                    Ocalif = CalificacionM("Taller de administracion", "${calif}")
                                    mate.add(Ocalif)

                                    json2 = output.getJSONObject(5)
                                    calif = json2.getString("calif").toString()
                                    Ocalif = CalificacionM("Fundamentos de investigacion", "${calif}")
                                    mate.add(Ocalif)

                                    json2 = output.getJSONObject(6)
                                    calif = json2.getString("calif").toString()
                                    Ocalif = CalificacionM("Tutoria", "${calif}")
                                    mate.add(Ocalif)

                                    json2 = output.getJSONObject(7)
                                    calif = json2.getString("calif").toString()
                                    Ocalif = CalificacionM("Actividades fisicas", "${calif}")
                                    mate.add(Ocalif)

                                    recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                    recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                }
                                catch (e : Exception){
                                    e.printStackTrace()
                                    Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                }
                            }

                        }.consumeGet(this, url)
                    }
                    else{
                        if(editSemestre.text.toString().toInt() == 2){
                            val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${9}&ultima=${15}&id_alumno=${usuario}"

                            object : MyUtils(){
                                override fun formatResponse(response: String) {
                                    try {
                                        val json = JSONObject(response)
                                        val output = json.getJSONArray("output")
                                        val mate = ArrayList<CalificacionM>()
                                        var json2 = output.getJSONObject(0)
                                        var calif = json2.getString("calif").toString()
                                        var Ocalif = CalificacionM("Calculo integral", "${calif}")
                                        mate.add(Ocalif)

                                        json2 = output.getJSONObject(1)
                                        calif = json2.getString("calif").toString()
                                        Ocalif = CalificacionM("Programacion orientada a objetos", "${calif}")
                                        mate.add(Ocalif)

                                        json2 = output.getJSONObject(2)
                                        calif = json2.getString("calif").toString()
                                        Ocalif = CalificacionM("Contabilidad financiera", "${calif}")
                                        mate.add(Ocalif)

                                        json2 = output.getJSONObject(3)
                                        calif = json2.getString("calif").toString()
                                        Ocalif = CalificacionM("Quimica", "${calif}")
                                        mate.add(Ocalif)

                                        json2 = output.getJSONObject(4)
                                        calif = json2.getString("calif").toString()
                                        Ocalif = CalificacionM("Algebra lineal", "${calif}")
                                        mate.add(Ocalif)

                                        json2 = output.getJSONObject(5)
                                        calif = json2.getString("calif").toString()
                                        Ocalif = CalificacionM("Probabilidad y estadistica", "${calif}")
                                        mate.add(Ocalif)

                                        json2 = output.getJSONObject(6)
                                        calif = json2.getString("calif").toString()
                                        Ocalif = CalificacionM("Musica y artes", "${calif}")
                                        mate.add(Ocalif)

                                        recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                        recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                    }
                                    catch (e : Exception){
                                        e.printStackTrace()
                                        Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                    }
                                }

                            }.consumeGet(this, url)
                        }
                        else{
                            if(editSemestre.text.toString().toInt() == 3){
                                val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${16}&ultima=${21}&id_alumno=${usuario}"

                                object : MyUtils(){
                                    override fun formatResponse(response: String) {
                                        try {
                                            val json = JSONObject(response)
                                            val output = json.getJSONArray("output")
                                            val mate = ArrayList<CalificacionM>()
                                            var json2 = output.getJSONObject(0)
                                            var calif = json2.getString("calif").toString()
                                            var Ocalif = CalificacionM("Calculo vectorial", "${calif}")
                                            mate.add(Ocalif)

                                            json2 = output.getJSONObject(1)
                                            calif = json2.getString("calif").toString()
                                            Ocalif = CalificacionM("Estructura de datos", "${calif}")
                                            mate.add(Ocalif)

                                            json2 = output.getJSONObject(2)
                                            calif = json2.getString("calif").toString()
                                            Ocalif = CalificacionM("Cultura empresarial", "${calif}")
                                            mate.add(Ocalif)

                                            json2 = output.getJSONObject(3)
                                            calif = json2.getString("calif").toString()
                                            Ocalif = CalificacionM("Investigacion de operaciones", "${calif}")
                                            mate.add(Ocalif)

                                            json2 = output.getJSONObject(4)
                                            calif = json2.getString("calif").toString()
                                            Ocalif = CalificacionM("Desarrollo sustentable", "${calif}")
                                            mate.add(Ocalif)

                                            json2 = output.getJSONObject(5)
                                            calif = json2.getString("calif").toString()
                                            Ocalif = CalificacionM("Fisica general", "${calif}")
                                            mate.add(Ocalif)

                                            recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                            recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                        }
                                        catch (e : Exception){
                                            e.printStackTrace()
                                            Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                        }
                                    }

                                }.consumeGet(this, url)
                            }
                            else{
                                if(editSemestre.text.toString().toInt() == 4){
                                    val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${22}&ultima=${27}&id_alumno=${usuario}"

                                    object : MyUtils(){
                                        override fun formatResponse(response: String) {
                                            try {
                                                val json = JSONObject(response)
                                                val output = json.getJSONArray("output")
                                                val mate = ArrayList<CalificacionM>()
                                                var json2 = output.getJSONObject(0)
                                                var calif = json2.getString("calif").toString()
                                                var Ocalif = CalificacionM("Ecuaciones diferenciales", "${calif}")
                                                mate.add(Ocalif)

                                                json2 = output.getJSONObject(1)
                                                calif = json2.getString("calif").toString()
                                                Ocalif = CalificacionM("Metodos numericos", "${calif}")
                                                mate.add(Ocalif)

                                                json2 = output.getJSONObject(2)
                                                calif = json2.getString("calif").toString()
                                                Ocalif = CalificacionM("Topicos avanzados de programacion", "${calif}")
                                                mate.add(Ocalif)

                                                json2 = output.getJSONObject(3)
                                                calif = json2.getString("calif").toString()
                                                Ocalif = CalificacionM("Fundamentos de base de datos", "${calif}")
                                                mate.add(Ocalif)

                                                json2 = output.getJSONObject(4)
                                                calif = json2.getString("calif").toString()
                                                Ocalif = CalificacionM("Simulacion", "${calif}")
                                                mate.add(Ocalif)

                                                json2 = output.getJSONObject(5)
                                                calif = json2.getString("calif").toString()
                                                Ocalif = CalificacionM("Principios electricos", "${calif}")
                                                mate.add(Ocalif)

                                                recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                                recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                            }
                                            catch (e : Exception){
                                                e.printStackTrace()
                                                Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                            }
                                        }

                                    }.consumeGet(this, url)
                                }
                                else{
                                    if(editSemestre.text.toString().toInt() == 5){
                                        val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${28}&ultima=${33}&id_alumno=${usuario}"

                                        object : MyUtils(){
                                            override fun formatResponse(response: String) {
                                                try {
                                                    val json = JSONObject(response)
                                                    val output = json.getJSONArray("output")
                                                    val mate = ArrayList<CalificacionM>()
                                                    var json2 = output.getJSONObject(0)
                                                    var calif = json2.getString("calif").toString()
                                                    var Ocalif = CalificacionM("Graficacion", "${calif}")
                                                    mate.add(Ocalif)

                                                    json2 = output.getJSONObject(1)
                                                    calif = json2.getString("calif").toString()
                                                    Ocalif = CalificacionM("Fundamentos de telecomunicaciones", "${calif}")
                                                    mate.add(Ocalif)

                                                    json2 = output.getJSONObject(2)
                                                    calif = json2.getString("calif").toString()
                                                    Ocalif = CalificacionM("Sistemas operativos", "${calif}")
                                                    mate.add(Ocalif)

                                                    json2 = output.getJSONObject(3)
                                                    calif = json2.getString("calif").toString()
                                                    Ocalif = CalificacionM("Taller de bases de datos", "${calif}")
                                                    mate.add(Ocalif)

                                                    json2 = output.getJSONObject(4)
                                                    calif = json2.getString("calif").toString()
                                                    Ocalif = CalificacionM("Fundamentos de ingenieria de software", "${calif}")
                                                    mate.add(Ocalif)

                                                    json2 = output.getJSONObject(5)
                                                    calif = json2.getString("calif").toString()
                                                    Ocalif = CalificacionM("Sistemas programables", "${calif}")
                                                    mate.add(Ocalif)

                                                    recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                                    recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                                }
                                                catch (e : Exception){
                                                    e.printStackTrace()
                                                    Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                                }
                                            }

                                        }.consumeGet(this, url)

                                    }
                                    else{
                                        if(editSemestre.text.toString().toInt() == 6){
                                            val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${34}&ultima=${39}&id_alumno=${usuario}"

                                            object : MyUtils(){
                                                override fun formatResponse(response: String) {
                                                    try {
                                                        val json = JSONObject(response)
                                                        val output = json.getJSONArray("output")
                                                        val mate = ArrayList<CalificacionM>()
                                                        var json2 = output.getJSONObject(0)
                                                        var calif = json2.getString("calif").toString()
                                                        var Ocalif = CalificacionM("Lenguajes y automatas I", "${calif}")
                                                        mate.add(Ocalif)

                                                        json2 = output.getJSONObject(1)
                                                        calif = json2.getString("calif").toString()
                                                        Ocalif = CalificacionM("Redes de computadoras", "${calif}")
                                                        mate.add(Ocalif)

                                                        json2 = output.getJSONObject(2)
                                                        calif = json2.getString("calif").toString()
                                                        Ocalif = CalificacionM("Taller de sistemas operativos", "${calif}")
                                                        mate.add(Ocalif)

                                                        json2 = output.getJSONObject(3)
                                                        calif = json2.getString("calif").toString()
                                                        Ocalif = CalificacionM("Administracion de bases de datos", "${calif}")
                                                        mate.add(Ocalif)

                                                        json2 = output.getJSONObject(4)
                                                        calif = json2.getString("calif").toString()
                                                        Ocalif = CalificacionM("Ingenieria de software", "${calif}")
                                                        mate.add(Ocalif)

                                                        json2 = output.getJSONObject(5)
                                                        calif = json2.getString("calif").toString()
                                                        Ocalif = CalificacionM("Programacion web", "${calif}")
                                                        mate.add(Ocalif)

                                                        recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                                        recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                                    }
                                                    catch (e : Exception){
                                                        e.printStackTrace()
                                                        Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                                    }
                                                }

                                            }.consumeGet(this, url)
                                        }
                                        else{
                                            if(editSemestre.text.toString().toInt() == 7){
                                                val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${40}&ultima=${45}&id_alumno=${usuario}"

                                                object : MyUtils(){
                                                    override fun formatResponse(response: String) {
                                                        try {
                                                            val json = JSONObject(response)
                                                            val output = json.getJSONArray("output")
                                                            val mate = ArrayList<CalificacionM>()
                                                            var json2 = output.getJSONObject(0)
                                                            var calif = json2.getString("calif").toString()
                                                            var Ocalif = CalificacionM("Lenguajes y automatas II", "${calif}")
                                                            mate.add(Ocalif)

                                                            json2 = output.getJSONObject(1)
                                                            calif = json2.getString("calif").toString()
                                                            Ocalif = CalificacionM("Conmutacion y Enrutamiento en Redes de Datos", "${calif}")
                                                            mate.add(Ocalif)

                                                            json2 = output.getJSONObject(2)
                                                            calif = json2.getString("calif").toString()
                                                            Ocalif = CalificacionM("Taller de Investigacion I", "${calif}")
                                                            mate.add(Ocalif)

                                                            json2 = output.getJSONObject(3)
                                                            calif = json2.getString("calif").toString()
                                                            Ocalif = CalificacionM("Gestion de Proyectos de Software", "${calif}")
                                                            mate.add(Ocalif)

                                                            json2 = output.getJSONObject(4)
                                                            calif = json2.getString("calif").toString()
                                                            Ocalif = CalificacionM("Arquitectura de Computadoras", "${calif}")
                                                            mate.add(Ocalif)

                                                            json2 = output.getJSONObject(5)
                                                            calif = json2.getString("calif").toString()
                                                            Ocalif = CalificacionM("Actividades Complementarias", "${calif}")
                                                            mate.add(Ocalif)

                                                            recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                                            recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                                        }
                                                        catch (e : Exception){
                                                            e.printStackTrace()
                                                            Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                                        }
                                                    }

                                                }.consumeGet(this, url)
                                            }
                                            else{
                                                if(editSemestre.text.toString().toInt() == 8){
                                                    val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${46}&ultima=${51}&id_alumno=${usuario}"

                                                    object : MyUtils(){
                                                        override fun formatResponse(response: String) {
                                                            try {
                                                                val json = JSONObject(response)
                                                                val output = json.getJSONArray("output")
                                                                val mate = ArrayList<CalificacionM>()
                                                                var json2 = output.getJSONObject(0)
                                                                var calif = json2.getString("calif").toString()
                                                                var Ocalif = CalificacionM("Programacion Logica y Funcional", "${calif}")
                                                                mate.add(Ocalif)

                                                                json2 = output.getJSONObject(1)
                                                                calif = json2.getString("calif").toString()
                                                                Ocalif = CalificacionM("Administracion de Redes", "${calif}")
                                                                mate.add(Ocalif)

                                                                json2 = output.getJSONObject(2)
                                                                calif = json2.getString("calif").toString()
                                                                Ocalif = CalificacionM("Taller de Investigacion II", "${calif}")
                                                                mate.add(Ocalif)

                                                                json2 = output.getJSONObject(3)
                                                                calif = json2.getString("calif").toString()
                                                                Ocalif = CalificacionM("Lenguajes de Interfaz", "${calif}")
                                                                mate.add(Ocalif)

                                                                json2 = output.getJSONObject(4)
                                                                calif = json2.getString("calif").toString()
                                                                Ocalif = CalificacionM("Servicio Social", "${calif}")
                                                                mate.add(Ocalif)

                                                                json2 = output.getJSONObject(5)
                                                                calif = json2.getString("calif").toString()
                                                                Ocalif = CalificacionM("Lab Lenguajes de Interfaz", "${calif}")
                                                                mate.add(Ocalif)

                                                                recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                                                recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                                            }
                                                            catch (e : Exception){
                                                                e.printStackTrace()
                                                                Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                                            }
                                                        }

                                                    }.consumeGet(this, url)
                                                }
                                                else{
                                                    if(editSemestre.text.toString().toInt() == 9){
                                                        val url = "${resources.getString(R.string.ws)}consultarCalificaciones.php?primera=${52}&ultima=${53}&id_alumno=${usuario}"

                                                        object : MyUtils(){
                                                            override fun formatResponse(response: String) {
                                                                try {
                                                                    val json = JSONObject(response)
                                                                    val output = json.getJSONArray("output")
                                                                    val mate = ArrayList<CalificacionM>()
                                                                    var json2 = output.getJSONObject(0)
                                                                    var calif = json2.getString("calif").toString()
                                                                    var Ocalif = CalificacionM("Inteligencia Artificial", "${calif}")
                                                                    mate.add(Ocalif)

                                                                    json2 = output.getJSONObject(1)
                                                                    calif = json2.getString("calif").toString()
                                                                    Ocalif = CalificacionM("Residencia Profesional", "${calif}")
                                                                    mate.add(Ocalif)

                                                                    recyclerView.adapter = MyAdapterCalif(this@CalificacionesAlumno, R.layout.row_calificaciones, mate)
                                                                    recyclerView.layoutManager = LinearLayoutManager(this@CalificacionesAlumno)
                                                                }
                                                                catch (e : Exception){
                                                                    e.printStackTrace()
                                                                    Toast.makeText(this@CalificacionesAlumno, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
                                                                }
                                                            }

                                                        }.consumeGet(this, url)
                                                    }
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}