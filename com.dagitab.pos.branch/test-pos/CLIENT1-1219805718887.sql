INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL) VALUES ("45", "0", "1", "0", "0", "1", "0");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("45", "018068116670", "1", "1", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("46", "018068116670", "1", "1", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("46", "023800010154", "4", "1", "0", "45.0", "24.83", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "023800010154" AND STORE_CODE = 001;
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("47", "0", "1", "0", "0", "1", "0", "0");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("47", "018068116670", "1", "1", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("47", "023800010154", "4", "1", "0", "45.0", "24.83", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "023800010154" AND STORE_CODE = 001;
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("48", "0", "1", "0", "0", "1", "0", "1");
INSERT INTO returned_items(OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("48", "018068116670", "1", "1", "84.47", "129.95", "1");
INSERT INTO returned_items(OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("48", "023800010154", "1", "1", "24.83", "22.5", "1");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("48", "027467050256", "1", "2", "0", "110.0", "71.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "027467050256" AND STORE_CODE = 001;
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("49", "0", "1", "0", "0", "1", "0", "0");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("49", "018068116670", "1", "2", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("50", "0", "1", "0", "0", "1", "0", "0");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("50", "018068116670", "1", "1", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("51", "0", "1", "0", "0", "1", "0", "0");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("51", "018068116670", "1", "1", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("52", "0", "1", "0", "0", "1", "0", "0");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("52", "018068116670", "1", "1", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO payment_item(OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("52", "1", "129.95", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("1", "0", "1", "0", "0", "1", "0", "0");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("1", "018068116670", "1", "1", "0", "129.95", "84.47", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "018068116670" AND STORE_CODE = 001;
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("1", "023800010154", "4", "1", "0", "45.0", "24.83", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 1 WHERE PROD_CODE = "023800010154" AND STORE_CODE = 001;
INSERT INTO payment_item(OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("1", "1", "152.45", "N/A", "N/A", "N/A", "N/A", "1");
INSERT INTO invoice(OR_NO, INVOICE_NO, ENCODER_CODE, ASSIST_CODE, CUST_NO, STORE_CODE, PARTIAL, `RETURN`) VALUES ("2", "0", "1", "0", "0", "1", "0", "1");
INSERT INTO returned_items(OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("2", "018068116670", "1", "1", "84.47", "129.95", "1");
INSERT INTO returned_items(OR_NO, PROD_CODE, RETURN_CODE, QUANTITY, COST, SELL_PRICE, STORE_CODE) VALUES ("2", "023800010154", "1", "1", "24.83", "22.5", "1");
INSERT INTO invoice_item(OR_NO, PROD_CODE, DISC_CODE, QUANTITY, DEFERRED, SELL_PRICE, COST, STORE_CODE) VALUES ("2", "027467050256", "1", "2", "0", "110.0", "71.5", "1");
UPDATE inventory_lu SET QUANTITY = QUANTITY - 2 WHERE PROD_CODE = "027467050256" AND STORE_CODE = 001;
INSERT INTO payment_item(OR_NO, PT_CODE, AMT, CARD_TYPE, CARD_NO, GC_NO, CHECK_NO, STORE_CODE) VALUES ("2", "1", "67.55", "N/A", "N/A", "N/A", "N/A", "1");