<?php

require 'ManejadorBD.php';

$materia = $_GET['materia'];
$m = new ManejadorBD();
$result = $m->findIdMateria($materia);
echo json_encode($result);

?>