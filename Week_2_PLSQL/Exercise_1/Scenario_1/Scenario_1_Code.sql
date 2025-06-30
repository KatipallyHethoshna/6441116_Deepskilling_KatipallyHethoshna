-- Drop if re-running
BEGIN
  EXECUTE IMMEDIATE 'DROP TABLE customers';
EXCEPTION
  WHEN OTHERS THEN NULL;
END;
/

-- Create table
CREATE TABLE customers (
  customer_id NUMBER,
  name VARCHAR2(50),
  age NUMBER,
  loan_interest_rate NUMBER(5,2)
);

-- Insert sample data
INSERT INTO customers VALUES (1, 'Alice', 65, 7.5);
INSERT INTO customers VALUES (2, 'Bob', 45, 6.8);
INSERT INTO customers VALUES (3, 'Charlie', 70, 8.2);

COMMIT;

SET SERVEROUTPUT ON;

BEGIN
  FOR cust IN (SELECT * FROM customers) LOOP
    IF cust.age > 60 THEN
      UPDATE customers
      SET loan_interest_rate = loan_interest_rate - 1
      WHERE customer_id = cust.customer_id;
      
      DBMS_OUTPUT.put_line('Applied discount for: ' || cust.name);
    END IF;
  END LOOP;
  
  COMMIT;
END;
/