-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Янв 24 2018 г., 15:47
-- Версия сервера: 10.1.29-MariaDB
-- Версия PHP: 7.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `hospital`
--

-- --------------------------------------------------------
DROP TABLE admins;
DROP TABLE nurses;
DROP TABLE prescriptions;
DROP TABLE cards;
DROP TABLE doctors;
DROP TABLE patients;
DROP TABLE categories;
DROP TABLE roles;
--
-- Структура таблицы `admins`
--

CREATE TABLE `admins` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `admins`
--

INSERT INTO `admins` (`id`, `name`, `surname`, `login`, `password`, `id_role`) VALUES
(0, 'Pavlo', 'Hrynevych', 'admin', 'carambica', 0);

-- --------------------------------------------------------
--
-- Структура таблицы `cards`
--

CREATE TABLE `cards` (
  `id` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `id_doctor` int(11) NOT NULL,
  `diagnosis` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `cards`
--

INSERT INTO `cards` (`id`, `id_patient`, `id_doctor`, `diagnosis`) VALUES
(0, 0, 0, 'Caries.'),
(1, 1, 1, 'Pulpitis.'),
(2, 2, 2, 'Multiple fractures.'),
(3, 0, 2, 'Vertebral displacement.');

-- --------------------------------------------------------

--
-- Структура таблицы `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `categories`
--

INSERT INTO `categories` (`id`, `name`) VALUES
(11, 'cardiologist'),
(4, 'dentist'),
(9, 'dermatologist'),
(10, 'gastroenterologist'),
(7, 'gynecologist'),
(5, 'ophthalmologist'),
(12, 'orthopedist'),
(6, 'otolaryngologist'),
(0, 'pediatrician'),
(8, 'psychiatrist'),
(13, 'rheumatologist'),
(2, 'surgeon'),
(3, 'therapeutist'),
(1, 'traumatologist');

-- --------------------------------------------------------

--
-- Структура таблицы `doctors`
--

CREATE TABLE `doctors` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `id_role` int(11) NOT NULL,
  `id_category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `doctors`
--

INSERT INTO `doctors` (`id`, `name`, `surname`, `date_of_birth`, `phone_number`, `email`, `login`, `password`, `id_role`, `id_category`) VALUES
(0, 'Inna', 'Solodova', '1970-07-02', '099-999-99-46', 'solod@gmail.com', 'solodinna', 'goodoctor', 2, 4),
(1, 'Alexandra', 'Melnikova', '1963-07-09', '099-000-23-34', NULL, 'melnik1963', 'melnikmelnik', 2, 4),
(2, 'Sergey', 'Potapov', '1980-12-21', '067-312-56-34', 'potap.doctor@gmail.com', 'kotelok1', 'kotelok2', 2, 2),
(3, 'Kamila', 'Adonkina', '1980-12-01', '066-274-21-00', 'one.more.user197538@gmail.com', 'kadon', 'donkeyhot', 2, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `nurses`
--

CREATE TABLE `nurses` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `nurses`
--

INSERT INTO `nurses` (`id`, `name`, `surname`, `date_of_birth`, `phone_number`, `email`, `login`, `password`, `id_role`) VALUES
(0, 'Olena', 'Petrenko', '1990-02-01', '099-777-55-39', 'olena1990@gmail.com', 'petrol', 'casatic', 3);

-- --------------------------------------------------------

--
-- Структура таблицы `patients`
--

CREATE TABLE `patients` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `date_of_birth` date NOT NULL,
  `sex` varchar(1) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `patients`
--

INSERT INTO `patients` (`id`, `name`, `surname`, `date_of_birth`, `sex`, `address`, `phone_number`, `email`, `login`, `password`, `id_role`) VALUES
(0, 'Ivan', 'Ivanov', '1963-11-09', 'M', 'Kharkiv, Bavarska st. 18, ap. 4', '8-800-555-35-35', 'ivan_mail@mail.ru', 'ivanov1963', 'cucumber', 1),
(1, 'Oleg', 'Babaskin', '1997-01-01', 'M', 'Kharkiv, Bavarska st. 2, ap. 14', '097-21-21-273', 'qwerty12345@gmail.com', 'babaska', 'qwerty', 1),
(2, 'Anna', 'Karenina', '1980-12-07', 'F', 'Kharkiv, Alchevskyh st. 21, ap. 1', '093-777-21-77', 'train_lover@mail.ru', 'karenina', '1234', 1);

-- --------------------------------------------------------

--
-- Структура таблицы `prescriptions`
--

CREATE TABLE `prescriptions` (
  `id` int(11) NOT NULL,
  `procedures` varchar(200) DEFAULT NULL,
  `proc_status` tinyint(1) DEFAULT NULL,
  `medicines` varchar(200) DEFAULT NULL,
  `med_status` tinyint(1) DEFAULT NULL,
  `operations` varchar(200) DEFAULT NULL,
  `op_status` tinyint(1) DEFAULT NULL,
  `id_card` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `prescriptions`
--

INSERT INTO `prescriptions` (`id`, `procedures`, `proc_status`, `medicines`, `med_status`, `operations`, `op_status`, `id_card`) VALUES
(0, '1) Rinse the oral cavity with \"SomeMedicine\".', 0, '1) \"SomeMedicine\"', 0, NULL, NULL, 0),
(1, '1) Rinse the oral cavity with \"Medicine-34\".', 0, '1) \"Medicine-34\"', 0, NULL, NULL, 1),
(2, NULL, NULL, NULL, NULL, 'All known.', 0, 2),
(3, NULL, NULL, '1) Ointment \"UnHurt\"', 0, NULL, NULL, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Дамп данных таблицы `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(0, 'admin'),
(2, 'doctor'),
(3, 'nurse'),
(1, 'patient');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);
  
--
-- Индексы таблицы `cards`
--
ALTER TABLE `cards`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Индексы таблицы `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Индексы таблицы `nurses`
--
ALTER TABLE `nurses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Индексы таблицы `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `login` (`login`);

--
-- Индексы таблицы `prescriptions`
--
ALTER TABLE `prescriptions`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

ALTER TABLE admins
  ADD CONSTRAINT fk_adm_role FOREIGN KEY (id_role) REFERENCES roles(id);

ALTER TABLE patients
  ADD CONSTRAINT fk_pat_role FOREIGN KEY (id_role) REFERENCES roles(id);

ALTER TABLE doctors
  ADD CONSTRAINT fk_doc_role FOREIGN KEY (id_role) REFERENCES roles(id);

ALTER TABLE nurses
  ADD CONSTRAINT fk_nur_role FOREIGN KEY (id_role) REFERENCES roles(id);
  
ALTER TABLE doctors
  ADD CONSTRAINT fk_doc_cat FOREIGN KEY (id_category) REFERENCES categories(id);
  
ALTER TABLE prescriptions
  ADD CONSTRAINT fk_pres_card FOREIGN KEY (id_card) REFERENCES cards(id);
  
ALTER TABLE cards
  ADD CONSTRAINT fk_card_pat FOREIGN KEY (id_patient) REFERENCES patients(id);
  
ALTER TABLE cards
  ADD CONSTRAINT fk_card_doc FOREIGN KEY (id_doctor) REFERENCES doctors(id);
  
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
