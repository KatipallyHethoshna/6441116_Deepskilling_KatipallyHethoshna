/*——————————————————————————————————————————
  Scenario 1  : SafeTransferFunds
  Purpose     : Transfer money; rollback & log on error
——————————————————————————————————————————*/
-- 1. Shared error_log (create once if missing)
BEGIN
  EXECUTE IMMEDIATE '
    CREATE TABLE error_log (
      log_id         NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
      log_date       DATE            DEFAULT SYSDATE,
      procedure_name VARCHAR2(50),
      err_code       NUMBER,
      err_msg        VARCHAR2(4000)
    )';
EXCEPTION WHEN OTHERS THEN IF SQLCODE != -955 THEN RAISE; END IF; END;
/

-- 2. Demo ACCOUNTS table
BEGIN EXECUTE IMMEDIATE 'DROP TABLE accounts'; EXCEPTION WHEN OTHERS THEN IF SQLCODE != -942 THEN RAISE; END IF; END;
/
CREATE TABLE accounts (
  acct_id  NUMBER PRIMARY KEY,
  balance  NUMBER(12,2)
);
INSERT INTO accounts VALUES (101, 5000);
INSERT INTO accounts VALUES (102, 3000);
COMMIT;

-- 3. Procedure
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
  p_from_acct IN accounts.acct_id%TYPE,
  p_to_acct   IN accounts.acct_id%TYPE,
  p_amount    IN NUMBER
) IS
  v_from_bal NUMBER;
  PRAGMA AUTONOMOUS_TRANSACTION;  -- for logging
BEGIN
  SELECT balance INTO v_from_bal
  FROM accounts
  WHERE acct_id = p_from_acct
  FOR UPDATE;

  IF v_from_bal < p_amount THEN
    RAISE_APPLICATION_ERROR(-20001,'Insufficient funds');
  END IF;

  UPDATE accounts SET balance = balance - p_amount WHERE acct_id = p_from_acct;
  UPDATE accounts SET balance = balance + p_amount WHERE acct_id = p_to_acct;
  COMMIT;
EXCEPTION
  WHEN OTHERS THEN
    ROLLBACK;
    INSERT INTO error_log (procedure_name, err_code, err_msg)
      VALUES ('SafeTransferFunds', SQLCODE, SQLERRM);
    COMMIT;  -- commit the log
    RAISE;
END;
/

-- 4. Quick test
BEGIN
  SafeTransferFunds(101, 102, 2000);  -- success
  SafeTransferFunds(101, 102, 6000);  -- fails, logs
EXCEPTION WHEN OTHERS THEN DBMS_OUTPUT.put_line(SQLERRM); END;
/
SELECT * FROM accounts;
SELECT * FROM error_log ORDER BY log_id;

