DROP TABLE IF EXISTS `invoice_set`;
CREATE TABLE  `invoice_set` (
  `PARENT_OR_NO` int(10) unsigned NOT NULL auto_increment,
  `OR_NO` int(10) unsigned NOT NULL,
  `STORE_CODE` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`PARENT_OR_NO`,`OR_NO`)
) ENGINE=InnoDB AUTO_INCREMENT=1860 DEFAULT CHARSET=latin1;