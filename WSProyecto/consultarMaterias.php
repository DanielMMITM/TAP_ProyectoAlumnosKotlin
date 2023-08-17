<?php

require 'ManejadorBD.php';

$m = new ManejadorBD();
$result = $m->materias();
echo json_encode($result);

?>