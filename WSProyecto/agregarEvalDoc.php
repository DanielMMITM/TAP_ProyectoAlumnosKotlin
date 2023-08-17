<?php

require 'ManejadorBD.php';

$id_alumno = $_POST['id_alumno'];

$id_maestro = $_POST['id_maestro'];

$calific = $_POST['calific'];

$coment = $_POST['coment'];

$id_rubro = $_POST['id_rubro'];

$m = new ManejadorBD();

$result = $m->agregarEvaluacion($id_alumno, $id_maestro, $calific, $coment, $id_rubro);
echo json_encode($result);

?>