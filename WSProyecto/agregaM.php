<?php

require 'ManejadorBD.php';

$nombre = $_POST['nombre'];

$usuario = $_POST['usuario'];

$password = $_POST['password'];

$id_materia = $_POST['id_materia'];

$m = new ManejadorBD();

$result = $m->registrarMaestro($nombre, $usuario, $password, $id_materia);
echo json_encode($result);

?>