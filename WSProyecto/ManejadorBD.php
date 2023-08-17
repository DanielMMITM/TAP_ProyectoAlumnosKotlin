<?php

class ManejadorBD {

	private $db;
	private $host;
	private $user;
	private $pass;
	private $result;

	function __construct() {
		$this->db = 'proyectosge';
		$this->host = 'localhost:3308';
		$this->user = 'root';
		$this->pass = '';

		$this->result = new \stdClass();
		$this->result->code = 200;
		$this->result->msg = 'Success';
		$this->result->output = array();
	}

	private function open() {
		$link = mysqli_connect($this->host, $this->user, $this->pass, $this->db) or die('Error connecting to DB');
		return $link;
	}

	private function close($link) {
		return mysqli_close($link);
	}

	public function registrarAlumno($nombre, $noControl, $carrera, $id_semestre, $password, $evaluacionDoc){
		try{
			$link = $this->open();
			$qry = "INSERT INTO alumno(nombre, noControl, carrera, id_semestre, password, evaluacionDoc) values('".$nombre."', '".$noControl."', '".$carrera."', '".$id_semestre."', '".$password."', 
			'".$evaluacionDoc."')";

			$r = mysqli_query($link, $qry);

			$this->close($link);
		}
		catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
		
	}

	public function cambiarName($id_alumno, $nombre){
		try{
			$link = $this->open();
			$qry = "UPDATE alumno SET nombre='".$nombre."' WHERE id_alumno=".$id_alumno; 

			$r = mysqli_query($link, $qry);

			$this->close($link);
		}
		catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function cambiarPass($id_alumno, $nueva_pass){
		try{
			$link = $this->open();
			$qry = "UPDATE alumno SET password='".$nueva_pass."' WHERE id_alumno=".$id_alumno; 

			$r = mysqli_query($link, $qry);

			$this->close($link);
		}
		catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function registrarMaestro($nombre, $usuario, $password, $id_materia){
		try{
			$link = $this->open();
			$qry = "INSERT INTO maestro(nombre, usuario, password, id_materia) values('".$nombre."', '".$usuario."', '".$password."', '".$id_materia."')";

			$r = mysqli_query($link, $qry);

			$this->close($link);
		}
		catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function maestros() {
		try {
			$link = $this->open();

			$qry = "SELECT * FROM maestro";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function findIdMateria($materia){
		try {
			$link = $this->open();

			$qry = "SELECT id_materia FROM materia WHERE nombre_materia="."'".$materia."'";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function findNombreMateria($materia){
		try {
			$link = $this->open();

			$qry = "SELECT nombre_materia FROM materia WHERE id_materia="."'".$materia."'";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function materias() {
		try {
			$link = $this->open();

			$qry = "SELECT * FROM materia";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function asignarCalificacion($calif, $id_alumno, $id_materia) {
		try {
			$link = $this->open();

			$qry ="INSERT INTO calificaciones(calif, id_alumno, id_materia) values('".$calif."', '".$id_alumno."', '".$id_materia."')";

			$r = mysqli_query($link, $qry);

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function consultarCalificaciones($id_alumno, $primera, $segunda){
		try {
			$link = $this->open();

			$qry = "SELECT id_materia, calif FROM calificaciones WHERE id_alumno="."'".$id_alumno."'"." AND id_materia>="."'".$primera."'"."AND id_materia<="."'".$segunda."'";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function findUsuarioAlumno($usuario){
		try {
			$link = $this->open();

			$qry = "SELECT * FROM alumno WHERE noControl="."'".$usuario."'";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function findUsuarioMaestro($usuario){
		try {
			$link = $this->open();

			$qry = "SELECT * FROM maestro WHERE usuario="."'".$usuario."'";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function agregarEvaluacion($id_alumno, $id_maestro, $calific, $coment, $id_rubro){
		try{
			$link = $this->open();
			if($coment != NULL){
				$qry = "INSERT INTO evaluaciones(id_alumno, id_maestro, coment, id_rubro) values('".$id_alumno."', '".$id_maestro."', '".$coment."',
				 '".$id_rubro."')";
			}
			else{
				$qry = "INSERT INTO evaluaciones(id_alumno, id_maestro, calific, id_rubro) values('".$id_alumno."', '".$id_maestro."', '".$calific."',
				 '".$id_rubro."')";
			}

			$r = mysqli_query($link, $qry);

			$this->close($link);
		}
		catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
		
	}

	public function evalRealizada($id_alumno, $evaluacionDoc){
		try{
			$link = $this->open();
			$qry = "UPDATE alumno SET evaluacionDoc='".$evaluacionDoc."' WHERE id_alumno=".$id_alumno; 

			$r = mysqli_query($link, $qry);

			$this->close($link);
		}
		catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function verEvaluaciones($id_maestro){
		try {
			$link = $this->open();

			$qry = "SELECT * FROM evaluaciones WHERE id_maestro="."'".$id_maestro."'";

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}

	public function obtenerNControl($id_alumno){
		try {
			$link = $this->open();

			$qry = "SELECT noControl FROM alumno WHERE id_alumno=".$id_alumno;

			$r = mysqli_query($link, $qry);

			while( $result[] = mysqli_fetch_array( $r, MYSQLI_ASSOC ) );

			foreach ($result as $value) {
				if($value) {
					array_push($this->result->output, $value);
				}
			}

			$this->close($link);
		} catch (Exception $e) {
			$this->result->code = 500;
			$this->result->msg = 'Failed: '.$e;
		}

		return $this->result;
	}



}

?>