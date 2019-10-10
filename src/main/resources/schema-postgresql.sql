DROP TABLE IF EXISTS customer;

CREATE TABLE  customer (
  customerId smallint  PRIMARY KEY NOT NULL,
  NAME varchar(100) NOT NULL,
  AGE smallint NOT NULL
);
