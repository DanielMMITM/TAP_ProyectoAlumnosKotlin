<?php

require 'ManejadorBD.php';

$usuario = $_GET['usuario'];
$m = new ManejadorBD();
$result = $m->findUsuarioMaestro($usuario);
echo json_encode($result);

?>