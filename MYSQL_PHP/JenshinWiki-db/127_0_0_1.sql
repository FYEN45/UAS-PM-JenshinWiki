-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Des 2021 pada 07.59
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
('JWI001', 'Wind Catcher', 'Dapat menampung Anemogranum dan menggunakan kekuatan yang ada di dalam Anemogranum untuk menciptakan arus angin.\r\n\r\nPara ahli Knights of Favonius menciptakan alat ini untuk memanfaatkan berkah dari Archon Anemo dan mempermudah Outrider dan para Knight dalam bertualang dan melakukan pengintaian.\r\n\r\nTetapi setiap pengguna Wind Glider harus memiliki Surat Izin Meluncur, sehingga manfaat dari alat ini cukup terbatas.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3004.png'),
('JWI002', 'Condensed Resin', 'Kamu bisa memiliki maksimal 5 buah dalam satu waktu.\r\n\r\nDapat digunakan sebagai pengganti Original Resin untuk mendapatkan lebih banyak hadiah dari Petrified Tree dan Ley Line Blossom.\r\n\r\nKristal yang mengandung banyak energi.\r\n\r\nPohon dan bunga yang berwarna perak ini terhubung ke Ley Line dengan menggunakan energi di dalam Resin, dan memurnikan endapan yang ada di dalamnya.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3007.png'),
('JWI003', 'Parametric Transformer', 'Sebuah alat yang bisa mengubah material yang diletakkan di dalamnya menjadi material lain.\r\n\r\nKamu harus melancarkan serangan Elemental saat proses transmutasi berlangsung untuk menciptakan energi yang cukup untuk melakukan proses tersebut.\r\n\r\nSetelah mendapatkan material hasil transmutasi, kamu harus menunggu selama tujuh hari ke depan untuk dapat menggunakannya lagi.\r\n\r\nAlat misterius yang kamu temukan di dalam reruntuhan ini memiliki kemampuan untuk mempercepat suatu siklus dan mengubah sebuah material. Secara umum, segala sesuatu berada dalam sebuah siklus. Ingatan dan elemen mengalir di dalam ley line, buah yang busuk jatuh dan kembali ke tanah, kemudian dari dalam tanah tersebut akan tumbuh sebatang pohon yang akan menghasilkan buah yang subur.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3016.png'),
('JWI004', 'Serenitea Pot', 'Poci yang didapat dari Madame Ping. Sepertinya ada alam lain yang terdapat di dalamnya...', 'https://genshin.honeyhunterworld.com/img/gadget/i_3024.png'),
('JWI005', 'Seed Dispensary', 'Wadah khusus yang diperoleh dari Madame Ping. Kata Madame Ping, wadah ini punya kemampuan untuk \"menghasilkan benih\"... Sesuai dengan karakteristik tanaman aslinya, benih yang diperoleh darinya dapat ditanam di ladang di dalam Alam Poci.\r\n\r\nTanaman yang dapat ditanam: Silk Flower, Sweet Flower, Cecilia, Glaze Lily, Windwheel Aster, Qingxin, Violetgrass, Valberry, Small Lamp Grass, Jueyun Chili, Carrot, Radish, Mint, Mushroom, Naku Weed, Horsetail, Snapdragon, Lotus Head, Calla Lily, Seagrass, dan Sea Ganoderma.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3028.png'),
('JWI006', 'Red Feather Fan', 'Meningkatkan 30% Movement SPD meluncur selama 30 detik setelah digunakan.\r\n\r\nItem ini memiliki kekuatan misterius. Saat berada di tangan pasukan tengu, peralatan ini memiliki efek kekuatan yang bervariasi. Namun saat berada di tangan orang biasa, peralatan ini masih memiliki kekuatan yang cukup untuk \"membuat tubuh lebih ringan\" di udara.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3030.png'),
('JWI007', 'Ayesha\'s Chaos Prospector', 'Alat praktis yang dibuat oleh Ayesha saat dia sedang meneliti benda-benda peninggalan zaman kuno. Alat ini bisa dipakai untuk mendeteksi peninggalan kuno yang terkubur di bawah tanah. Namun, karena produksinya yang tergesa-gesa, tingkat akurasinya masih kurang. Pengguna alat ini harus siap menghadapi hal-hal tak terduga yang mungkin terjadi ketika menggunakannya ....', 'https://genshin.honeyhunterworld.com/img/gadget/i_3038.png'),
('JWI008', 'Shiki Koshou', 'Hadiah dari Shiki Taishou, berupa replika yang memiliki kekuatan spiritual. Shiki Koshou tidak dapat menggunakan kekuatan jimat, tapi dia dapat berkomunikasi dengan Shiki Taishou kapan pun, dan membagikan apa yang dia lihat dan dia dengar. Jadi bisa dibilang, kamu seperti sedang membawa Shiki Taishou di perjalananmu.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3040.png'),
('JWI009', 'Memento Lens', 'Sebuah lensa misterius. Jika kamu melihat patung rubah kecil dengan lensa ini, kamu dapat menemukan hal-hal yang sebelumnya tidak ada.\r\n\r\nKonon, Kitsune Saiguu yang legendaris meninggalkan sebuah catalyst untuk Hiroshi, kepala Klan Hiiragi saat itu, sebagai alat pengusir roh jahat.\r\n\r\nKlan Hiiragi kemudian memesan Kamera khusus dari luar bangsa dan memasangkan catalyst tersebut pada lensa, dan menghadiahkannya kembali ke Kuil Agung Narukami sebagai simbol persahabatan.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3031.png'),
('JWI010', 'Floral Zither', 'Alat musik yang dapat digunakan dalam pertunjukan.\r\n\r\nTerbuat dari bahan yang agak unik, dan sepertinya sangat berbeda dari desain yang sedang populer saat ini. Kekuatan tak terduga telah terjalin ke dalam benang halus yang bahkan memungkinkannya memunculkan ilusi di dalam hati pemainnya.\r\n\r\nAda pepatah lama di Liyue yang berbunyi: \"Semakin lama mata memandang sebuah ilusi yang indah, semakin dalam pula hati seseorang akan terseret ke dalam godaannya.\" Tampaknya hanya orang yang berkemauan keras yang bisa menggunakan alat musik ini.', 'https://genshin.honeyhunterworld.com/img/gadget/i_3039.png');

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
('JWM1', 'Dvalin', 'Dvalin, sang Dragon of the East, salah satu dari Four Winds di Mondstadt.\r\n\r\nSeiring berjalannya waktu di tengah-tengah kegelapan pekat, bahkan batu permata paling berkilau sekalipun akan tertutup oleh debu, dan seekor naga yang mulia pun akan terjerat dalam rasa benci dan dendam.\r\n\r\nTapi kalian harus selalu ingat bahwa debu dapat dibersihkan dan rasa benci ataupun dendam yang meracuni dapat disingkirkan.', 'https://genshin.honeyhunterworld.com/img/enemy/m_29010101.png'),
('JWM2', 'Lupus Boreas', 'Serigala Wolvendom adalah hantu yang menakutkan bagi orang-orang Mondstadt.\r\n\r\nSuatu wujud kabur yang berpijar dan langsung menghilang di tengah-tengah hutan, lolongan dingin yang berbunyi di antara sebuah jarak yang tidak dapat ditebak, pandangan mata dari suatu sudut tersembunyi yang terus menggerayangi punggung seseorang.\r\n\r\nManusia sangat jarang memiliki kesempatan untuk bertatap mata dengan serigala, karena ini juga adalah hukum yang ditetapkan Lupus Boreas untuk kaumnya.', 'https://genshin.honeyhunterworld.com/img/enemy/m_29020101.png'),
('JWM3', 'Childe', 'Dia adalah seorang petarung sejati yang selalu haus akan pertarungan.\r\n\r\nSetiap pertumpahan darah, setiap tantangan hidup dan mati, adalah cobaan yang menyenangkan bagi dirinya.\r\n\r\nOrang-orang mengatakan bahwa sejak muda, kemampuan bertarung Tartaglia sudah dikenal di seluruh penjuru.\r\n\r\nTetapi dia sendiri tidak pernah memamerkan dirinya seperti itu.', 'https://genshin.honeyhunterworld.com/img/enemy/m_29030101.png'),
('JWM4', 'Azhdaha', 'Seekor naga tua yang umurnya setua gunung yang ditinggalinya.\r\n\r\nDi zaman yang hampir terlupakan, dia berdiri bersama dengan orang yang menguasai pelabuhan batu. Tapi pada akhirnya, mereka berseteru, dan naga tersebut tersegel di bawah tanah yang gelap. Setelah beratus tahun lamanya disegel, kekuatannya perlahan menghilang. Dia juga telah menjadi cacat karena berbagai jenis erosi yang dialaminya.\r\n\r\nGetaran amarah yang samar dari naga di bawah tanah ini bergema melalui perut pegunungan bagaikan sebuah kenangan dari zaman dulu.', 'https://genshin.honeyhunterworld.com/img/enemy/m_29040101.png'),
('JWM5', 'La Signora', 'Berbeda dengan Fatui lainnya yang memanfaatkan kekuatan Delusion, Delusion milik Signora adalah sebuah alat untuk meredam kekuatan api yang bisa melahap segalanya. Daripada sebuah senjata, mungkin lebih tepat disebut sebagai alat untuk menangkap musuhnya.\r\n\r\nBara api yang telah terkumpul selama bertahun-tahun akhirnya memecahkan segel Delusion dan membuat penampilan Signora seperti saat ini.\r\n\r\nWarna merah fajar terpantul di bola matanya, dan akhirnya Signora membuka sayap apinya dan terbang menuju fajar di ufuk langit.\r\n', 'https://genshin.honeyhunterworld.com/img/enemy/m_29050101.png');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `status` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `username`, `password`, `status`) VALUES
(1, 'admin', 'admin@gmail.com', 'admin', 'admin123', 'admin'),
(2, 'Yoimiya', 'yoimiya@gmail.com', 'yoimiya', 'yoimiya123', 'user'),
(3, 'Albedo', 'albedo@yahooi.com', 'albedo', 'albedo123', 'user'),
(4, 'Ganyu', 'ganyu@hotmail.com', 'ganyu', 'ganyu123', 'user'),
(5, 'Zhongli', 'zhongli@yahoo.com', 'lizhong', 'zhongli123', 'user'),
(6, 'Venti', 'venti@gmail.com', 'venti', 'venti123', 'user'),
(7, 'Qiqi', 'qiqi@hotmail.com', 'qiqii', 'qiqi1234', 'user'),
(8, 'Mona', 'mona@gmail.com', 'monaa', 'mona_123', 'user'),
(9, 'Klee', 'klee@yahoo.com', 'kleee', 'kleee123', 'user'),
(10, 'Keqing', 'keqing@hotmail.com', 'keqing', 'qing_123', 'user'),
(11, 'Jean', 'jean@gmail.com', 'jeann', 'jean_123', 'user'),
(12, 'Diluc', 'diluc@hotmail.com', 'diluc', 'diluc123', 'user'),
(13, 'Arataki Itto', 'itto@gmail.com', 'arataki', 'itto1234', 'user'),
(14, 'Aloy', 'aloy@yahoo.co.id', 'aloyy', 'aloy_123', 'user'),
(15, 'Raiden Shogun', 'shogun@gmail.com', 'shogun', 'raiden123', 'user'),
(16, 'Sangonomiya Kokomi', 'kokomi@gmail.com', 'sangonomiya', 'kokomi123', 'user'),
(17, 'Kaedehara Kazuna', 'kaedehara@gmail.com', 'kazuna', 'kaedehara123', 'user'),
(18, 'Eula', 'eula@gmail.com', 'eulaa', 'eula@123', 'user'),
(19, 'Hu Tao', 'hutao@gmail.com', 'taohu', 'hutao123', 'user'),
(20, 'Kamisato Ayaka', 'kamisatoayaka@yahoo.co.id', 'ayaka', 'kamisato123', 'user'),
(21, 'Tartaglia', 'tartaglia@gmail.com', 'tartaglia', 'tartaglia123', 'user'),
(22, 'Xiao', 'xiao@yahoo.com', 'xiaoo', 'xiaoo_123', 'user');

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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`,`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
