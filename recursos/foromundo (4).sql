-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-05-2025 a las 11:15:15
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `foromundo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comun`
--

CREATE TABLE `comun` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(150) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `tipo_usuario` varchar(100) NOT NULL,
  `nivel_participacion` varchar(100) DEFAULT NULL,
  `num_comentarios` int(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `creador`
--

CREATE TABLE `creador` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellidos` varchar(150) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `num_foros_creados` int(11) DEFAULT NULL,
  `fecha_registro` date NOT NULL,
  `tipo_usuario` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `creador`
--

INSERT INTO `creador` (`id_usuario`, `nombre`, `apellidos`, `email`, `password`, `num_foros_creados`, `fecha_registro`, `tipo_usuario`) VALUES
(19, 'Isaac', 'Fernández', 'isaac@gmail.com', 'Hello123!', 1, '2025-05-19', 'CREADOR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `foro`
--

CREATE TABLE `foro` (
  `id_foro` int(11) NOT NULL,
  `titulo` varchar(200) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `fecha_creacion` date DEFAULT NULL,
  `id_creador` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `foro`
--

INSERT INTO `foro` (`id_foro`, `titulo`, `descripcion`, `fecha_creacion`, `id_creador`) VALUES
(34, '¿Se puede añadir una relacion N:M?', 'En MySQL', '2025-05-19', 19);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `texto`
--

CREATE TABLE `texto` (
  `id_contenido` int(11) NOT NULL,
  `texto` text DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `id_foro` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `texto`
--

INSERT INTO `texto` (`id_contenido`, `texto`, `fecha`, `id_usuario`, `id_foro`) VALUES
(195, 'En dos tablas iguales', '2025-05-19', NULL, 34);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comun`
--
ALTER TABLE `comun`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `creador`
--
ALTER TABLE `creador`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `foro`
--
ALTER TABLE `foro`
  ADD PRIMARY KEY (`id_foro`),
  ADD KEY `foro_ibfk_1` (`id_creador`);

--
-- Indices de la tabla `texto`
--
ALTER TABLE `texto`
  ADD PRIMARY KEY (`id_contenido`),
  ADD KEY `texto_ibfk_2` (`id_foro`),
  ADD KEY `fk_texto_usuario` (`id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comun`
--
ALTER TABLE `comun`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `creador`
--
ALTER TABLE `creador`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `foro`
--
ALTER TABLE `foro`
  MODIFY `id_foro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT de la tabla `texto`
--
ALTER TABLE `texto`
  MODIFY `id_contenido` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=196;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `foro`
--
ALTER TABLE `foro`
  ADD CONSTRAINT `foro_ibfk_1` FOREIGN KEY (`id_creador`) REFERENCES `creador` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `texto`
--
ALTER TABLE `texto`
  ADD CONSTRAINT `fk_texto_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `comun` (`id_usuario`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `texto_ibfk_2` FOREIGN KEY (`id_foro`) REFERENCES `foro` (`id_foro`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
