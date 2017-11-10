INSERT INTO station (name) VALUES("Neftek");
INSERT INTO station (name) VALUES("Авиас");
INSERT INTO station (name) VALUES("Okko");
INSERT INTO station (name) VALUES("WOG");

INSERT INTO product (name) VALUES("A95");
INSERT INTO product (name) VALUES("A92");
INSERT INTO product (name) VALUES("A95+");
INSERT INTO product (name) VALUES("Дт");
INSERT INTO product (name) VALUES("Дт+");
INSERT INTO product (name) VALUES("Газ");

INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(1,1,27.99);
INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(1,2,26.99);
INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(1,3,25.99);

INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(2,1,25.95);
INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(2,2,25.45);
INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(2,3,24.00);

INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(3,1,28.99);
INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(3,2,27.99);
INSERT INTO price (STATION_ID,PRODUCT_ID,price) VALUES(3,3,26.49);

INSERT INTO role(ROLE)
VALUES ("ROLE_ADMIN");
INSERT INTO role(ROLE)
VALUES ("ROLE_USER");
INSERT INTO role(ROLE)
VALUES ("ROLE_GUEST");


INSERT INTO users(EMAIL,PASSWORD,STATUS)
VALUES ("admin@admin.ua","admin",1);

INSERT INTO user_role_detail(USER_ID,ROLE_ID)
VALUES (1,"ROLE_ADMIN")