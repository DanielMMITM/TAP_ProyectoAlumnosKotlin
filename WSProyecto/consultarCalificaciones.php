<?php

require 'ManejadorBD.php';

$id_alumno = $_GET['id_alumno'];
$primera = $_GET['primera'];
$ultima = $_GET['ultima'];

$m = new ManejadorBD();

$result = $m->consultarCalificaciones($id_alumno, $primera, $ultima);
echo json_encode($result);

?>