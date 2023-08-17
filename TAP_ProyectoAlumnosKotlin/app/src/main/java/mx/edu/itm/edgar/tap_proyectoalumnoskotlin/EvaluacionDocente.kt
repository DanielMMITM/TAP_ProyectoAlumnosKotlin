package mx.edu.itm.edgar.tap_proyectoalumnoskotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MateriaRet
import mx.edu.itm.edgar.tap_proyectoalumnoskotlin.utils.MyUtils
import org.json.JSONObject
import org.w3c.dom.Text
import java.lang.Exception

class EvaluacionDocente : AppCompatActivity() {

    var idmaestro_materia = 0
    var semestre = 0
    var contadorRubro = 0;
    var contMaestro = 0

    lateinit var nombre : String
    lateinit var idmaestro : String
    lateinit var idmaestro2 : String
    lateinit var idalumno : String
    lateinit var usuario : String

    lateinit var Rubro1 : TextView
    lateinit var Rubro2 : TextView
    lateinit var Rubro3 : TextView
    lateinit var Rubro4 : TextView
    lateinit var Rubro5 : TextView
    lateinit var Rubro6 : TextView
    lateinit var Rubro7 : TextView
    lateinit var Rubro8 : TextView
    lateinit var Rubro9 : TextView
    lateinit var comentarios : TextView
    lateinit var RubroDesc1 : TextView
    lateinit var RubroDesc2 : TextView
    lateinit var RubroDesc3 : TextView
    lateinit var RubroDesc4 : TextView
    lateinit var RubroDesc5 : TextView
    lateinit var RubroDesc6 : TextView
    lateinit var RubroDesc7 : TextView
    lateinit var RubroDesc8 : TextView
    lateinit var RubroDesc9 : TextView
    lateinit var descComentarios : TextView
    lateinit var Profesor1 : TextView
    lateinit var Profesor2 : TextView
    lateinit var SpinnerProf1_Rubro1 : Spinner
    lateinit var SpinnerProf2_Rubro1 : Spinner
    lateinit var SpinnerProf1_Rubro2 : Spinner
    lateinit var SpinnerProf2_Rubro2 : Spinner
    lateinit var SpinnerProf1_Rubro3 : Spinner
    lateinit var SpinnerProf2_Rubro3 : Spinner
    lateinit var SpinnerProf1_Rubro4 : Spinner
    lateinit var SpinnerProf2_Rubro4 : Spinner
    lateinit var SpinnerProf1_Rubro5 : Spinner
    lateinit var SpinnerProf2_Rubro5 : Spinner
    lateinit var SpinnerProf1_Rubro6 : Spinner
    lateinit var SpinnerProf2_Rubro6 : Spinner
    lateinit var SpinnerProf1_Rubro7 : Spinner
    lateinit var SpinnerProf2_Rubro7 : Spinner
    lateinit var SpinnerProf1_Rubro8 : Spinner
    lateinit var SpinnerProf2_Rubro8 : Spinner
    lateinit var SpinnerProf1_Rubro9 : Spinner
    lateinit var SpinnerProf2_Rubro9 : Spinner
    lateinit var comentarios1 : EditText
    lateinit var comentarios2 : EditText
    lateinit var btnSiguenteEvDoc : Button
    lateinit var btnAnteriorEvDoc : Button
    lateinit var btnGuardarEvDoc : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluacion_docente)

        Rubro1 = findViewById(R.id.Rubro1)
        Rubro2 = findViewById(R.id.Rubro2)
        Rubro3 = findViewById(R.id.Rubro3)
        Rubro4 = findViewById(R.id.Rubro4)
        Rubro5 = findViewById(R.id.Rubro5)
        Rubro6 = findViewById(R.id.Rubro6)
        Rubro7 = findViewById(R.id.Rubro7)
        Rubro8 = findViewById(R.id.Rubro8)
        Rubro9 = findViewById(R.id.Rubro9)
        comentarios = findViewById(R.id.comentarios)

        RubroDesc1 = findViewById(R.id.RubroDesc1)
        RubroDesc2 = findViewById(R.id.RubroDesc2)
        RubroDesc3 = findViewById(R.id.RubroDesc3)
        RubroDesc4 = findViewById(R.id.RubroDesc4)
        RubroDesc5 = findViewById(R.id.RubroDesc5)
        RubroDesc6 = findViewById(R.id.RubroDesc6)
        RubroDesc7 = findViewById(R.id.RubroDesc7)
        RubroDesc8 = findViewById(R.id.RubroDesc8)
        RubroDesc9 = findViewById(R.id.RubroDesc9)
        descComentarios = findViewById(R.id.descComentarios)

        Profesor1 = findViewById(R.id.Profesor1)
        Profesor2 = findViewById(R.id.Profesor2)

        SpinnerProf1_Rubro1 = findViewById(R.id.SpinnerProf1_Rubro1)
        SpinnerProf2_Rubro1 = findViewById(R.id.SpinnerProf2_Rubro1)
        SpinnerProf1_Rubro2 = findViewById(R.id.SpinnerProf1_Rubro2)
        SpinnerProf2_Rubro2 = findViewById(R.id.SpinnerProf2_Rubro2)
        SpinnerProf1_Rubro3 = findViewById(R.id.SpinnerProf1_Rubro3)
        SpinnerProf2_Rubro3 = findViewById(R.id.SpinnerProf2_Rubro3)
        SpinnerProf1_Rubro4 = findViewById(R.id.SpinnerProf1_Rubro4)
        SpinnerProf2_Rubro4 = findViewById(R.id.SpinnerProf2_Rubro4)
        SpinnerProf1_Rubro5 = findViewById(R.id.SpinnerProf1_Rubro5)
        SpinnerProf2_Rubro5 = findViewById(R.id.SpinnerProf2_Rubro5)
        SpinnerProf1_Rubro6 = findViewById(R.id.SpinnerProf1_Rubro6)
        SpinnerProf2_Rubro6 = findViewById(R.id.SpinnerProf2_Rubro6)
        SpinnerProf1_Rubro7 = findViewById(R.id.SpinnerProf1_Rubro7)
        SpinnerProf2_Rubro7 = findViewById(R.id.SpinnerProf2_Rubro7)
        SpinnerProf1_Rubro8 = findViewById(R.id.SpinnerProf1_Rubro8)
        SpinnerProf2_Rubro8 = findViewById(R.id.SpinnerProf2_Rubro8)
        SpinnerProf1_Rubro9 = findViewById(R.id.SpinnerProf1_Rubro9)
        SpinnerProf2_Rubro9 = findViewById(R.id.SpinnerProf2_Rubro9)

        comentarios1 = findViewById(R.id.comentarios1)
        comentarios2 = findViewById(R.id.comentarios2)

        btnSiguenteEvDoc = findViewById(R.id.btnSiguienteEvDoc)
        btnAnteriorEvDoc = findViewById(R.id.btnAnteriorEvDoc)
        btnGuardarEvDoc = findViewById(R.id.btnGuardarEvDoc)

        usuario = intent.getStringExtra("usuario").toString()
        idalumno = ""
        idmaestro = ""
        idmaestro2 = ""
        nombre = ""

        limpiarPantalla()

        try {
            val url = "${resources.getString(R.string.ws)}findUsuarioAlumno.php?noControl=$usuario"
            object : MyUtils() {
                override fun formatResponse(response: String) {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")
                    val jsonP = output.getJSONObject(0)
                    semestre = jsonP.getString("id_semestre").toInt()
                    idalumno = jsonP.getString("id_alumno").toString()
                }
            }.consumeGet(this, url)
        } catch (e: Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_LONG).show()
        }

        GlobalScope.launch(Dispatchers.IO){
            networkCall1()
        }

        contadorRubro = 1;
        mostrarRubro()

        btnSiguenteEvDoc.setOnClickListener {
            contadorRubro++
            mostrarRubro()
        }
        btnAnteriorEvDoc.setOnClickListener {
            contadorRubro--
            mostrarRubro()
        }

        btnGuardarEvDoc.setOnClickListener {
            GlobalScope.launch (Dispatchers.IO){
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro1.selectedItem.toString(),"1")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro2.selectedItem.toString(),"2")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro3.selectedItem.toString(),"3")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro4.selectedItem.toString(),"4")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro5.selectedItem.toString(),"5")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro6.selectedItem.toString(),"6")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro7.selectedItem.toString(),"7")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro8.selectedItem.toString(),"8")
                agregarEvaluacion(idalumno, idmaestro, SpinnerProf1_Rubro9.selectedItem.toString(),"9")
                agregarEvaluacionComent(idalumno, idmaestro, comentarios1.text.toString(),"10")

                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro1.selectedItem.toString(),"1")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro2.selectedItem.toString(),"2")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro3.selectedItem.toString(),"3")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro4.selectedItem.toString(),"4")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro5.selectedItem.toString(),"5")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro6.selectedItem.toString(),"6")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro7.selectedItem.toString(),"7")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro8.selectedItem.toString(),"8")
                agregarEvaluacion(idalumno, idmaestro2, SpinnerProf2_Rubro9.selectedItem.toString(),"9")
                agregarEvaluacionComent(idalumno, idmaestro2, comentarios2.text.toString(),"10")
            }
            GlobalScope.launch(Dispatchers.IO){
                networkCallEvalRealizada()
            }
            Toast.makeText(this, "Gracias por realizar tu evaluacion docente", Toast.LENGTH_SHORT).show()
            finish()
        }

    }

    suspend fun networkCall1(){
        delay(200L)
        try{
            val url = "${resources.getString(R.string.ws)}consultarMaestros.php"

            object : MyUtils(){
                override fun formatResponse(response: String) {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")

                    for (i in 0..output.length()-1) {
                        val json2 = output.getJSONObject(i)
                        idmaestro = json2.getString("id_maestro").toString()
                        nombre = json2.getString("nombre").toString()
                        idmaestro_materia = json2.getString("id_materia").toInt()
                        println("$nombre /////////idmateria $idmaestro_materia")

                        println("maestro: ${json2.getString("nombre")}")
                        if(contMaestro < 2){
                            subnetworkCall1(idmaestro_materia, idmaestro, nombre)
                        }
                        else{
                            break
                        }
                    }
                }

            }.consumeGet(this, url)
        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_SHORT).show()
        }
    }

    private fun subnetworkCall1(idmaestro_materia : Int, id_maestro : String, nombreM : String){
        println("id_maestro" + id_maestro + "//// nombre" + nombreM)
        try{
            val url = "${resources.getString(R.string.ws)}consultarMaterias.php"

            object : MyUtils(){
                override fun formatResponse(response: String) {
                    val json = JSONObject(response)
                    val output = json.getJSONArray("output")

                    for (i in 0..output.length()-1) {
                        val jsonM = output.getJSONObject(i)
                        if(idmaestro_materia == jsonM.getString("id_materia").toInt()){

                            println("primer if${idmaestro_materia} //////// ${jsonM.getString("id_materia")}")

                            if(semestre == jsonM.getString("id_semestre").toInt()){

                                println("segundo if${semestre} //////// ${jsonM.getString("id_semestre")}")

                                if(contMaestro == 0){
                                    idmaestro = id_maestro
                                    Profesor1.text = nombreM
                                    println("${Profesor1.text} //////// $nombre")
                                    contMaestro++
                                    return
                                }
                                else{
                                    if(contMaestro == 1){
                                        idmaestro2 = id_maestro
                                        Profesor2.text = nombreM
                                        println("${Profesor2.text} //////// $nombre")
                                        contMaestro++
                                        return
                                    }
                                }
                            }
                        }
                    }
                }

            }.consumeGet(this, url)
        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_SHORT).show()
        }
    }

    suspend fun agregarEvaluacion(id_alumno : String, id_maestro: String, calific : String, id_rubro : String){
        delay(200L)
        println("maestro :$id_maestro")
        val url = "${resources.getString(R.string.ws)}agregarEvalDoc.php"
        val params = HashMap<String, String>()
        params.put("id_alumno", id_alumno)
        params.put("id_maestro", id_maestro)
        params.put("calific", calific)
        params.put("id_rubro", id_rubro)

        try{
            object : MyUtils(){
                override fun formatResponse(response: String) {
                    for(i in params){
                        println("paramsCALIF: ${params}")
                    }
                }

            }.consumePost(this, url, params)

        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_SHORT).show()
        }

    }

    suspend fun agregarEvaluacionComent(id_alumno : String, id_maestro2: String, coment : String, id_rubro : String){
        delay(200L)
        println("maestroComent:$id_maestro2")
        val url = "${resources.getString(R.string.ws)}agregarEvalDoc.php"
        val params = HashMap<String, String>()
        params.put("id_alumno", id_alumno)
        params.put("id_maestro", id_maestro2)
        params.put("coment", coment)
        params.put("id_rubro", id_rubro)

        try{
            object : MyUtils(){
                override fun formatResponse(response: String) {
                    for(i in params){
                        println("params: ${params}")
                    }
                }

            }.consumePost(this, url, params)

        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_SHORT).show()
        }

    }

    suspend fun networkCallEvalRealizada(){
        delay(500L)
        try{
            val url = "${resources.getString(R.string.ws)}actualizarEvaluacionDoc.php?id_alumno=${idalumno}&evaluacionDoc=${1}"

            object : MyUtils() {
                override fun formatResponse(response: String) {
                    Toast.makeText(this@EvaluacionDocente, "Guardado con éxito", Toast.LENGTH_SHORT).show()
                }
            }.consumeGet(this, url)
        }
        catch (e : Exception){
            e.printStackTrace()
            Toast.makeText(this, "Error, intente mas tarde", Toast.LENGTH_SHORT).show()
        }
    }


    private fun mostrarRubro(){
        if(contadorRubro == 1){
            limpiarPantalla()
            Rubro1.setVisibility(View.VISIBLE)
            RubroDesc1.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro1.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro1.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 2){
            println(SpinnerProf1_Rubro1.selectedItem.toString())
            println(SpinnerProf2_Rubro1.selectedItem.toString())

            limpiarPantalla()
            Rubro2.setVisibility(View.VISIBLE)
            RubroDesc2.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro2.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro2.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 3){
            limpiarPantalla()
            Rubro3.setVisibility(View.VISIBLE)
            RubroDesc3.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro3.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro3.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 4){
            limpiarPantalla()
            Rubro4.setVisibility(View.VISIBLE)
            RubroDesc4.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro4.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro4.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 5){
            limpiarPantalla()
            Rubro5.setVisibility(View.VISIBLE)
            RubroDesc5.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro5.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro5.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 6){
            limpiarPantalla()
            Rubro6.setVisibility(View.VISIBLE)
            RubroDesc6.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro6.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro6.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 7){
            limpiarPantalla()
            Rubro7.setVisibility(View.VISIBLE)
            RubroDesc7.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro7.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro7.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 8){
            limpiarPantalla()
            Rubro8.setVisibility(View.VISIBLE)
            RubroDesc8.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro8.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro8.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 9){
            limpiarPantalla()
            Rubro9.setVisibility(View.VISIBLE)
            RubroDesc9.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            SpinnerProf1_Rubro9.setVisibility(View.VISIBLE)
            SpinnerProf2_Rubro9.setVisibility(View.VISIBLE)
            btnSiguenteEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
        if(contadorRubro == 10){
            limpiarPantalla()
            comentarios.setVisibility(View.VISIBLE)
            descComentarios.setVisibility(View.VISIBLE)
            Profesor1.setVisibility(View.VISIBLE)
            Profesor2.setVisibility(View.VISIBLE)
            comentarios1.setVisibility(View.VISIBLE)
            comentarios2.setVisibility(View.VISIBLE)
            btnGuardarEvDoc.setVisibility(View.VISIBLE)
            btnAnteriorEvDoc.setVisibility(View.VISIBLE)
        }
    }


    private fun limpiarPantalla(){
        Rubro1.setVisibility(View.INVISIBLE)
        Rubro2.setVisibility(View.INVISIBLE)
        Rubro3.setVisibility(View.INVISIBLE)
        Rubro4.setVisibility(View.INVISIBLE)
        Rubro5.setVisibility(View.INVISIBLE)
        Rubro6.setVisibility(View.INVISIBLE)
        Rubro7.setVisibility(View.INVISIBLE)
        Rubro8.setVisibility(View.INVISIBLE)
        Rubro9.setVisibility(View.INVISIBLE)
        comentarios.setVisibility(View.INVISIBLE)

        RubroDesc1.setVisibility(View.INVISIBLE)
        RubroDesc2.setVisibility(View.INVISIBLE)
        RubroDesc3.setVisibility(View.INVISIBLE)
        RubroDesc4.setVisibility(View.INVISIBLE)
        RubroDesc5.setVisibility(View.INVISIBLE)
        RubroDesc6.setVisibility(View.INVISIBLE)
        RubroDesc7.setVisibility(View.INVISIBLE)
        RubroDesc8.setVisibility(View.INVISIBLE)
        RubroDesc9.setVisibility(View.INVISIBLE)
        descComentarios.setVisibility(View.INVISIBLE)

        Profesor1.setVisibility(View.INVISIBLE)
        Profesor2.setVisibility(View.INVISIBLE)

        SpinnerProf1_Rubro1.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro1.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro2.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro2.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro3.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro3.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro4.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro4.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro5.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro5.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro6.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro6.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro7.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro7.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro8.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro8.setVisibility(View.INVISIBLE)
        SpinnerProf1_Rubro9.setVisibility(View.INVISIBLE)
        SpinnerProf2_Rubro9.setVisibility(View.INVISIBLE)

        comentarios1.setVisibility(View.INVISIBLE)
        comentarios2.setVisibility(View.INVISIBLE)

        btnSiguenteEvDoc.setVisibility(View.INVISIBLE)
        btnAnteriorEvDoc.setVisibility(View.INVISIBLE)
        btnGuardarEvDoc.setVisibility(View.INVISIBLE)
    }

}