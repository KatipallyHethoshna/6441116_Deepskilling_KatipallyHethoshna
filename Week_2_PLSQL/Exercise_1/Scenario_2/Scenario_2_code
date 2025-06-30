BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE customers_vip';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

CREATE TABLE customers_vip (
  customer_id NUMBER,
  name VARCHAR2(50),
  balance NUMBER(10,2),
  is_vip VARCHAR2(5) DEFAULT 'FALSE'
);

INSERT INTO customers_vip VALUES (1, 'John', 8000, 'FALSE');
INSERT INTO customers_vip VALUES (2, 'Sita', 15000, 'FALSE');
INSERT INTO customers_vip VALUES (3, 'Ravi', 12000, 'FALSE');

COMMIT;
