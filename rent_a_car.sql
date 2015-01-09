-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- Računalo: 127.0.0.1
-- Vrijeme generiranja: Ožu 09, 2014 u 03:59 
-- Verzija poslužitelja: 5.6.14
-- PHP verzija: 5.5.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Baza podataka: `rent_a_car`
--

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `automobili`
--

CREATE TABLE IF NOT EXISTS `automobili` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kreirano` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `registarska_oznaka` varchar(12) NOT NULL,
  `marka` varchar(100) NOT NULL,
  `oznaka` varchar(100) NOT NULL,
  `snaga_motora` varchar(50) NOT NULL,
  `boja` varchar(50) NOT NULL,
  `godina_proizvodnje` varchar(10) NOT NULL,
  `cijena_pri_kupnji` double(12,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=58 ;

--
-- Izbacivanje podataka za tablicu `automobili`
--

INSERT INTO `automobili` (`id`, `kreirano`, `registarska_oznaka`, `marka`, `oznaka`, `snaga_motora`, `boja`, `godina_proizvodnje`, `cijena_pri_kupnji`) VALUES
(57, '2014-03-09 15:57:30', '654645', 'ertert', 'dtggdf', 'ggfdg', 'fdgfdg', '56', 200.00),
(6, '2014-03-02 19:09:23', 'rewrwe', '324', 'rwerewr', '45454', 'trete', 'rter', 656.45),
(7, '2014-03-02 19:09:41', 'rere', 'r4545', '43543', '5tetr', '6546', 'tter', 0.00),
(8, '2014-03-02 19:10:11', 'rwerw', '4545', 'trt', '456346', 'tret', 'tre655', 0.00),
(12, '2014-03-02 19:33:10', 'e454353453', 'trertretret', '565656', 'rttr5656', 'ztz5656', '454545', 65656.00),
(13, '2014-03-02 19:33:25', 'aaa', 'bbb', 'ccc', 'ddd', 'eee', 'fff', 0.00),
(14, '2014-03-02 19:36:02', 'dfdf', 'dfrses"fdsfs"', 'fdsdf"dsdf', 'fdsfdsffds', 'dsfds', '', 0.00),
(15, '2014-03-02 19:50:52', 'erer', '4545', 'trtrt', '545656tzr', 'ztztz', '77tzt', 0.00),
(16, '2014-03-02 19:51:15', '445', 'trtretertert', 'ertre', 't5656', 'tzzt', '5656ztz', 33333.00),
(41, '2014-03-02 23:55:24', '454eerrtrt', 'rwerwe', 'werwer', '', '', '', 0.00),
(42, '2014-03-03 00:37:59', '454545', 'tertre', '', '', '', '', 568.00),
(43, '2014-03-04 19:38:02', 'sadsad', 'sadasd', '', '', '', '', 0.00),
(44, '2014-03-04 19:38:14', '43434', 'dssad', '', '', '', '', 0.00),
(45, '2014-03-04 19:39:43', '343455', 'dsdsad', '', '', '', '', 0.00),
(47, '2014-03-04 19:41:16', 'dsa333', 'trrrr', '', '', '', '', 45.00),
(49, '2014-03-04 19:42:26', 'zzzz', 'rrr', '', '', '', '', 0.00),
(50, '2014-03-04 19:53:05', 'wewqeqwewq', 'ferewrer dfgdgdfgdfgfdg gfdgdfgf4545 frgdgdfgdfg df', 'grftt', 'gfdg', 'rt5et', '45454', 34.00),
(51, '2014-03-04 22:16:16', 'šnjosss', 'sdsad', '', '', '', 'sds', 34.00),
(52, '2014-03-08 01:57:29', '55-32-ZG24', 'Ford', '22-3c', '1500ccm', 'plava', '2010', 70000.00),
(53, '2014-03-08 01:59:07', 'AAA-3_B222', 'Ferarri', '22-334', '3000ccm', 'žuta', '2001', 80000.00),
(54, '2014-03-08 02:01:37', 'ACD-777-ZG', 'Fiat', '33-EO', '400ccm', 'siva', '2007', 40000.00),
(56, '2014-03-08 02:34:28', '45554', '', '', '', '', '', 0.00);

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `prihodi`
--

CREATE TABLE IF NOT EXISTS `prihodi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kreirano` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `registarska_oznaka` varchar(12) NOT NULL,
  `datum` varchar(10) NOT NULL,
  `datum_vracanja` varchar(10) NOT NULL,
  `osoba_tvrtka` varchar(255) NOT NULL,
  `km_pocetak` varchar(20) NOT NULL,
  `km_kraj` varchar(20) NOT NULL,
  `vrsta_prihoda` varchar(150) NOT NULL,
  `opis` varchar(255) NOT NULL,
  `iznos` double(12,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Izbacivanje podataka za tablicu `prihodi`
--

INSERT INTO `prihodi` (`id`, `kreirano`, `registarska_oznaka`, `datum`, `datum_vracanja`, `osoba_tvrtka`, `km_pocetak`, `km_kraj`, `vrsta_prihoda`, `opis`, `iznos`) VALUES
(1, '2014-03-08 00:57:56', '', '', '', '', '', '', '', '', 0.00),
(2, '2014-03-08 02:49:40', '55-32-ZG24', '2.3.2001', '', '', '', '', '', '', 0.00),
(3, '2014-03-08 10:28:23', 'AXQ-ZG-344', '3.3.2013.', '8.3.2013.', 'Tvrkta test, test   fsfsdfdsf\ndsf', '22000', '29000', 'Najam', 'Uništeno vjetrobransko staklo', 2000.00),
(4, '2014-03-08 10:32:42', 'AAA-3_B222', '4.05.2013.', '5.6.2013.', 'Test tvrkta d.o.o. Ulica 42\nZagreb, RH', '33000', '34000', 'Reklama (vanredni)', 'Ugovorena reklama na vratima na god dana', 2400.00),
(5, '2014-03-08 10:32:46', 'AAA-3_B222', '19.05.2013', '20.6.2013', '', '500', '1500', '', '', 7800.00),
(6, '2014-03-08 10:32:47', 'AAA-3_B222', '', '', '', '', '', '', '', 0.00),
(7, '2014-03-08 10:34:12', 'AAA-3_B222', '', '', '', '', '', '', '', 0.00),
(8, '2014-03-08 10:34:34', '454545', '455ee', '', '', '', '', '', '', 0.00),
(9, '2014-03-08 10:35:02', 'dsa333', '6.6.2013.', '', '', '', '', '', '', 0.00),
(10, '2014-03-08 10:35:05', 'AXQ-ZG-344', '19.08.2013', '19.09.2013', '', '13300', '15550', 'Najam (vanredni)', '', 1900.00),
(11, '2014-03-08 10:36:39', 'mmmuuu', '', '', '', '', '', '', '', 0.00),
(12, '2014-03-08 10:36:55', 'AAA-3_B222', '1.1.2013.', '', '', '', '', '', 'erewrrewrwer', 300.00),
(13, '2014-03-08 10:41:45', 'AXQ-ZG-344', '4.4.2012.', '5.5.2012.', '', '', '', '', '', 0.00),
(14, '2014-03-08 17:09:13', 'AAA-3_B222', '7.7.2013.', '8.7.2013.', 'Test, test, test\ntest', '33000', '55000', 'Najam vanredni', 'Vanredni najam opis..', 300.00);

-- --------------------------------------------------------

--
-- Tablična struktura za tablicu `troskovi`
--

CREATE TABLE IF NOT EXISTS `troskovi` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kreirano` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `registarska_oznaka` varchar(12) NOT NULL,
  `datum` varchar(10) NOT NULL,
  `do_datuma` varchar(10) NOT NULL,
  `osoba_tvrtka` varchar(255) NOT NULL,
  `km_pocetak` varchar(20) NOT NULL,
  `km_kraj` varchar(20) NOT NULL,
  `vrsta_troskova` varchar(150) NOT NULL,
  `opis` varchar(255) NOT NULL,
  `iznos` double(12,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Izbacivanje podataka za tablicu `troskovi`
--

INSERT INTO `troskovi` (`id`, `kreirano`, `registarska_oznaka`, `datum`, `do_datuma`, `osoba_tvrtka`, `km_pocetak`, `km_kraj`, `vrsta_troskova`, `opis`, `iznos`) VALUES
(1, '2014-03-08 10:45:35', '55-32-ZG24', '2.2.2013.', '3.3.2013.', 'Tvrtka test d.d.\nBranko brani?, Vinogradska 1, Zg', '13000', '', 'Popravak - vanredni trošak', 'Popravak limarije', 6000.00),
(2, '2014-03-08 16:02:04', 'AAA-3_B222', '4.5.2013.', '6.7.2013.', 'Netko netki?, Zg, RH', '23000', '25000', 'Popravak - vanredni', 'Popravak krova', 3450.00);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
