-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dic 19, 2015 alle 18:03
-- Versione del server: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `campi_yellow`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `amministratore`
--

CREATE TABLE IF NOT EXISTS `amministratore` (
  `ID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `calendario`
--

CREATE TABLE IF NOT EXISTS `calendario` (
`ID` int(255) NOT NULL,
  `Fascia` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `campo`
--

CREATE TABLE IF NOT EXISTS `campo` (
`ID` int(255) NOT NULL,
  `Tipo_campo` text
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `classifica`
--

CREATE TABLE IF NOT EXISTS `classifica` (
`ID` int(255) NOT NULL,
  `Punti` int(255) DEFAULT NULL,
  `Goal_fatti` int(255) DEFAULT NULL,
  `Goal_subiti` int(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `credenziali`
--

CREATE TABLE IF NOT EXISTS `credenziali` (
`ID` int(255) NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Avviso` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `detiene`
--

CREATE TABLE IF NOT EXISTS `detiene` (
  `Squadra` int(255) NOT NULL DEFAULT '0',
  `Giocatore` int(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `giocatore`
--

CREATE TABLE IF NOT EXISTS `giocatore` (
  `ID` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `giornata`
--

CREATE TABLE IF NOT EXISTS `giornata` (
`ID` int(255) NOT NULL,
  `Data_giornata` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `iscritta`
--

CREATE TABLE IF NOT EXISTS `iscritta` (
  `Squadra` int(255) NOT NULL DEFAULT '0',
  `Torneo` int(255) NOT NULL DEFAULT '0',
  `Classifica` int(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `notifica`
--

CREATE TABLE IF NOT EXISTS `notifica` (
`ID` int(255) NOT NULL,
  `Utente` int(255) DEFAULT NULL,
  `Squadra` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `partita`
--

CREATE TABLE IF NOT EXISTS `partita` (
`ID` int(255) NOT NULL,
  `Campo` int(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `partita_torneo`
--

CREATE TABLE IF NOT EXISTS `partita_torneo` (
  `ID` int(255) NOT NULL,
  `Tabellone` int(255) DEFAULT NULL,
  `Goal_casa` int(255) DEFAULT NULL,
  `Goal_trasferta` int(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `possiede`
--

CREATE TABLE IF NOT EXISTS `possiede` (
  `Giocatore` int(255) NOT NULL DEFAULT '0',
  `Statistiche` int(255) NOT NULL DEFAULT '0',
  `Partita` int(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `prenota`
--

CREATE TABLE IF NOT EXISTS `prenota` (
  `Utente` int(255) NOT NULL DEFAULT '0',
  `Campo` int(255) NOT NULL DEFAULT '0',
  `Giornata` int(255) NOT NULL DEFAULT '0',
  `Calendario` int(255) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `squadra`
--

CREATE TABLE IF NOT EXISTS `squadra` (
`ID` int(255) NOT NULL,
  `Nome` varchar(255) DEFAULT NULL,
  `Capitano` varchar(255) DEFAULT NULL,
  `Colore_divisa` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `statistiche`
--

CREATE TABLE IF NOT EXISTS `statistiche` (
`ID` int(255) NOT NULL,
  `Goal_fatti` int(255) DEFAULT NULL,
  `Goal_subiti` int(255) DEFAULT NULL,
  `Cart_gialli` int(255) DEFAULT NULL,
  `Cart_rossi` int(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `tabellone`
--

CREATE TABLE IF NOT EXISTS `tabellone` (
`ID` int(255) NOT NULL,
  `Casa` int(255) DEFAULT NULL,
  `Trasferta` int(255) DEFAULT NULL,
  `Torneo` int(255) DEFAULT NULL,
  `Data_partita` date DEFAULT NULL,
  `Ris` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `tipo_torneo`
--

CREATE TABLE IF NOT EXISTS `tipo_torneo` (
`ID` int(255) NOT NULL,
  `Nome` varchar(255) DEFAULT NULL,
  `Descrizione_tipo` text
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `torneo`
--

CREATE TABLE IF NOT EXISTS `torneo` (
`ID` int(255) NOT NULL,
  `Nome` varchar(255) DEFAULT NULL,
  `Descrizione` text,
  `Data_inizio` date DEFAULT NULL,
  `Tipo` int(255) DEFAULT NULL,
  `stato` tinyint(1) DEFAULT NULL,
  `Iscrizioni` tinyint(1) DEFAULT NULL,
  `Massimo_iscrizioni` int(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struttura della tabella `utente`
--

CREATE TABLE IF NOT EXISTS `utente` (
  `ID` int(255) NOT NULL,
  `Nome` varchar(255) DEFAULT NULL,
  `Cognome` varchar(255) DEFAULT NULL,
  `E_mail` varchar(255) DEFAULT NULL,
  `Data_di_nascita` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `amministratore`
--
ALTER TABLE `amministratore`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `calendario`
--
ALTER TABLE `calendario`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `campo`
--
ALTER TABLE `campo`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `classifica`
--
ALTER TABLE `classifica`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `credenziali`
--
ALTER TABLE `credenziali`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `detiene`
--
ALTER TABLE `detiene`
 ADD PRIMARY KEY (`Giocatore`,`Squadra`), ADD KEY `Squadra` (`Squadra`);

--
-- Indexes for table `giocatore`
--
ALTER TABLE `giocatore`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `giornata`
--
ALTER TABLE `giornata`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `iscritta`
--
ALTER TABLE `iscritta`
 ADD PRIMARY KEY (`Squadra`,`Torneo`,`Classifica`), ADD KEY `Torneo` (`Torneo`), ADD KEY `Classifica` (`Classifica`);

--
-- Indexes for table `notifica`
--
ALTER TABLE `notifica`
 ADD PRIMARY KEY (`ID`), ADD KEY `Utente` (`Utente`);

--
-- Indexes for table `partita`
--
ALTER TABLE `partita`
 ADD PRIMARY KEY (`ID`), ADD KEY `Campo` (`Campo`);

--
-- Indexes for table `partita_torneo`
--
ALTER TABLE `partita_torneo`
 ADD PRIMARY KEY (`ID`), ADD KEY `Tabellone` (`Tabellone`);

--
-- Indexes for table `possiede`
--
ALTER TABLE `possiede`
 ADD PRIMARY KEY (`Giocatore`,`Statistiche`,`Partita`), ADD KEY `Statistiche` (`Statistiche`), ADD KEY `Partita` (`Partita`);

--
-- Indexes for table `prenota`
--
ALTER TABLE `prenota`
 ADD PRIMARY KEY (`Utente`,`Campo`,`Giornata`,`Calendario`), ADD KEY `Campo` (`Campo`), ADD KEY `Giornata` (`Giornata`), ADD KEY `calendario` (`Calendario`);

--
-- Indexes for table `squadra`
--
ALTER TABLE `squadra`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `statistiche`
--
ALTER TABLE `statistiche`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `tabellone`
--
ALTER TABLE `tabellone`
 ADD PRIMARY KEY (`ID`), ADD KEY `Torneo` (`Torneo`), ADD KEY `Trasferta` (`Trasferta`), ADD KEY `Casa` (`Casa`,`Trasferta`);

--
-- Indexes for table `tipo_torneo`
--
ALTER TABLE `tipo_torneo`
 ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `torneo`
--
ALTER TABLE `torneo`
 ADD PRIMARY KEY (`ID`), ADD KEY `Tipo` (`Tipo`);

--
-- Indexes for table `utente`
--
ALTER TABLE `utente`
 ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `calendario`
--
ALTER TABLE `calendario`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `campo`
--
ALTER TABLE `campo`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `classifica`
--
ALTER TABLE `classifica`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `credenziali`
--
ALTER TABLE `credenziali`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `giornata`
--
ALTER TABLE `giornata`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT for table `notifica`
--
ALTER TABLE `notifica`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `partita`
--
ALTER TABLE `partita`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `squadra`
--
ALTER TABLE `squadra`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=54;
--
-- AUTO_INCREMENT for table `statistiche`
--
ALTER TABLE `statistiche`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=38;
--
-- AUTO_INCREMENT for table `tabellone`
--
ALTER TABLE `tabellone`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=37;
--
-- AUTO_INCREMENT for table `tipo_torneo`
--
ALTER TABLE `tipo_torneo`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `torneo`
--
ALTER TABLE `torneo`
MODIFY `ID` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `amministratore`
--
ALTER TABLE `amministratore`
ADD CONSTRAINT `amministratore_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `detiene`
--
ALTER TABLE `detiene`
ADD CONSTRAINT `detiene_ibfk_1` FOREIGN KEY (`Squadra`) REFERENCES `squadra` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `detiene_ibfk_2` FOREIGN KEY (`Giocatore`) REFERENCES `giocatore` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `giocatore`
--
ALTER TABLE `giocatore`
ADD CONSTRAINT `giocatore_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `iscritta`
--
ALTER TABLE `iscritta`
ADD CONSTRAINT `iscritta_ibfk_1` FOREIGN KEY (`Squadra`) REFERENCES `squadra` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `iscritta_ibfk_2` FOREIGN KEY (`Torneo`) REFERENCES `torneo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `iscritta_ibfk_3` FOREIGN KEY (`Classifica`) REFERENCES `classifica` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `notifica`
--
ALTER TABLE `notifica`
ADD CONSTRAINT `notifica_ibfk_1` FOREIGN KEY (`Utente`) REFERENCES `credenziali` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `partita`
--
ALTER TABLE `partita`
ADD CONSTRAINT `partita_ibfk_1` FOREIGN KEY (`Campo`) REFERENCES `campo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `partita_torneo`
--
ALTER TABLE `partita_torneo`
ADD CONSTRAINT `partita_torneo_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `partita` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `partita_torneo_ibfk_2` FOREIGN KEY (`Tabellone`) REFERENCES `tabellone` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `possiede`
--
ALTER TABLE `possiede`
ADD CONSTRAINT `possiede_ibfk_1` FOREIGN KEY (`Giocatore`) REFERENCES `giocatore` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `possiede_ibfk_2` FOREIGN KEY (`Statistiche`) REFERENCES `statistiche` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `possiede_ibfk_3` FOREIGN KEY (`Partita`) REFERENCES `partita` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `prenota`
--
ALTER TABLE `prenota`
ADD CONSTRAINT `prenota_ibfk_1` FOREIGN KEY (`Utente`) REFERENCES `utente` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `prenota_ibfk_2` FOREIGN KEY (`Campo`) REFERENCES `campo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `prenota_ibfk_3` FOREIGN KEY (`Giornata`) REFERENCES `giornata` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `prenota_ibfk_4` FOREIGN KEY (`Calendario`) REFERENCES `calendario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `tabellone`
--
ALTER TABLE `tabellone`
ADD CONSTRAINT `tabellone_ibfk_1` FOREIGN KEY (`Torneo`) REFERENCES `torneo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `tabellone_ibfk_2` FOREIGN KEY (`Casa`) REFERENCES `squadra` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `tabellone_ibfk_3` FOREIGN KEY (`Trasferta`) REFERENCES `squadra` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `torneo`
--
ALTER TABLE `torneo`
ADD CONSTRAINT `torneo_ibfk_1` FOREIGN KEY (`Tipo`) REFERENCES `tipo_torneo` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `utente`
--
ALTER TABLE `utente`
ADD CONSTRAINT `utente_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `credenziali` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
