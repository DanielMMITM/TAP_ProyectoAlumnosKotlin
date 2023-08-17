<?php

require 'ManejadorBD.php';

$usuario = $_GET['noControl'];
$m = new ManejadorBD();
$result = $m->findUsuarioAlumno($usuario);
echo json_encode($result);

?>