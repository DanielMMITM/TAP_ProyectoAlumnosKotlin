<?php

require 'ManejadorBD.php';

$id_alumno = $_GET['id_alumno'];
$m = new ManejadorBD();
$result = $m->obtenerNControl($id_alumno);
echo json_encode($result);

?>