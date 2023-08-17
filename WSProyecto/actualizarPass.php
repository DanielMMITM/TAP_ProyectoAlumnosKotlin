<?php

require 'ManejadorBD.php';

$id_alumno = $_GET['id_alumno'];

$nueva_pass = $_GET['password'];

$m = new ManejadorBD();

$result = $m->cambiarPass($id_alumno, $nueva_pass);
echo json_encode($result);

?>