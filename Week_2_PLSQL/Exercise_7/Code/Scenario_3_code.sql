BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE accounts PURGE';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN RAISE; END IF;
END;
/

CREATE TABLE accounts (
    account_id   NUMBER PRIMARY KEY,
    customer_id  NUMBER,
    balance      NUMBER(12,2)
);
BEGIN
   EXECUTE IMMEDIATE 'DROP TABLE error_log PURGE';
EXCEPTION
   WHEN OTHERS THEN
      IF SQLCODE != -942 THEN RAISE; END IF;
END;
/

CREATE TABLE error_log (
    log_id      NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    proc_name   VARCHAR2(50),
    err_code    NUMBER,
    err_message VARCHAR2(4000),
    log_ts      TIMESTAMP DEFAULT SYSTIMESTAMP
);

CREATE OR REPLACE PROCEDURE log_error (
    p_proc_name IN VARCHAR2,
    p_err_code  IN NUMBER,
    p_err_msg   IN VARCHAR2
) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
BEGIN
    INSERT INTO error_log (proc_name, err_code, err_message)
    VALUES (p_proc_name, p_err_code, p_err_msg);
    COMMIT;
END log_error;
/
CREATE OR REPLACE PACKAGE AccountOperations IS
    PROCEDURE OpenAccount (
        p_account_id  IN NUMBER,
        p_customer_id IN NUMBER,
        p_balance     IN NUMBER
    );

    PROCEDURE CloseAccount (
        p_account_id IN NUMBER
    );

    FUNCTION GetTotalBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/
CREATE OR REPLACE PACKAGE BODY AccountOperations IS

    PROCEDURE OpenAccount (
        p_account_id  IN NUMBER,
        p_customer_id IN NUMBER,
        p_balance     IN NUMBER
    ) IS
    BEGIN
        INSERT INTO accounts (account_id, customer_id, balance)
        VALUES (p_account_id, p_customer_id, p_balance);
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            log_error('AccountOperations.OpenAccount', SQLCODE, 'Account already exists');
            RAISE_APPLICATION_ERROR(-20800, 'Account ID '||p_account_id||' already exists');
        WHEN OTHERS THEN
            log_error('AccountOperations.OpenAccount', SQLCODE, SQLERRM);
            ROLLBACK; RAISE;
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_account_id IN NUMBER
    ) IS
    BEGIN
        DELETE FROM accounts WHERE account_id = p_account_id;

        IF SQL%ROWCOUNT = 0 THEN
            RAISE_APPLICATION_ERROR(-20801, 'Account not found: ' || p_account_id);
        END IF;

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            log_error('AccountOperations.CloseAccount', SQLCODE, SQLERRM);
            ROLLBACK; RAISE;
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_customer_id IN NUMBER
    ) RETURN NUMBER IS
        v_total NUMBER;
    BEGIN
        SELECT COALESCE(SUM(balance), 0)
        INTO v_total
        FROM accounts
        WHERE customer_id = p_customer_id;
        RETURN v_total;
    EXCEPTION
        WHEN OTHERS THEN
            log_error('AccountOperations.GetTotalBalance', SQLCODE, SQLERRM);
            RETURN NULL;
    END GetTotalBalance;

END AccountOperations;
/
SET SERVEROUTPUT ON;

BEGIN
    -- 1. Open a new account
    AccountOperations.OpenAccount(1001, 501, 25000);

    -- 2. Open another account for same customer
    AccountOperations.OpenAccount(1002, 501, 15000);

    -- 3. Print total balance for customer 501
    DBMS_OUTPUT.PUT_LINE(
        'Total Balance for Customer 501: ₹' ||
        AccountOperations.GetTotalBalance(501)
    );

    -- 4. Close one account
    AccountOperations.CloseAccount(1001);

    -- 5. Print total balance again
    DBMS_OUTPUT.PUT_LINE(
        'Balance after closing 1001: ₹' ||
        AccountOperations.GetTotalBalance(501)
    );
END;
/
-- See accounts
SELECT * FROM accounts;

-- See error logs
SELECT * FROM error_log ORDER BY log_ts DESC;