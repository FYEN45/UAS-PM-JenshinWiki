-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 27 Des 2021 pada 07.16
-- Versi server: 10.4.18-MariaDB
-- Versi PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jenshinwiki_db`
--
CREATE DATABASE IF NOT EXISTS `jenshinwiki_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `jenshinwiki_db`;

-- --------------------------------------------------------

--
-- Struktur dari tabel `items`
--

CREATE TABLE `items` (
  `id` varchar(20) NOT NULL,
  `item_name` varchar(100) NOT NULL,
  `item_description` text NOT NULL,
  `item_image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `items`
--

INSERT INTO `items` (`id`, `item_name`, `item_description`, `item_image`) VALUES
('JWI1', 'Wind Catcher', 'Dapat menampung Anemogranum dan menggunakan kekuatan yang ada di dalam Anemogranum untuk menciptakan arus angin.\r\n\r\nPara ahli Knights of Favonius menciptakan alat ini untuk memanfaatkan berkah dari Archon Anemo dan mempermudah Outrider dan para Knight dalam bertualang dan melakukan pengintaian.\r\n\r\nTetapi setiap pengguna Wind Glider harus memiliki Surat Izin Meluncur, sehingga manfaat dari alat ini cukup terbatas.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3004.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `monsters`
--

CREATE TABLE `monsters` (
  `id` varchar(20) NOT NULL,
  `monster_name` varchar(100) NOT NULL,
  `monster_description` text NOT NULL,
  `monster_image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `monsters`
--

INSERT INTO `monsters` (`id`, `monster_name`, `monster_description`, `monster_image`) VALUES
('JWM1', 'Dvalin', 'Dvalin, sang Dragon of the East, salah satu dari Four Winds di Mondstadt.\r\n\r\nSeiring berjalannya waktu di tengah-tengah kegelapan pekat, bahkan batu permata paling berkilau sekalipun akan tertutup oleh debu, dan seekor naga yang mulia pun akan terjerat dalam rasa benci dan dendam.\r\n\r\nTapi kalian harus selalu ingat bahwa debu dapat dibersihkan dan rasa benci ataupun dendam yang meracuni dapat disingkirkan.', 'https://genshin.honeyhunterworld.com/img/enemy/m_29010101.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `password`, `status`) VALUES
(1, 'admin', 'admin', 'admin123', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `monsters`
--
ALTER TABLE `monsters`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`,`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
