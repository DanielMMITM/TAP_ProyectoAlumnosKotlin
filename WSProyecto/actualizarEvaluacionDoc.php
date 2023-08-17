<?php

require 'ManejadorBD.php';

$id_alumno = $_GET['id_alumno'];

$evaluacionDoc = $_GET['evaluacionDoc'];

$m = new ManejadorBD();

$result = $m->evalRealizada($id_alumno, $evaluacionDoc);
echo json_encode($result);

?>