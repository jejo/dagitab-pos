DROP TABLE IF EXISTS `babyland`.`robinsons_compliance`;
CREATE TABLE  `babyland`.`robinsons_compliance` (
  `TRANS_NO` int(10) unsigned zerofill NOT NULL auto_increment,
  `FILENAME` varchar(40) NOT NULL,
  `REPORT_DATE` date NOT NULL,
  `EOD_COUNTER` int(10) default '0',
  PRIMARY KEY  (`TRANS_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=87 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `babyland`.`festival_compliance`;
CREATE TABLE  `babyland`.`festival_compliance` (
  `TRANS_NO` int(10) unsigned zerofill NOT NULL auto_increment,
  `FILENAME` varchar(40) NOT NULL,
  `REPORT_DATE` date NOT NULL,
  `EOD_COUNTER` int(10) default '0',
  `MODE` varchar(3) default NULL,
  PRIMARY KEY  (`TRANS_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `babyland`.`eod_log`;
CREATE TABLE  `babyland`.`eod_log` (
  `TRANS_NO` int(10) unsigned zerofill NOT NULL auto_increment,
  `TRANS_DATE` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `EOD_TIME` timestamp NOT NULL default '0000-00-00 00:00:00',
  `IS_SENT` varchar(3) default NULL,
  PRIMARY KEY  (`TRANS_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=134 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `babyland`.`eastwood_compliance`;
CREATE TABLE  `babyland`.`eastwood_compliance` (
  `TRANS_NO` int(10) unsigned zerofill NOT NULL auto_increment,
  `FILENAME` varchar(40) NOT NULL,
  `REPORT_DATE` date NOT NULL,
  `EOD_COUNTER` int(10) default '0',
  `MODE` varchar(3) default NULL,
  PRIMARY KEY  (`TRANS_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=107 DEFAULT CHARSET=latin1;