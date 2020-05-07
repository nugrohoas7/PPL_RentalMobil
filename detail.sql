-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 07 Bulan Mei 2020 pada 06.16
-- Versi server: 10.4.6-MariaDB
-- Versi PHP: 7.3.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rental_mobil`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail`
--

CREATE TABLE `detail` (
  `nama_rental` varchar(200) NOT NULL,
  `alamat_rental` varchar(200) NOT NULL,
  `jml_kendaraan` int(20) NOT NULL,
  `telp_rental` int(20) NOT NULL,
  `merk_mobil` varchar(200) NOT NULL,
  `tahun_mobil` int(5) NOT NULL,
  `plat_mobil` varchar(100) NOT NULL,
  `model_mobil` varchar(100) NOT NULL,
  `kode_sewa` varchar(100) NOT NULL,
  `jenis_mobil` varchar(100) NOT NULL,
  `biaya_sewa` int(50) NOT NULL,
  `denda_sewa` int(50) NOT NULL,
  `tanggal_sewa` varchar(60) NOT NULL,
  `tanggal_kembali` varchar(60) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `umur` int(100) NOT NULL,
  `no_identitas` int(20) NOT NULL,
  `alamat` varchar(10) NOT NULL,
  `no_telp` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
