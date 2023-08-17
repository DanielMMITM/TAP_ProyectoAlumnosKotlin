package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject
import java.lang.Exception

class MenuProfesorPrincipal : AppCompatActivity() {

    private lateinit var alumnos : String
    private lateinit var textUsuario: TextView
    private lateinit var textMateria: TextView
    private lateinit var textTotalAlumnos: TextView
    private lateinit var textAlumnos : TextView
    private lateinit var textRubro1: TextView
    private lateinit var textRubro2: TextView
    private lateinit var textRubro3: TextView
    private lateinit var textRubro4: TextView
    private lateinit var textRubro5: TextView
    private lateinit var textRubro6: TextView
    private lateinit var textRubro7: TextView
    private lateinit var textRubro8: TextView
    private lateinit var textRubro9: TextView
    private lateinit var textPromedio: TextView
    private lateinit var textComentarios: TextView

    var promedioRubro1 = 0.0
    var promedioRubro2 = 0.0
    var promedioRubro3 = 0.0
    var promedioRubro4 = 0.0
    var promedioRubro5 = 0.0
    var promedioRubro6 = 0.0
    var promedioRubro7 = 0.0
    var promedioRubro8 = 0.0
    var promedioRubro9 = 0.0
    var promedioTotal = 0.0

    var contA = 0

    var comentarios = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_profesor_principal)

        textUsuario = findViewById(R.id.textUsuario)
        textMateria = findViewById(R.id.textMateria)
        textTotalAlumnos = findViewById(R.id.textTotalAlumnos)
        textAlumnos = findViewById(R.id.textAlumnos)
        textRubro1 = findViewById(R.id.textRubro1)
        textRubro2 = findViewById(R.id.textRubro2)
        textRubro3 = findViewById(R.id.textRubro3)
        textRubro4 = findViewById(R.id.textRubro4)
        textRubro5 = findViewById(R.id.textRubro5)
        textRubro6 = findViewById(R.id.textRubro6)
        textRubro7 = findViewById(R.id.textRubro7)
        textRubro8 = findViewById(R.id.textRubro8)
        textRubro9 = findViewById(R.id.textRubro9)
        textPromedio = findViewById(R.id.textPromedio)
        textComentarios = findViewById(R.id.textComentarios)

        alumnos = ""

        val usuario = intent.getStringExtra("usuario")

        val url = "${resources.getString(R.string.ws)}findUsuarioMaestro.php?usuario=$usuario"
        textUsuario.setText("Bienvenido $usuario")

        object : MyUtils() {
            override fun formatResponse(response: String) {
                val jo = JSONObject(response)
                val output = jo.getJSONArray("output")
                val jsonP = output.getJSONObject(0)
                val id_profe = jsonP.getString("id_maestro")
                val idMateria = jsonP.getString("id_materia")

                val url2 = "${resources.getString(R.string.ws)}consultarEvaluaciones.php?id_maestro=$id_profe"

                object : MyUtils() {
                    override fun formatResponse(response: String) {
                        val jo2 = JSONObject(response)
                        val output2 = jo2.getJSONArray("output")

                        for (i in 0..output2.length()-1){
                            val json = output2.getJSONObject(i)
                            if(json.getString("id_rubro").toInt() == 1){
                                promedioRubro1 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 2){
                                promedioRubro2 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 3){
                                promedioRubro3 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 4){
                                promedioRubro4 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 5){
                                promedioRubro5 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 6){
                                promedioRubro6 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 7){
                                promedioRubro7 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 8){
                                promedioRubro8 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 9){
                                promedioRubro9 += json.getString("calific").toFloat()
                            }
                            if(json.getString("id_rubro").toInt() == 10){
                                comentarios += json.getString("coment") + ", "
                                contA++
                                val id_alumno = json.getString("id_alumno")
                                try{
                                    val url3 = "${resources.getString(R.string.ws)}consultarNoControl.php?id_alumno=${id_alumno}"
                                        object : MyUtils(){
                                            override fun formatResponse(response: String) {
                                                val jo3 = JSONObject(response)
                                                val output3 = jo3.getJSONArray("output")
                                                val json3 = output3.getJSONObject(0)
                                                alumnos += json3.getString("noControl").toString() + ", "
                                                textAlumnos.text = "Alumnos que evaluaron: " + alumnos
                                                println(alumnos)
                                            }

                                        }.consumeGet(this@MenuProfesorPrincipal, url3)
                                }
                                catch (e : Exception){
                                    e.printStackTrace()
                                }
                            }
                        }

                        promedioRubro1 = promedioRubro1/contA.toFloat()
                        promedioRubro2 = promedioRubro2/contA.toFloat()
                        promedioRubro3 = promedioRubro3/contA.toFloat()
                        promedioRubro4 = promedioRubro4/contA.toFloat()
                        promedioRubro5 = promedioRubro5/contA.toFloat()
                        promedioRubro6 = promedioRubro6/contA.toFloat()
                        promedioRubro7 = promedioRubro7/contA.toFloat()
                        promedioRubro8 = promedioRubro8/contA.toFloat()
                        promedioRubro9 = promedioRubro9/contA.toFloat()

                        promedioTotal = (promedioRubro1 + promedioRubro2 + promedioRubro3 + promedioRubro4 + promedioRubro5 + promedioRubro6 + promedioRubro7 + promedioRubro8 + promedioRubro9)/9

                        textTotalAlumnos.text = "Total de alumnos: $contA"
                        //textAlumnos.text = "Alumnos que evaluaron: $alumnos"
                        textRubro1.text = "Promedio rubro 1: $promedioRubro1"
                        textRubro2.text = "Promedio rubro 2: $promedioRubro2"
                        textRubro3.text = "Promedio rubro 3: $promedioRubro3"
                        textRubro4.text = "Promedio rubro 4: $promedioRubro4"
                        textRubro5.text = "Promedio rubro 5: $promedioRubro5"
                        textRubro6.text = "Promedio rubro 6: $promedioRubro6"
                        textRubro7.text = "Promedio rubro 7: $promedioRubro7"
                        textRubro8.text = "Promedio rubro 8: $promedioRubro8"
                        textRubro9.text = "Promedio rubro 9: $promedioRubro9"
                        textPromedio.text = "Promedio total: $promedioTotal"
                        textComentarios.text = "Comentarios: $comentarios"

                        val url4 = "${resources.getString(R.string.ws)}findNombreMateria.php?materia=$idMateria"

                        object : MyUtils() {
                            override fun formatResponse(response: String) {
                                val jo4 = JSONObject(response)
                                val output4 = jo4.getJSONArray("output")
                                val json3 = output4.getJSONObject(0)

                                val materia = json3.getString("nombre_materia")

                                textMateria.text = "Materia: $materia"
                            }
                        }.consumeGet(this@MenuProfesorPrincipal, url4)
                    }
                }.consumeGet(this@MenuProfesorPrincipal, url2)
            }
        }.consumeGet(this, url)


    }
}