<?php

require 'ManejadorBD.php';

$materia = $_GET['materia'];
$m = new ManejadorBD();
$result = $m->findNombreMateria($materia);
echo json_encode($result);

?>