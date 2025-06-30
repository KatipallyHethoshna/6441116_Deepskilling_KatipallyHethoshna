/* ========== PACKAGE SPEC ========== */
CREATE OR REPLACE PACKAGE CustomerManagement IS
    --  put your spec declarations here
    PROCEDURE AddCustomer (
        p_cust_id  IN NUMBER,
        p_name     IN VARCHAR2,
        p_address  IN VARCHAR2,
        p_phone    IN VARCHAR2
    );
    PROCEDURE UpdateCustomer (
        p_cust_id  IN NUMBER,
        p_name     IN VARCHAR2 DEFAULT NULL,
        p_address  IN VARCHAR2 DEFAULT NULL,
        p_phone    IN VARCHAR2 DEFAULT NULL
    );
    FUNCTION GetCustomerBalance (p_cust_id IN NUMBER)
        RETURN NUMBER;
END CustomerManagement;
/
/*  ↑  IMPORTANT: that lone slash compiles the spec  */

/* ========== PACKAGE BODY ========== */
CREATE OR REPLACE PACKAGE BODY CustomerManagement IS
    PROCEDURE AddCustomer (
        p_cust_id  IN NUMBER,
        p_name     IN VARCHAR2,
        p_address  IN VARCHAR2,
        p_phone    IN VARCHAR2
    ) IS
    BEGIN
        INSERT INTO customers
              (customer_id, name, address, phone, lastmodified)
        VALUES (p_cust_id,   p_name, p_address, p_phone, SYSDATE);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            log_error('CustomerManagement.AddCustomer',
                      SQLCODE,'ID exists');
            RAISE_APPLICATION_ERROR(-20600,'Customer exists');
    END AddCustomer;

    PROCEDURE UpdateCustomer (
        p_cust_id  IN NUMBER,
        p_name     IN VARCHAR2 DEFAULT NULL,
        p_address  IN VARCHAR2 DEFAULT NULL,
        p_phone    IN VARCHAR2 DEFAULT NULL
    ) IS
    BEGIN
        UPDATE customers
           SET name         = COALESCE(p_name,    name),
               address      = COALESCE(p_address, address),
               phone        = COALESCE(p_phone,   phone),
               lastmodified = SYSDATE
         WHERE customer_id  = p_cust_id;
        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20601,'Customer not found');
        END IF;
        COMMIT;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance (p_cust_id IN NUMBER)
        RETURN NUMBER
    IS
        v_tot NUMBER;
    BEGIN
        SELECT COALESCE(SUM(balance),0)
          INTO v_tot
          FROM accounts
         WHERE customer_id = p_cust_id;
        RETURN v_tot;
    END GetCustomerBalance;
END CustomerManagement;
/
/*  ↑  another lone slash compiles the body  */

/* ========== QUICK TEST ========== */
SET SERVEROUTPUT ON
BEGIN
    CustomerManagement.AddCustomer(9000,'Test','Nowhere','000');
    DBMS_OUTPUT.PUT_LINE(
        'Balance = '||
        CustomerManagement.GetCustomerBalance(9000)
    );
END;
/

SHOW ERRORS PACKAGE CustomerManagement;
SHOW ERRORS PACKAGE BODY CustomerManagement;

SET SERVEROUTPUT ON;

BEGIN
    -- Add a new customer
    CustomerManagement.AddCustomer(1001, 'Ravi Kumar', 'Hyderabad', '9876543210');

    -- Update the same customer's details
    CustomerManagement.UpdateCustomer(1001, p_address => 'Bangalore');

    -- Get and print total balance for the customer
    DBMS_OUTPUT.PUT_LINE(
        'Total Balance for Customer 1001: ₹' ||
        CustomerManagement.GetCustomerBalance(1001)
    );
END;
/