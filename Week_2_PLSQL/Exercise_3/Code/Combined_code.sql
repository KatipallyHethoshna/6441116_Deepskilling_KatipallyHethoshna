/* add a flag so we can tell savings from current accounts */
ALTER TABLE accounts  ADD account_type   VARCHAR2(20);

/* add a department id so we can give department‑wide bonuses */
ALTER TABLE employees ADD department_id  NUMBER;

/* verify names */
DESC accounts;
DESC employees; 

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest IS
BEGIN
    /* One set‑based UPDATE is faster than a row cursor */
    UPDATE accounts
       SET balance = balance * 1.01            
     WHERE account_type = 'SAVINGS';

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        log_error('ProcessMonthlyInterest', SQLCODE, SQLERRM);
        ROLLBACK;
        RAISE;
END ProcessMonthlyInterest;
/
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_dept_id   IN NUMBER,
    p_bonus_pct IN NUMBER     
) IS
BEGIN
    UPDATE employees
       SET salary = salary * (1 + p_bonus_pct/100)
     WHERE department_id = p_dept_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(
            -20010,
            'No employees found for department ' || p_dept_id
        );
    END IF;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        log_error('UpdateEmployeeBonus', SQLCODE, SQLERRM);
        ROLLBACK;
        RAISE;
END UpdateEmployeeBonus;
/

CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_acct IN NUMBER,
    p_to_acct   IN NUMBER,
    p_amount    IN NUMBER
) IS
    v_balance NUMBER;
BEGIN
    -- Lock the source row and read its balance
    SELECT balance
    INTO   v_balance
    FROM   accounts
    WHERE  ACCT_ID = p_from_acct
    FOR UPDATE;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(
            -20020,
            'Insufficient funds in account ' || p_from_acct
        );
    END IF;

    -- Subtract from source
    UPDATE accounts
    SET    balance = balance - p_amount
    WHERE  ACCT_ID = p_from_acct;

    -- Add to destination
    UPDATE accounts
    SET    balance = balance + p_amount
    WHERE  ACCT_ID = p_to_acct;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        log_error('TransferFunds', SQLCODE, SQLERRM);
        ROLLBACK;
        RAISE;
END TransferFunds;
/
SHOW ERRORS PROCEDURE TransferFunds;

BEGIN
    ProcessMonthlyInterest;
END;
/

UPDATE accounts SET account_type = 'SAVINGS' WHERE ACCT_ID IN (101, 202);
COMMIT;

BEGIN
    UpdateEmployeeBonus(1,10);  
END;
/



SELECT ACCT_ID, balance FROM accounts;