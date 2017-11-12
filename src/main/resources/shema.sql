CREATE DATABASE gas_coupons CHARACTER SET utf8 COLLATE utf8_general_ci;
USE gas_coupons;
CREATE TABLE product (
  id   INT      NOT NULL AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  UNIQUE (NAME),
  PRIMARY KEY (id)
) ENGINE = InnoDB CHARACTER SET=UTF8;

CREATE TABLE station (
  id   INT      NOT NULL AUTO_INCREMENT,
  NAME VARCHAR(30) NOT NULL,
  UNIQUE (NAME),
  PRIMARY KEY (id)
)ENGINE = InnoDB CHARACTER SET=UTF8;


CREATE TABLE price (
  ID         INT NOT NULL AUTO_INCREMENT,
  STATION_ID INT NOT NULL,
  PRODUCT_ID INT NOT NULL,
  PRICE      DECIMAL(10,2),
  PRIMARY KEY (ID),
  CONSTRAINT FK_PRICE_PROD_1 FOREIGN KEY (PRODUCT_ID)
  REFERENCES product (id),
  CONSTRAINT FK_PRICE_STATION_1 FOREIGN KEY (STATION_ID)
  REFERENCES station (id),
  UNIQUE (PRODUCT_ID, STATION_ID)
)ENGINE = InnoDB CHARACTER SET=UTF8;

CREATE TABLE users(
  ID       INT         NOT NULL AUTO_INCREMENT,
  EMAIL    VARCHAR(30) NOT NULL,
  PASSWORD VARCHAR(80) NOT NULL,
  STATUS TINYINT(1) NOT NULL DEFAULT 0,
  UNIQUE (EMAIL),
  PRIMARY KEY (ID)
)ENGINE = InnoDB CHARACTER SET=UTF8;

CREATE TABLE role (
  ROLE VARCHAR(30) NOT NULL,
  PRIMARY KEY (ROLE)
)ENGINE = InnoDB CHARACTER SET=UTF8;


CREATE TABLE user_role_detail (
  USER_ID INT         NOT NULL,
  ROLE_ID VARCHAR(30) NOT NULL,
  PRIMARY KEY (USER_ID, ROLE_ID),
  CONSTRAINT FK_USER_ROLE_DETAIL_1 FOREIGN KEY (USER_ID)
  REFERENCES users (ID)
    ON DELETE CASCADE,
  CONSTRAINT FK_USER_ROLE_DETAIL_2 FOREIGN KEY (ROLE_ID)
  REFERENCES role (ROLE)
)ENGINE = InnoDB CHARACTER SET=UTF8;

CREATE TABLE basket (
  ID      INT NOT NULL AUTO_INCREMENT,
  USER_ID INT NOT NULL,
  SUMM    INT          DEFAULT 0,
  CONSTRAINT FK_BASKET_USER_1 FOREIGN KEY (USER_ID)
  REFERENCES users (ID)
    ON DELETE CASCADE,
  PRIMARY KEY (ID)
)ENGINE = InnoDB CHARACTER SET=UTF8;

CREATE TABLE basket_item (
  ID         INT NOT NULL AUTO_INCREMENT,
  BASKET_ID  INT NOT NULL,
  PRICE_ID INT NOT NULL,
  COUNT      INT          DEFAULT 0,
  CONSTRAINT FK_BASKET_ITEM_BASKET_1 FOREIGN KEY (BASKET_ID)
  REFERENCES basket(ID)
    ON DELETE CASCADE,
  CONSTRAINT FK_BASKET_ITEM_PRODUCT_1 FOREIGN KEY (PRICE_ID)
  REFERENCES price(ID),
  PRIMARY KEY (ID)
)ENGINE = InnoDB CHARACTER SET=UTF8;