<?php

require 'ManejadorBD.php';

$calif = $_POST['calif'];

$id_alumno = $_POST['id_alumno'];

$id_materia = $_POST['id_materia'];

$m = new ManejadorBD();

$result = $m->asignarCalificacion($calif, $id_alumno, $id_materia);
echo json_encode($result);

?>