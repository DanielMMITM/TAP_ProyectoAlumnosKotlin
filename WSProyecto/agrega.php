<?php

require 'ManejadorBD.php';

$nombre = $_POST['nombre'];

$noControl = $_POST['noControl'];

$carrera = $_POST['carrera'];

$id_semestre = $_POST['semestre'];

$password = $_POST['password'];

$evaluacionDoc = $_POST['evaluacionDoc'];

$m = new ManejadorBD();

$result = $m->registrarAlumno($nombre, $noControl, $carrera, $id_semestre, $password, $evaluacionDoc);
echo json_encode($result);

?>