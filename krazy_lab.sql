-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 17-06-2020 a las 10:21:23
-- Versión del servidor: 10.3.22-MariaDB-0+deb10u1
-- Versión de PHP: 7.3.18-1+0~20200515.59+debian10~1.gbp12fa4f

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `krazy_lab`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Albaran_compras`
--

CREATE TABLE `Albaran_compras` (
  `cif_proveedor` varchar(9) NOT NULL,
  `numero_albaran` varchar(9) NOT NULL,
  `fecha_compra` varchar(10) NOT NULL,
  `orden_pedidos` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Albaran_compras`
--

INSERT INTO `Albaran_compras` (`cif_proveedor`, `numero_albaran`, `fecha_compra`, `orden_pedidos`) VALUES
('a1111111a', '122', '2020-06-16', '12132'),
('a1111111a', '123132', '2020-06-16', '123123'),
('12345678A', '123213', '2020-06-10', '123234213'),
('12345678A', '12345', '2020-06-12', '3445433454'),
('a1234567b', '15921404', 'null', ''),
('a1234567b', '15921428', 'null', ''),
('a1234567b', '15921741', 'null', ''),
('a1234567b', '15921742', 'null', ''),
('a1234567b', '15922320', 'null', ''),
('a1234567b', '15922343', 'null', ''),
('a1234567b', '15922347', 'null', ''),
('a1234567b', '15922354', 'null', '1592235427924'),
('a1234567b', '15922355', 'null', '1592235542994'),
('a1234567b', '15922367', 'null', '1592236778898'),
('a1234567b', '15922370', 'null', '1592237049260'),
('a1234567b', '15922377', 'null', '1592237736138'),
('a1234567b', '15922385', 'null', '1592238582016'),
('a1234567b', '15922386', 'null', '1592238654273'),
('a1234567b', '15922388', 'null', '1592238828104'),
('a1111111a', '2334', '2020-06-16', '234324'),
('a1234567b', '234', 'null', '234'),
('e4545321g', '2344344', '2020-06-16', '234234'),
('a1234567b', '234543345', 'null', '234342234'),
('a1234567b', '23911654', 'null', '1592239115774'),
('a1234567b', '23913132', 'null', '1592239131321'),
('a1234567b', '23915115', 'null', '1592239151152'),
('a1234567b', '23970207', 'null', '1592239701406'),
('a1234567b', '23971849', 'null', '1592239718499'),
('a1234567b', '24327730', 'null', '1592243276401'),
('a1234567b', '24330182', 'null', '1592243301827'),
('a1234567b', '24354842', 'null', '1592243547240'),
('a1234567b', '24945297', 'null', '1592249451957'),
('a1234567b', '24950126', 'null', '1592249501262'),
('a1234567b', '25007592', 'null', '1592250073046'),
('a1111111a', '25508049', 'null', '1592255077329'),
('a1234567b', '25511099', 'null', '1592255107085'),
('a1234567b', '25837077', 'null', '1592258369012'),
('a1111111a', '28145073', 'null', '1592281448066'),
('a1234567b', '28288522', '2020-06-16', '1592282883076'),
('a1234567b', '28300506', '2020-06-16', '1592283003777'),
('a1111111a', '28404867', '2020-06-16', '1592284047567'),
('a1234567b', '28410849', '2020-06-16', '1592284107464'),
('a1234567b', '28416086', '2020-06-16', '1592284159783'),
('a1111111a', '28550681', '2020-06-16', '1592285505868'),
('a1234567b', '28606884', '2020-06-16', '1592286066533'),
('s2332323c', '36829145', '2020-06-17', '1592368290396');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Albaran_ventas`
--

CREATE TABLE `Albaran_ventas` (
  `nif_cliente` varchar(9) NOT NULL,
  `numero_albaran` varchar(9) NOT NULL,
  `fecha_venta` date NOT NULL,
  `contado_credito` varchar(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Albaran_ventas`
--

INSERT INTO `Albaran_ventas` (`nif_cliente`, `numero_albaran`, `fecha_venta`, `contado_credito`) VALUES
('00000000A', '1231321', '2020-06-16', 'contado'),
('11111111a', '234', '2020-06-16', 'contado'),
('00000000A', '27025718', '2020-06-16', 'contado'),
('00000000A', '27071527', '2020-06-16', 'credito'),
('34343434f', '27202981', '2020-06-16', 'credito'),
('00000000A', '27214824', '2020-06-16', 'contado'),
('00000000A', '27241321', '2020-06-16', 'contado'),
('00000000A', '27313133', '2020-06-16', 'contado'),
('00000000A', '27373688', '2020-06-16', 'contado'),
('00000000A', '27402700', '2020-06-16', 'contado'),
('00000000A', '27410032', '2020-06-16', 'contado'),
('00000000A', '27422257', '2020-06-16', 'contado'),
('00000000A', '27442267', '2020-06-16', 'contado'),
('00000000A', '27467188', '2020-06-16', 'contado'),
('44444444f', '27485076', '2020-06-16', 'contado'),
('00000000A', '27500022', '2020-06-16', 'credito'),
('00000000A', '27508031', '2020-06-16', 'credito'),
('00000000A', '27530199', '2020-06-16', 'credito'),
('00000000A', '27672802', '2020-06-16', 'credito'),
('00000000A', '27718824', '2020-06-16', 'contado'),
('00000000A', '27729864', '2020-06-16', 'contado'),
('00000000A', '27758830', '2020-06-16', 'credito'),
('12121212a', '28019407', '2020-06-16', 'credito'),
('12121212a', '28172172', '2020-06-16', 'credito'),
('11111111a', '28743463', '2020-06-16', 'contado'),
('90909090p', '30726264', '2020-05-16', 'credito'),
('90909090p', '30731265', '2020-05-16', 'credito'),
('90909090p', '30737050', '2020-05-16', 'contado'),
('90909090p', '31282359', '2020-06-16', 'credito'),
('00000000A', '34627349', '2020-06-16', 'contado'),
('90909090p', '34934196', '2020-06-17', 'contado'),
('22222222e', '36836049', '2020-06-17', 'contado'),
('00000000A', '36935949', '2020-06-17', 'contado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Articulos`
--

CREATE TABLE `Articulos` (
  `codigo` varchar(9) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `precio` double NOT NULL,
  `stock` int(11) NOT NULL,
  `stock_minimo` int(11) NOT NULL,
  `stock_medio` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Articulos`
--

INSERT INTO `Articulos` (`codigo`, `descripcion`, `precio`, `stock`, `stock_minimo`, `stock_medio`) VALUES
('11', 'asasd2', 500, 506, 123, 2),
('111', 'test', 3123, 1598, 10, 20),
('1111', 'boli', 1, 10105, 5, 2),
('11111', 'dada', 234, 110, 12, 2),
('1112', 'sasa', 123, 20, 12, 3),
('1113', 'sasa', 87, 30, 23, 3),
('12', 'sdfdfg', 123, 59, 456, 5),
('123', 'papafrita', 123, 121, 3456, 43),
('1234', 'asdsa', 123, 0, 123, 3),
('1313', 'adasdsd', 13, 12, 123, 123),
('222', 'pelota', 222, 455, 5, 10),
('2323', 'sdsadf', 50, 19, 132, 12),
('333', 'raton', 876, 4474, 12, 25),
('444', 'cuchara', 3, 25, 15, 35),
('777', 'sardinas', 777, 1278, 30, 10),
('888', 'gafas', 5.89, 60, 5, 3),
('999', 'coquina', 50, 345344, 345, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Clientes`
--

CREATE TABLE `Clientes` (
  `nif` varchar(9) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `credito_disponible` double NOT NULL,
  `descuento_cliente` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Clientes`
--

INSERT INTO `Clientes` (`nif`, `nombre`, `direccion`, `telefono`, `credito_disponible`, `descuento_cliente`) VALUES
('00000000A', 'dada', 'la prueebaa', '777777777', 192, 50),
('11111111a', 'asdasd', 'asdasd', '234234234', 234, 234),
('12121212a', 'asd', 'adsasd', '243234234', 3, 33),
('12121212d', 'sdf', 'sdfsdfsdf', '334234234', 234, 234),
('12121212e', 'asdasd', 'asdasdasdas', '123123123', 23123123, 3),
('12121212s', 'qweqe', 'qweqawrewr', '123123123', 200, 20),
('12345678a', 'dada', 'asdasdasd sewrwer', '999999990', 78.78, 78.78),
('22222222e', 'asdasd', 'asdadasdas', '234234234', 6666666, 66),
('34343434f', 'ddfg', 'rfdgdfghdfgh', '555555555', 700, 5),
('43444444r', 'qweqwe', 'qweqwe', '234234234', 34, 34),
('44444444f', 'ertxv', 'sdxxcv', '454545455', 45544, 45),
('90909090p', 'jaimito', 'calle la pesacatora n14', '888888889', 7225, 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Detalle_albaran_compras`
--

CREATE TABLE `Detalle_albaran_compras` (
  `numero_albaran` varchar(9) NOT NULL,
  `codigo_articulo` varchar(9) NOT NULL,
  `orden_pedido` varchar(100) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` double NOT NULL,
  `descuento` float NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Detalle_albaran_compras`
--

INSERT INTO `Detalle_albaran_compras` (`numero_albaran`, `codigo_articulo`, `orden_pedido`, `cantidad`, `precio`, `descuento`, `id`) VALUES
('15922367', '11', '11', 11, 11, 11, 1),
('15922370', '11', '11', 11, 11, 11, 2),
('15922370', '11', '600', 11, 11, 11, 3),
('15922370', '11', '800', 11, 11, 11, 4),
('15922377', '11', '11', 11, 11, 11, 5),
('15922377', '11', '11', 11, 11, 11, 6),
('15922377', '11', '111', 11, 11, 11, 7),
('15922385', '11', '11', 11, 11, 11, 8),
('15922385', '11', '11', 11, 11, 11, 9),
('15922386', '11', '11', 11, 11, 11, 10),
('15922388', '11', '11', 11, 11, 11, 11),
('23911654', '11', '1', 1, 1, 1, 12),
('23913132', '11', '1', 1, 1, 1, 13),
('23915115', '11', '1', 1, 1, 1, 14),
('23970207', '11', '11', 11, 11, 11, 15),
('23971849', '11', '11', 11, 11, 11, 16),
('24327730', '11', '11', 11, 11, 11, 17),
('24330182', '11', '1', 1, 1, 1, 18),
('24354842', '11', '11', 11, 11, 11, 19),
('24945297', '1313', '13', 13, 13, 13, 20),
('24950126', '11', '11', 123, 11, 11, 21),
('25007592', '111', '11', 111, 111, 1, 22),
('25508049', '11', '11', 11, 11, 11, 23),
('25511099', '11', '11', 11, 11, 11, 24),
('25837077', '12', '23', 23, 123, 23, 25),
('28145073', '11', '11', 11, 11, 11, 26),
('2344344', '11', '11', 11, 11, 11, 27),
('2334', '1111', '132131212', 11111, 1, 2, 28),
('28288522', '999', '4543445', 345345, 23, 3, 29),
('28288522', '12', '23', 112, 12, 123, 30),
('28288522', '12', '123', 5, 23, 123, 31),
('28300506', '12', '12', 12, 12, 12, 32),
('28300506', '12', '1212', 10, 112, 212, 33),
('123132', '11111', '12', 12, 12, 12, 34),
('123132', '11111', '12', 10, 12, 12, 35),
('123132', '12', '12', 10, 12, 12, 36),
('123132', '12', '12', 10, 12, 12, 37),
('122', '11111', '123', 10, 123, 123, 38),
('122', '11111', '123', 10, 123, 123, 39),
('122', '11111', '123', 100, 123, 123, 40),
('122', '12', '12', 10, 12, 12, 41),
('122', '12', '12', 12, 12, 12, 42),
('28404867', '11', '11', 11, 11, 11, 43),
('28404867', '11', '11', 11, 11, 11, 44),
('28404867', '11', '11', 11, 11, 11, 45),
('28404867', '111', '11', 11, 11, 11, 46),
('28404867', '111', '11', 11, 11, 11, 47),
('28410849', '11', '11', 1, 11, 11, 48),
('28410849', '11', '11', 1, 11, 11, 49),
('28416086', '11', '11', 11, 11, 11, 50),
('28416086', '11', '11', 11, 11, 11, 51),
('28416086', '11', '11', 11, 11, 11, 52),
('28416086', '11', '11', 11, 11, 11, 53),
('28416086', '11', '11', 11, 11, 11, 54),
('28416086', '11', '111', 11, 11, 11, 55),
('28550681', '11', '11', 11, 11, 11, 56),
('28550681', '11', '11', 11, 11, 11, 57),
('28550681', '1112', '12', 10, 213, 12, 58),
('28550681', '1112', '123', 10, 123, 123, 59),
('28606884', '11', '44', 55, 66, 23, 60),
('28606884', '11', '88', 99, 12, 12, 61),
('36829145', '111', '1', 1, 1, 1, 62);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Detalle_albaran_ventas`
--

CREATE TABLE `Detalle_albaran_ventas` (
  `numero_albaran` varchar(9) NOT NULL,
  `codigo_articulo` varchar(9) NOT NULL,
  `fecha_venta` date NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` double NOT NULL,
  `descuento` float NOT NULL,
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Detalle_albaran_ventas`
--

INSERT INTO `Detalle_albaran_ventas` (`numero_albaran`, `codigo_articulo`, `fecha_venta`, `cantidad`, `precio`, `descuento`, `id`) VALUES
('1231321', '11', '2020-06-02', 12, 12, 12, 1),
('1231321', '11', '2020-06-02', 12, 12, 12, 2),
('1231321', '11', '2020-06-02', 12, 12, 99, 3),
('1231321', '11', '2020-06-02', 12, 12, 99, 4),
('27500022', '999', '2020-06-16', 1, 50, 50, 5),
('27508031', '999', '2020-06-16', 1, 50, 50, 6),
('27530199', '999', '2020-06-16', 1, 50, 50, 7),
('27672802', '444', '2020-06-16', 1, 3, 50, 8),
('27718824', '999', '2020-06-16', 2, 50, 50, 9),
('27729864', '999', '2020-06-16', 20, 50, 50, 10),
('27758830', '1111', '2020-06-16', 4, 1, 50, 11),
('28019407', '11', '2020-06-16', 1, 500, 33, 12),
('28172172', '11', '2020-06-16', 1, 500, 33, 13),
('234', '11', '2020-06-16', 1, 500, 234, 14),
('28743463', '444', '2020-06-16', 45, 3, 234, 15),
('30726264', '1111', '2020-05-16', 5, 1, 50, 16),
('30731265', '1111', '2020-05-16', 1000, 1, 50, 17),
('30737050', '444', '2020-05-16', 2, 3, 50, 18),
('31282359', '2323', '2020-06-16', 3, 50, 50, 19),
('34627349', '11', '2020-06-16', 12, 500, 50, 20),
('34934196', '111', '2020-06-17', 1, 3123, 50, 21),
('34934196', '1111', '2020-06-17', 1, 1, 50, 22),
('34934196', '12', '2020-06-17', 1, 123, 50, 23),
('34934196', '999', '2020-06-17', 1, 50, 50, 24),
('34934196', '222', '2020-06-17', 1, 222, 50, 25),
('34934196', '333', '2020-06-17', 1, 876, 50, 26),
('36836049', '123', '2020-06-17', 2, 123, 66, 27),
('36836049', '11', '2020-06-17', 2, 500, 66, 28),
('36836049', '1313', '2020-06-17', 1, 13, 66, 29),
('36836049', '1313', '2020-06-17', 1, 13, 66, 30),
('36836049', '1313', '2020-06-17', 1, 13, 66, 31),
('36935949', '111', '2020-06-17', 1, 3123, 50, 32),
('36935949', '11', '2020-06-17', 1, 500, 50, 33),
('36935949', '444', '2020-06-17', 1, 3, 50, 34),
('36935949', '2323', '2020-06-17', 1, 50, 50, 35);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Proveedores`
--

CREATE TABLE `Proveedores` (
  `cif` varchar(9) NOT NULL,
  `nombre_empresa` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefonos` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `Proveedores`
--

INSERT INTO `Proveedores` (`cif`, `nombre_empresa`, `direccion`, `telefonos`) VALUES
('12345678A', 'empresa_test', 'calle gavilan nº14', '657897890'),
('a1111111a', '23wer', 'sdfsdfsf', '343434344'),
('a1111111q', 'asdasd', 'asdasasdsd', '123333333'),
('a1212121c', 'fghfgh', 'fghfgh', '456456456'),
('a1212121d', 'asasd', 'asdasd', '123123123'),
('a1212122s', 'asdasd', 'adsads', '121212122'),
('a1212222f', 'zsdff', 'sfdsd', '343434344'),
('a1221212w', 'zxcxzc', 'zxczxczxc', '234234234'),
('a1234545r', 'cacaca', 'trhfghfghfg', '345345345'),
('a1234554r', 'asdasd', 'asdasd', '234234234'),
('A1234567B', 'fulanita', 'calle la torrija nº 14', '678909890'),
('A1234567Z', 'la papaAsa', 'calle el horno nº 14', '789898890'),
('a2222222v', 'testeo', 'asdasdas  dasasd asd as dasd', '654545454'),
('a3434343t', 'sesdfsdf', 'werwersdfgf ver w terwt4ewr', '345345345'),
('a4444444r', 'asdasd', 'asdasdasd', '234234234'),
('a9999999p', 'xdfgdfg', 'mmmmmmmm', '898989899'),
('c1233443v', 'asdasd', 'sdadsasd', '213423443'),
('e4545321g', 'asdasdasd', 'sdasdasd', '321423424'),
('s2332323c', 'zzazazaza', 'sdfsdfdasfsdafg', '343434343'),
('x1212121w', 'qawsed', 'asdasdasd', '123123123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Proveedores_articulos`
--

CREATE TABLE `Proveedores_articulos` (
  `codigo_articulo` varchar(9) NOT NULL,
  `codigo_proveedor` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Albaran_compras`
--
ALTER TABLE `Albaran_compras`
  ADD PRIMARY KEY (`numero_albaran`) USING BTREE,
  ADD UNIQUE KEY `numero_albaran` (`numero_albaran`) USING BTREE,
  ADD KEY `cif_proveedor` (`cif_proveedor`) USING BTREE,
  ADD KEY `orden_pedidos` (`orden_pedidos`) USING BTREE;

--
-- Indices de la tabla `Albaran_ventas`
--
ALTER TABLE `Albaran_ventas`
  ADD PRIMARY KEY (`numero_albaran`) USING BTREE,
  ADD UNIQUE KEY `numero_albaran` (`numero_albaran`) USING BTREE,
  ADD KEY `nif_cliente` (`nif_cliente`) USING BTREE,
  ADD KEY `fecha_venta` (`fecha_venta`) USING BTREE;

--
-- Indices de la tabla `Articulos`
--
ALTER TABLE `Articulos`
  ADD PRIMARY KEY (`codigo`),
  ADD UNIQUE KEY `codigo` (`codigo`);

--
-- Indices de la tabla `Clientes`
--
ALTER TABLE `Clientes`
  ADD PRIMARY KEY (`nif`,`nombre`,`direccion`) USING BTREE,
  ADD UNIQUE KEY `nif` (`nif`);

--
-- Indices de la tabla `Detalle_albaran_compras`
--
ALTER TABLE `Detalle_albaran_compras`
  ADD PRIMARY KEY (`id`),
  ADD KEY `numero_albaran` (`numero_albaran`) USING BTREE,
  ADD KEY `codigo_articulo` (`codigo_articulo`) USING BTREE;

--
-- Indices de la tabla `Detalle_albaran_ventas`
--
ALTER TABLE `Detalle_albaran_ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `numero_albaran` (`numero_albaran`),
  ADD KEY `codigo_articulo` (`codigo_articulo`);

--
-- Indices de la tabla `Proveedores`
--
ALTER TABLE `Proveedores`
  ADD PRIMARY KEY (`cif`),
  ADD UNIQUE KEY `cif` (`cif`);

--
-- Indices de la tabla `Proveedores_articulos`
--
ALTER TABLE `Proveedores_articulos`
  ADD PRIMARY KEY (`codigo_articulo`,`codigo_proveedor`) USING BTREE,
  ADD UNIQUE KEY `codigo_articulo` (`codigo_articulo`),
  ADD UNIQUE KEY `codigo_proveedor` (`codigo_proveedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Detalle_albaran_compras`
--
ALTER TABLE `Detalle_albaran_compras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT de la tabla `Detalle_albaran_ventas`
--
ALTER TABLE `Detalle_albaran_ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Albaran_compras`
--
ALTER TABLE `Albaran_compras`
  ADD CONSTRAINT `Albaran_compras_ibfk_1` FOREIGN KEY (`cif_proveedor`) REFERENCES `Proveedores` (`cif`);

--
-- Filtros para la tabla `Albaran_ventas`
--
ALTER TABLE `Albaran_ventas`
  ADD CONSTRAINT `Albaran_ventas_ibfk_1` FOREIGN KEY (`nif_cliente`) REFERENCES `Clientes` (`nif`);

--
-- Filtros para la tabla `Detalle_albaran_compras`
--
ALTER TABLE `Detalle_albaran_compras`
  ADD CONSTRAINT `Detalle_albaran_compras_ibfk_1` FOREIGN KEY (`codigo_articulo`) REFERENCES `Articulos` (`codigo`),
  ADD CONSTRAINT `Detalle_albaran_compras_ibfk_2` FOREIGN KEY (`numero_albaran`) REFERENCES `Albaran_compras` (`numero_albaran`);

--
-- Filtros para la tabla `Detalle_albaran_ventas`
--
ALTER TABLE `Detalle_albaran_ventas`
  ADD CONSTRAINT `Detalle_albaran_ventas_ibfk_1` FOREIGN KEY (`codigo_articulo`) REFERENCES `Articulos` (`codigo`),
  ADD CONSTRAINT `Detalle_albaran_ventas_ibfk_2` FOREIGN KEY (`numero_albaran`) REFERENCES `Albaran_ventas` (`numero_albaran`);

--
-- Filtros para la tabla `Proveedores_articulos`
--
ALTER TABLE `Proveedores_articulos`
  ADD CONSTRAINT `Proveedores_articulos_ibfk_1` FOREIGN KEY (`codigo_articulo`) REFERENCES `Articulos` (`codigo`),
  ADD CONSTRAINT `Proveedores_articulos_ibfk_2` FOREIGN KEY (`codigo_proveedor`) REFERENCES `Proveedores` (`cif`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
