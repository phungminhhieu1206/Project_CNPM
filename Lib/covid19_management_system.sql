-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2020 at 05:31 PM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `covid19_management_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `benh`
--

CREATE TABLE `benh` (
  `id_benh` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `benh`
--

INSERT INTO `benh` (`id_benh`, `name`) VALUES
(1, 'Gan mãn tính'),
(2, 'Máu mãn tính'),
(3, 'Phổi mãn tính'),
(4, 'Thận mãn tính'),
(5, 'Tim mạch'),
(6, 'Huyết áp cao'),
(7, 'HIV/AIDS'),
(8, 'Tiểu đường'),
(9, 'Ung thư');

-- --------------------------------------------------------

--
-- Table structure for table `cach_ly`
--

CREATE TABLE `cach_ly` (
  `IDKhaiCL` int(11) NOT NULL,
  `IDNhanKhau` int(11) NOT NULL,
  `ngayKhaiCL` date NOT NULL,
  `loaiCL` int(11) NOT NULL,
  `mucDoCL` int(11) NOT NULL,
  `ngayBatDauCL` date NOT NULL,
  `diaChiCL` varchar(100) NOT NULL,
  `soPhongCL` varchar(100) NOT NULL,
  `soGiuongCL` varchar(100) NOT NULL,
  `tenNgCungPhCL` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cach_ly`
--

INSERT INTO `cach_ly` (`IDKhaiCL`, `IDNhanKhau`, `ngayKhaiCL`, `loaiCL`, `mucDoCL`, `ngayBatDauCL`, `diaChiCL`, `soPhongCL`, `soGiuongCL`, `tenNgCungPhCL`) VALUES
(17, 1, '2020-12-20', 1, 3, '2020-12-20', '', '', '', ''),
(19, 2, '2020-12-20', 1, 3, '2020-12-20', '', '', '', ''),
(20, 3, '2020-12-20', 2, 1, '2020-12-20', 'Sân bay Nội Bài', 'G5', 'G5', 'Thủy, Hiếu'),
(21, 4, '2020-12-20', 2, 1, '2020-12-20', 'Đại học bách khoa Hà Nội', 'G1', 'G1', 'Thắng, Vũ'),
(22, 7, '2020-12-20', 2, 2, '2020-12-20', 'Sân bóng Bách Khoa', '1', '2', ''),
(23, 5, '2020-12-20', 2, 2, '2020-12-20', 'Lãnh cung', '1', '3', ''),
(24, 8, '2020-12-20', 2, 1, '2020-12-20', 'Sân vận động quốc gia Mỹ Đình', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `dich_te`
--

CREATE TABLE `dich_te` (
  `id` int(11) NOT NULL,
  `id_person` int(11) NOT NULL,
  `ngay_khai` date NOT NULL,
  `tx_benh` int(11) NOT NULL,
  `tu_vung_dich` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dich_te`
--

INSERT INTO `dich_te` (`id`, `id_person`, `ngay_khai`, `tx_benh`, `tu_vung_dich`) VALUES
(4, 1, '2020-12-18', 0, 0),
(5, 3, '2020-12-18', 0, 0),
(6, 6, '2020-12-18', 0, 1),
(8, 2, '2020-12-20', 0, 0),
(9, 4, '2020-12-20', 0, 0),
(10, 7, '2020-12-20', 1, 0),
(11, 5, '2020-12-20', 1, 1),
(12, 8, '2020-12-20', 0, 0),
(16, 9, '2020-12-20', 0, 0),
(17, 10, '2020-12-20', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `khai_benh`
--

CREATE TABLE `khai_benh` (
  `id` int(11) NOT NULL,
  `id_person` int(11) NOT NULL,
  `ma_benh` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khai_benh`
--

INSERT INTO `khai_benh` (`id`, `id_person`, `ma_benh`) VALUES
(71, 3, 2),
(72, 3, 5),
(73, 3, 8),
(83, 2, 4),
(84, 2, 5),
(85, 2, 9),
(86, 4, 3),
(87, 4, 4),
(88, 4, 5),
(89, 7, 2),
(90, 7, 5),
(91, 7, 6),
(92, 8, 4),
(105, 8, 4);

-- --------------------------------------------------------

--
-- Table structure for table `khai_trieu_chung`
--

CREATE TABLE `khai_trieu_chung` (
  `id` int(11) NOT NULL,
  `id_person` int(11) NOT NULL,
  `trieu_chung` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `khai_trieu_chung`
--

INSERT INTO `khai_trieu_chung` (`id`, `id_person`, `trieu_chung`) VALUES
(80, 3, 4),
(81, 3, 5),
(82, 3, 6),
(98, 6, 1),
(99, 6, 4),
(100, 6, 5),
(101, 6, 6),
(102, 6, 7),
(103, 2, 4),
(104, 2, 8),
(105, 2, 9),
(106, 4, 3),
(107, 4, 5),
(108, 4, 7),
(128, 10, 1),
(129, 10, 5),
(130, 10, 9),
(131, 9, 5),
(132, 9, 6),
(133, 9, 8),
(134, 1, 1),
(135, 1, 2),
(136, 1, 5),
(137, 1, 6);

-- --------------------------------------------------------

--
-- Table structure for table `nhan_khau`
--

CREATE TABLE `nhan_khau` (
  `ID` int(11) NOT NULL,
  `hoTen` varchar(100) NOT NULL,
  `chungMinhThu` varchar(100) NOT NULL,
  `ngaySinh` date NOT NULL,
  `gioiTinh` int(11) NOT NULL,
  `danToc` varchar(100) NOT NULL,
  `quocTich` varchar(100) NOT NULL,
  `ngheNghiep` varchar(100) NOT NULL,
  `soDienThoai` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `diaChi` varchar(100) NOT NULL,
  `checkBHYT` int(11) NOT NULL,
  `maTheBHYT` varchar(100) NOT NULL,
  `maHoKhau` varchar(100) NOT NULL,
  `noiLamViec` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `nhan_khau`
--

INSERT INTO `nhan_khau` (`ID`, `hoTen`, `chungMinhThu`, `ngaySinh`, `gioiTinh`, `danToc`, `quocTich`, `ngheNghiep`, `soDienThoai`, `email`, `diaChi`, `checkBHYT`, `maTheBHYT`, `maHoKhau`, `noiLamViec`) VALUES
(1, 'Phùng Minh Hiếu', '123456789', '2000-06-12', 1, 'Kinh', 'Việt Nam', 'Sinh viên', '0981932985', 'hieuphung@gmail.com', 'Cầu Giấy, Hà Nội', 0, '', 'HK1206', 'Đại học bách khoa Hà Nội'),
(2, 'Lươn Thanh Độ', '001200083536', '1989-12-18', 1, 'Tày', 'Việt Nam', 'Streamer', '0123686799', 'dophung@gmail.com', 'Cao Bằng', 0, '', 'HK1812', 'Hà Nội'),
(3, 'Nguyễn Thùy Trang', '001200087899', '2000-05-19', 2, 'Kinh', 'Việt Nam', 'Dược sĩ', '0989430288', 'thuytrang@gmail.com', 'Trung Châu, Đan Phượng, Hà Nội', 1, 'YT1905', 'HK1905', 'Đại học dược Hà Nội'),
(4, 'Phạm Văn Hạnh', '001200083525', '2000-07-06', 1, 'Kinh', 'Việt Nam', 'Sinh viên', '0983586790', 'hanhpv@gmail.com', 'Hải Dương', 0, '', 'HK0607', 'DS Lab'),
(5, 'Đinh Thị Thủy', '123456780', '2000-09-24', 2, 'Kinh', 'Việt Nam', 'Bán khẩu trang', '0979430299', 'dinhthuy249@gmail.com', 'Trung Châu, Đan Phượng, Hà Nội', 1, 'YT2409', 'HK2409', 'Đại học dược Hà Nội'),
(6, 'Trần Thị Thu Hà', '017413245', '1998-05-08', 2, 'Kinh', 'Việt Nam', '', '0123679901', 'hathu@gmail.com', 'Xuân Thủy, Cầu Giấy, Hà Nội', 1, 'YT85', 'HK85', ''),
(7, 'Nguyễn Văn An', '234567189', '1998-12-03', 1, 'Tày', 'Việt Nam', 'Kinh doanh tự do', '0987234012', 'vanan@gmail.com', 'Số 8 Phú Diễn, Hà Nội', 0, '', 'HK0312', 'Hà Nội'),
(8, 'Phạm Hải Yến', '001200035737', '2000-09-18', 2, 'Kinh', 'Việt Nam', 'Sinh viên', '0123454898', 'yenpham@gmail.com', 'Đại Cồ Việt, Hà Nội', 1, 'YT1809', 'HK1809', 'Đại học bách khoa Hà Nội'),
(9, 'Nguyễn Mạnh Tưởng', '001200025890', '1999-12-20', 1, 'Kinh', 'Việt Nam', 'Kỹ sư', '0351917689', 'tuongnguyen@gmail.com', 'Số 6, Hai Bà Trưng, Hà Nội', 1, 'YT2012', 'HK2012', 'Đại học bách khoa Hà Nội'),
(10, 'Phạm Huy Hùng', '001200089803', '2000-05-06', 1, 'Mông', 'Việt Nam', 'Sửa chữa ô tô', '0978908125', 'hungpham@gmail.com', 'Số 7, Trần Thái Tông, Hà Nội', 0, '', 'HK0605', 'Quán bar'),
(11, 'Nguyễn Thạch Thủy', '001300025790', '1997-05-14', 2, 'Kinh', 'Việt Nam', 'Dược sĩ', '0978236798', 'thuy@gmail.com', 'Số 9, Quốc Tử Giám, Hà Nội', 1, 'YT1405', 'HK1405', 'Đại học dược Hà Nội'),
(12, 'Trần Thị Thúy Linh', '001200045489', '2000-03-03', 2, 'Thái', 'Việt Nam', 'Sinh viên', '0989222890', 'thuylinh@gmail.com', 'Vân Nam, Phúc Thọ, Hà Nội', 0, '', 'HK0303', 'Đại học quốc gia Hà Nội'),
(13, 'Ngô Minh Hiếu', '001200023989', '1997-07-05', 1, 'Kinh', 'Việt Nam', 'Bác sĩ', '0909789233', 'hieungo@gmail.com', 'Số 9, Lý Thái Tổ, Hà Nội', 0, '', 'HK0507', 'Bệnh viên bạch mai Hà Nội'),
(14, 'Trịnh Thu Hà', '001200066709', '1996-09-03', 2, 'Kinh', 'Việt Nam', 'Ca sĩ', '0908788933', 'hathu@gmail.com', 'Số 9, Ô Chợ Dừa, Hà Nội', 0, '', 'HK0309', 'Bôn ba'),
(15, 'Phùng Mạnh Tưởng', '001300036579', '1999-10-04', 2, 'Kinh', 'Việt Nam', 'Sinh viên', '0979443099', 'manhtuong@gmail.com', 'Số 10, Tạ Quang Bửu, Hà Nội', 1, 'YT0410', 'HK0410', ''),
(17, 'Ngô Thị Xuyến', '001300024567', '1990-09-08', 2, 'Kinh', 'Việt Nam', '', '0909000899', 'ngoxuyen@gmail.com', 'Số 9, Trần Đăng Ninh, Hà Nội', 0, '', 'HK0809', '');

-- --------------------------------------------------------

--
-- Table structure for table `test_covid`
--

CREATE TABLE `test_covid` (
  `IDTestCovid` int(11) NOT NULL,
  `IDNhanKhau` int(11) NOT NULL,
  `ngayTest` date NOT NULL,
  `lanTest` int(11) NOT NULL,
  `hinhThucTest` int(11) NOT NULL,
  `diaDiemTest` varchar(100) NOT NULL,
  `ketQuaTest` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `test_covid`
--

INSERT INTO `test_covid` (`IDTestCovid`, `IDNhanKhau`, `ngayTest`, `lanTest`, `hinhThucTest`, `diaDiemTest`, `ketQuaTest`) VALUES
(25, 1, '2020-12-20', 1, 1, 'Bệnh viện XanhPon, Hà Nội', 2),
(30, 1, '2020-12-20', 2, 2, 'Bệnh viện Hoài Đức, Hà Nội', 1),
(31, 4, '2020-12-20', 1, 1, '', 2);

-- --------------------------------------------------------

--
-- Table structure for table `trieu_chung`
--

CREATE TABLE `trieu_chung` (
  `id` int(11) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `trieu_chung`
--

INSERT INTO `trieu_chung` (`id`, `name`) VALUES
(1, 'Sốt'),
(2, 'Ho'),
(3, 'Khó thở'),
(4, 'Viêm phổi'),
(5, 'Đau họng'),
(6, 'Mệt mỏi'),
(7, 'Buồn nôn'),
(8, 'Tiêu chảy'),
(9, 'Xuất huyết ngoài da');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `benh`
--
ALTER TABLE `benh`
  ADD PRIMARY KEY (`id_benh`);

--
-- Indexes for table `cach_ly`
--
ALTER TABLE `cach_ly`
  ADD PRIMARY KEY (`IDKhaiCL`),
  ADD KEY `fk_cl` (`IDNhanKhau`);

--
-- Indexes for table `dich_te`
--
ALTER TABLE `dich_te`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_dt_1` (`id_person`);

--
-- Indexes for table `khai_benh`
--
ALTER TABLE `khai_benh`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_benh` (`ma_benh`),
  ADD KEY `fk_kb_2` (`id_person`);

--
-- Indexes for table `khai_trieu_chung`
--
ALTER TABLE `khai_trieu_chung`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_trieu_chung` (`trieu_chung`),
  ADD KEY `fk_ktc_1` (`id_person`);

--
-- Indexes for table `nhan_khau`
--
ALTER TABLE `nhan_khau`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `test_covid`
--
ALTER TABLE `test_covid`
  ADD PRIMARY KEY (`IDTestCovid`),
  ADD KEY `fk_test` (`IDNhanKhau`);

--
-- Indexes for table `trieu_chung`
--
ALTER TABLE `trieu_chung`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `benh`
--
ALTER TABLE `benh`
  MODIFY `id_benh` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `cach_ly`
--
ALTER TABLE `cach_ly`
  MODIFY `IDKhaiCL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `dich_te`
--
ALTER TABLE `dich_te`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `khai_benh`
--
ALTER TABLE `khai_benh`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=106;

--
-- AUTO_INCREMENT for table `khai_trieu_chung`
--
ALTER TABLE `khai_trieu_chung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=138;

--
-- AUTO_INCREMENT for table `nhan_khau`
--
ALTER TABLE `nhan_khau`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `test_covid`
--
ALTER TABLE `test_covid`
  MODIFY `IDTestCovid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `trieu_chung`
--
ALTER TABLE `trieu_chung`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cach_ly`
--
ALTER TABLE `cach_ly`
  ADD CONSTRAINT `fk_cl` FOREIGN KEY (`IDNhanKhau`) REFERENCES `nhan_khau` (`ID`);

--
-- Constraints for table `dich_te`
--
ALTER TABLE `dich_te`
  ADD CONSTRAINT `fk_dt_1` FOREIGN KEY (`id_person`) REFERENCES `nhan_khau` (`ID`);

--
-- Constraints for table `khai_benh`
--
ALTER TABLE `khai_benh`
  ADD CONSTRAINT `fk_benh` FOREIGN KEY (`ma_benh`) REFERENCES `benh` (`id_benh`),
  ADD CONSTRAINT `fk_kb_2` FOREIGN KEY (`id_person`) REFERENCES `nhan_khau` (`ID`);

--
-- Constraints for table `khai_trieu_chung`
--
ALTER TABLE `khai_trieu_chung`
  ADD CONSTRAINT `fk_ktc_1` FOREIGN KEY (`id_person`) REFERENCES `nhan_khau` (`ID`),
  ADD CONSTRAINT `fk_trieu_chung` FOREIGN KEY (`trieu_chung`) REFERENCES `trieu_chung` (`id`);

--
-- Constraints for table `test_covid`
--
ALTER TABLE `test_covid`
  ADD CONSTRAINT `fk_test` FOREIGN KEY (`IDNhanKhau`) REFERENCES `nhan_khau` (`ID`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `fk_login` FOREIGN KEY (`id`) REFERENCES `nhan_khau` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
