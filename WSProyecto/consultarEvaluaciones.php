<?php

require 'ManejadorBD.php';

$id_maestro = $_GET['id_maestro'];

$m = new ManejadorBD();
$result = $m->verEvaluaciones($id_maestro);
echo json_encode($result);

?>