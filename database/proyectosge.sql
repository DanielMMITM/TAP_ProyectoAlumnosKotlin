-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3308
-- Tiempo de generación: 25-06-2021 a las 00:35:47
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectosge`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

DROP TABLE IF EXISTS `alumno`;
CREATE TABLE IF NOT EXISTS `alumno` (
  `id_alumno` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` text NOT NULL,
  `noControl` varchar(8) NOT NULL,
  `carrera` text NOT NULL,
  `id_semestre` varchar(2) NOT NULL,
  `password` text NOT NULL,
  `evaluacionDoc` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_alumno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `calificaciones`
--

DROP TABLE IF EXISTS `calificaciones`;
CREATE TABLE IF NOT EXISTS `calificaciones` (
  `id_calif` int(11) NOT NULL AUTO_INCREMENT,
  `calif` int(3) NOT NULL,
  `id_alumno` int(11) NOT NULL,
  `id_materia` int(2) NOT NULL,
  PRIMARY KEY (`id_calif`),
  KEY `id_materia` (`id_materia`),
  KEY `id_alumno` (`id_alumno`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evaluaciones`
--

DROP TABLE IF EXISTS `evaluaciones`;
CREATE TABLE IF NOT EXISTS `evaluaciones` (
  `id_evaluacion` int(11) NOT NULL AUTO_INCREMENT,
  `id_alumno` int(11) NOT NULL,
  `id_maestro` int(11) NOT NULL,
  `calific` int(1) DEFAULT NULL,
  `coment` text,
  `id_rubro` int(2) NOT NULL,
  PRIMARY KEY (`id_evaluacion`),
  KEY `id_alumno` (`id_alumno`),
  KEY `id_maestro` (`id_maestro`),
  KEY `id_rubro` (`id_rubro`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `maestro`
--

DROP TABLE IF EXISTS `maestro`;
CREATE TABLE IF NOT EXISTS `maestro` (
  `id_maestro` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` text NOT NULL,
  `usuario` text NOT NULL,
  `password` text NOT NULL,
  `id_materia` int(2) NOT NULL,
  PRIMARY KEY (`id_maestro`),
  KEY `id_materia` (`id_materia`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia`
--

DROP TABLE IF EXISTS `materia`;
CREATE TABLE IF NOT EXISTS `materia` (
  `id_materia` int(2) NOT NULL AUTO_INCREMENT,
  `nombre_materia` text NOT NULL,
  `id_semestre` varchar(2) NOT NULL,
  PRIMARY KEY (`id_materia`)
) ENGINE=MyISAM AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `materia`
--

INSERT INTO `materia` (`id_materia`, `nombre_materia`, `id_semestre`) VALUES
(1, 'Calculo Diferencial', '1'),
(2, 'Fundamentos de Programacion', '1'),
(3, 'Taller de Etica', '1'),
(4, 'Matematicas Discretas', '1'),
(5, 'Taller de Administracion', '1'),
(6, 'Fundamentos de Investigacion', '1'),
(7, 'Tutoria', '1'),
(8, 'Actividades Fisicas', '1'),
(9, 'Calculo Integral', '2'),
(10, 'Programacion Orientada a Objetos', '2'),
(11, 'Contabilidad Financiera', '2'),
(12, 'Quimica', '2'),
(13, 'Algebra Lineal', '2'),
(14, 'Probabilidad y Estadistica', '2'),
(15, 'Musica y Artes', '2'),
(16, 'Calculo Vectorial', '3'),
(17, 'Estructura de Datos', '3'),
(18, 'Cultura Empresarial', '3'),
(19, 'Investigacion de Operaciones', '3'),
(20, 'Desarrollo Sustentable', '3'),
(21, 'Fisica General', '3'),
(22, 'Ecuaciones Diferenciales', '4'),
(23, 'Metodos Numericos', '4'),
(24, 'Topicos Avanzados de Programacion', '4'),
(25, 'Fundamentos de Base de Datos', '4'),
(26, 'Simulacion', '4'),
(27, 'Principios Electricos', '4'),
(28, 'Graficacion', '5'),
(29, 'Fundamentos de Telecomunicaciones', '5'),
(30, 'Sistemas Operativos', '5'),
(31, 'Taller de Base de Datos', '5'),
(32, 'Fundamentos de Ingenieria de Software', '5'),
(33, 'Sistemas Programables', '5'),
(34, 'Lenguajes y Automatas I', '6'),
(35, 'Redes de Computadoras', '6'),
(36, 'Taller de Sistemas Operativos', '6'),
(37, 'Admininistracion de Base de Datos', '6'),
(38, 'Ingenieria de Software', '6'),
(39, 'Programacion Web', '6'),
(40, 'Lenguajes y Automatas II', '7'),
(41, 'Conmutacion y Enrutamiento en Redes de Datos', '7'),
(42, 'Taller de Investigacion I', '7'),
(43, 'Gestion de Proyectos de Software', '7'),
(44, 'Arquitectura de Computadoras', '7'),
(45, 'Actividades Complementarias', '7'),
(46, 'Programacion Logica y Funcional', '8'),
(47, 'Administracion de Redes', '8'),
(48, 'Taller de Investigacion II', '8'),
(49, 'Lenguajes de Interfaz', '8'),
(50, 'Servicio Social', '8'),
(51, 'Lab Lenguajes de Interfaz', '8'),
(52, 'Inteligencia Artificial', '9'),
(53, 'Residencia Profesional', '9');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rubros`
--

DROP TABLE IF EXISTS `rubros`;
CREATE TABLE IF NOT EXISTS `rubros` (
  `id_rubro` int(2) NOT NULL AUTO_INCREMENT,
  `nombre_rubro` text NOT NULL,
  PRIMARY KEY (`id_rubro`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `rubros`
--

INSERT INTO `rubros` (`id_rubro`, `nombre_rubro`) VALUES
(1, 'Rubro 1'),
(2, 'Rubro 2'),
(3, 'Rubro 3'),
(4, 'Rubro 4'),
(5, 'Rubro 5'),
(6, 'Rubro 6'),
(7, 'Rubro 7'),
(8, 'Rubro 8'),
(9, 'Rubro 9'),
(10, 'Comentario');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
