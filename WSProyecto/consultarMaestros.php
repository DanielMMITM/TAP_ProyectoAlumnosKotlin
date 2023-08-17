<?php

require 'ManejadorBD.php';

$m = new ManejadorBD();
$result = $m->maestros();
echo json_encode($result);

?>