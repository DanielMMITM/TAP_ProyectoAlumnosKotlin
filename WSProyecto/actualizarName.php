<?php

require 'ManejadorBD.php';

$id_alumno = $_GET['id_alumno'];

$nombre = $_GET['nombre'];

$m = new ManejadorBD();

$result = $m->cambiarName($id_alumno, $nombre);
echo json_encode($result);

?>