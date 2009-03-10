INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 16,0,1,0,0,1,0,0,15.0,str_to_date('2008-09-25 13:50:54','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("16", "023800010154", "1", "3", "0", "45.0", "24.83", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 3 WHERE PROD_CODE = "023800010154" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("16", "1", "135.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO pull_out_requests (`STO_TO_CODE`,`ISSUE_CLERK`,`PO_REASON_CODE`,`REQUEST_DATE`) VALUES ( 001,1,0000000001,str_to_date('2008-09-25 13:51:38','%Y-%m-%d %H:%i:%S'));
INSERT INTO `pull_out_request_items` (REQUEST_NO, STORE_CODE, PROD_CODE, QUANTITY) VALUES ("3", "001", "023800015081", "2");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 17,0,1,0,0,1,0,0,800.1,str_to_date('2008-10-06 12:15:48','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("17", "077539165128", "1", "2", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("17", "1", "1199.9", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 18,0,1,0,0,1,0,0,7.0,str_to_date('2008-10-06 12:18:50','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("18", "0070-3", "1", "2", "0", "196.5", "127.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "0070-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("18", "1", "393.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 19,0,1,0,0,1,0,0,380.0,str_to_date('2008-10-06 12:20:18','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("19", "015-3", "4", "2", "0", "120.0", "78.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "015-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("19", "1", "120.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 20,0,1,0,0,1,0,0,669.1,str_to_date('2008-10-06 12:24:25','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("20", "077539165128", "1", "2", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("20", "0070", "1", "2", "0", "65.5", "42.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "0070" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("20", "1", "1330.9", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 21,0,1,0,0,1,0,0,619.5,str_to_date('2008-10-06 12:40:55','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("21", "0072-3", "1", "2", "0", "140.0", "91.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "0072-3" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("21", "0070", "1", "2", "0", "65.5", "42.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "0070" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("21", "511606", "1", "2", "0", "295.0", "0.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "511606" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("21", "4710482005054", "1", "2", "0", "189.75", "135.2", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "4710482005054" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("21", "1", "1380.5", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 22,0,1,0,0,1,0,0,795.0,str_to_date('2008-10-06 13:12:45','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("22", "511606", "1", "99", "0", "295.0", "0.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 99 WHERE PROD_CODE = "511606" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("22", "1", "29205.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 23,0,1,0,0,1,0,0,276.5,str_to_date('2008-10-06 13:13:35','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("23", "0025-3", "1", "9", "0", "191.5", "124.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 9 WHERE PROD_CODE = "0025-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("23", "1", "1723.5", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 24,0,1,0,0,1,0,0,0.0,str_to_date('2008-10-06 13:15:23','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("24", "0072-3", "1", "1", "0", "140.0", "91.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0072-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("24", "1", "140.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 25,0,1,0,0,1,0,0,6.5,str_to_date('2008-10-06 13:17:14','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("25", "0015-3", "1", "1", "0", "143.5", "90.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0015-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("25", "1", "143.5", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 26,0,1,0,0,1,0,0,20.1,str_to_date('2008-10-06 13:17:41','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("26", "015A", "1", "2", "0", "39.95", "26.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "015A" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("26", "1", "79.9", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 27,0,1,0,0,1,0,0,0.0,str_to_date('2008-10-06 13:18:40','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("27", "0072-3", "1", "1", "0", "140.0", "91.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0072-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("27", "1", "140.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 28,0,1,0,0,1,0,0,605.0,str_to_date('2008-10-06 13:23:11','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("28", "48.82052181004220", "1", "20", "0", "69.75", "48.82", "1");
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("28", "1", "1395.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 29,0,1,0,0,1,0,0,0.0,str_to_date('2008-10-06 13:25:07','%Y-%m-%d %H:%i:%S'));
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 30,0,1,0,0,1,0,0,105.0,str_to_date('2008-10-06 13:30:33','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("30", "48.82052181004220", "1", "20", "0", "69.75", "48.82", "1");
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("30", "1", "1395.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 31,0,1,0,0,1,0,0,0.0,str_to_date('2008-10-06 13:32:18','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("31", "032-3", "1", "2", "0", "166.5", "108.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "032-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("31", "1", "333.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 32,0,1,0,0,1,0,0,2.5,str_to_date('2008-10-06 17:23:53','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("32", "511606", "4", "1", "0", "295.0", "0.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "511606" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("32", "1", "147.5", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 33,0,1,0,0,1,0,0,36.77,str_to_date('2008-10-06 17:36:34','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("33", "077539165128", "4", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("33", "0019-6", "4", "1", "0", "326.5", "212.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0019-6" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("33", "1", "463.23", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 34,0,1,0,0,1,0,0,0.05,str_to_date('2008-10-06 17:37:31','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("34", "077539165128", "1", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("34", "1", "599.95", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 35,0,1,0,0,1,0,1,182.45,str_to_date('2008-10-06 17:38:09','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("35", "077539165128", "1", "1", "389.97", "599.95", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("35", "0015-3", "1", "5", "0", "143.5", "90.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 5 WHERE PROD_CODE = "0015-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("35", "1", "117.55", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 36,0,1,0,0,1,0,1,16.75,str_to_date('2008-10-06 17:38:54','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("36", "0019-6", "1", "1", "212.0", "163.25", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "0019-6" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("36", "0070-3", "1", "1", "0", "196.5", "127.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0070-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("36", "1", "33.25", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 37,0,1,0,0,1,0,0,1.75,str_to_date('2008-10-06 18:03:35','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("37", "0070-3", "4", "1", "0", "196.5", "127.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0070-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("37", "1", "98.25", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 38,0,1,0,0,1,0,0,60.02,str_to_date('2008-10-06 18:22:29','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("38", "077539165128", "4", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("38", "0072-3", "1", "1", "0", "140.0", "91.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0072-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("38", "1", "439.98", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 39,0,1,0,0,1,0,0,60.02,str_to_date('2008-10-06 18:23:44','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("39", "077539165128", "4", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("39", "0072-3", "1", "1", "0", "140.0", "91.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0072-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("39", "1", "439.98", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 40,0,1,0,0,1,0,1,23.47,str_to_date('2008-10-06 18:24:29','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("40", "077539165128", "1", "1", "389.97", "299.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("40", "0019-6", "1", "1", "0", "326.5", "212.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0019-6" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("40", "1", "26.53", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 41,0,1,0,0,1,0,1,23.48,str_to_date('2008-10-06 18:33:58','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("41", "077539165128", "1", "1", "389.97", "299.98", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("41", "0019-6", "1", "1", "0", "326.5", "212.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0019-6" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("41", "1", "26.52", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 42,0,1,0,0,1,0,1,80.01,str_to_date('2008-10-06 18:34:49','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("42", "077539165128", "1", "1", "389.97", "299.98", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("42", "077539165128", "7", "1", "0", "419.97", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("42", "1", "119.99", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 43,0,1,0,0,1,0,1,80.01,str_to_date('2008-10-06 18:41:49','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("43", "077539165128", "1", "1", "389.97", "299.98", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("43", "077539165128", "7", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("43", "1", "119.99", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 44,0,1,0,0,1,0,1,80.01,str_to_date('2008-10-06 18:43:20','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("44", "077539165128", "1", "1", "389.97", "299.98", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("44", "077539165128", "7", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("44", "1", "119.99", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 45,0,1,0,0,1,0,0,64.0,str_to_date('2008-10-06 19:03:07','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("45", "511606", "5", "1", "0", "295.0", "0.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "511606" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("45", "1", "236.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 46,0,1,0,0,1,0,1,3.48,str_to_date('2008-10-07 17:34:14','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("46", "077539165128", "1", "1", "389.97", "299.98", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("46", "0019-6", "1", "1", "0", "326.5", "212.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "0019-6" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("46", "1", "26.52", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 47,0,1,0,0,1,0,0,250.05,str_to_date('2008-10-08 08:46:23','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("47", "077539165128", "1", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("47", "511606", "1", "2", "0", "295.0", "0.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "511606" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("47", "015-3", "4", "1", "0", "120.0", "78.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "015-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("47", "1", "1249.95", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 48,0,1,0,0,1,0,1,0.0,str_to_date('2008-10-08 08:49:42','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("48", "077539165128", "1", "1", "389.97", "599.95", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("48", "027467050249", "1", "6", "0", "110.0", "71.5", "1");
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("48", "1", "60.05", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 49,0,1,0,0,1,0,0,60.0,str_to_date('2008-10-20 11:36:02','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("49", "015-3", "1", "2", "0", "120.0", "78.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "015-3" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("49", "1", "240.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 50,0,1,0,0,1,0,1,45.0,str_to_date('2008-10-20 11:37:55','%Y-%m-%d %H:%i:%S'));
INSERT INTO `returned_items` (OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("50", "015-3", "1", "2", "78.0", "120.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY + 2 WHERE PROD_CODE = "015-3" AND STORE_CODE = 001;
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("50", "36706", "1", "1", "0", "495.0", "321.75", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "36706" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("50", "1", "255.0", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 2819,0,1,0,0,1,0,0,0.05,str_to_date('2008-11-04 13:22:22','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("2819", "077539165128", "1", "1", "0", "599.95", "389.97", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "077539165128" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("2819", "1", "599.95", "N/A", "N/A", "N/A", "N/A", "1");
UPDATE `delivery_items` SET PROCESSED_STAT="1", RCVD_DATE="2008-11-04 00:00:00", ACCEPTED_QTY="5", MISSING_QTY="5", DAMAGED_QTY="0" WHERE DEL_ITEM_NO="3026";
UPDATE inventory_lu SET QUANTITY = QUANTITY + 5 WHERE PROD_CODE = "FP2" AND STORE_CODE = 001;
UPDATE `delivery_items` SET PROCESSED_STAT="1", RCVD_DATE="2008-11-04 00:00:00", ACCEPTED_QTY="6", MISSING_QTY="4", DAMAGED_QTY="2" WHERE DEL_ITEM_NO="3027";
UPDATE inventory_lu SET QUANTITY = QUANTITY + 6 WHERE PROD_CODE = "CP8" AND STORE_CODE = 001;
UPDATE `deliveries` SET STATUS="complete" WHERE DEL_NO="798" AND STO_TO_CODE="001";
INSERT INTO invoice (`OR_NO`,`INVOICE_NO`,`ENCODER_CODE`,`ASSIST_CODE`,`CUST_NO`,`STORE_CODE`,`PARTIAL`,`RETURN`,`CHANGE_AMOUNT`,`TRANS_DT`) VALUES ( 2820,0,1,0,0,1,0,0,10.0,str_to_date('2008-11-04 16:18:15','%Y-%m-%d %H:%i:%S'));
INSERT INTO `invoice_item` (OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("2820", "511606", "1", "2", "0", "295.0", "0.0", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "511606" AND STORE_CODE = 001;
INSERT INTO `payment_item` (OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("2820", "1", "590.0", "N/A", "N/A", "N/A", "N/A", "1");