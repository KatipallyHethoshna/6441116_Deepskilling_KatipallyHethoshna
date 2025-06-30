SET SERVEROUTPUT ON;


/*
   SCENARIO 1 ― GenerateMonthlyStatements
   ▸ Prints every customer’s transactions for the current month
*/
DECLARE
    CURSOR c_txn IS
        SELECT c.customer_id,
               c.name,
               t.txn_id,
               t.txn_type,
               t.amount,
               t.txn_ts
        FROM   customers  c
        JOIN   accounts   a ON a.customer_id = c.customer_id
        JOIN   transactions t ON t.account_no = a.account_no
        WHERE  TRUNC(t.txn_ts,'MM') = TRUNC(SYSDATE,'MM')   -- current month
        ORDER  BY c.customer_id, t.txn_ts;

    v_curr_cust   NUMBER := NULL;
BEGIN
    DBMS_OUTPUT.PUT_LINE(CHR(10) ||
        '==== Monthly Statements for '||
        TO_CHAR(SYSDATE,'MON YYYY')||' ====');

    FOR rec IN c_txn LOOP
        IF v_curr_cust IS NULL OR rec.customer_id <> v_curr_cust THEN
            IF v_curr_cust IS NOT NULL THEN
                DBMS_OUTPUT.PUT_LINE('-------------------------------------------');
            END IF;
            v_curr_cust := rec.customer_id;
            DBMS_OUTPUT.PUT_LINE(CHR(10)||
               'Customer: '||rec.name||'  (ID '||rec.customer_id||')');
            DBMS_OUTPUT.PUT_LINE('TxnID | Date  | Type     | Amount');
        END IF;

        DBMS_OUTPUT.PUT_LINE(
            RPAD(rec.txn_id,6)||' '||
            TO_CHAR(rec.txn_ts,'DD-MON')||' '||
            RPAD(rec.txn_type,8)||' '||
            TO_CHAR(rec.amount)
        );
    END LOOP;
END;
/

/* SCENARIO 2 ― ApplyAnnualFee
   ▸ Deducts a flat ₹500 maintenance fee from every account
   ▸ Prints old vs. new balance for each account
 */
 DECLARE
    CURSOR c_acct IS
        SELECT account_no, balance
        FROM   accounts
        FOR UPDATE;                 -- lock each row while we change it

    c_fee CONSTANT NUMBER := 500;   -- change fee amount here
BEGIN
    DBMS_OUTPUT.PUT_LINE(CHR(10)||
        '==== Applying Annual Fee of ₹'||c_fee||' ====');

    FOR rec IN c_acct LOOP
        UPDATE accounts
        SET    balance = balance - c_fee
        WHERE  CURRENT OF c_acct;

        DBMS_OUTPUT.PUT_LINE(
            'Account '||rec.account_no||
            ' : '||TO_CHAR(rec.balance)||' → '||
            TO_CHAR(rec.balance - c_fee)
        );
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee applied to all accounts.');
END;
/

/* SCENARIO 3 ― UpdateLoanInterestRates
   ▸ Simple policy:
       – If current rate < 10 %, add 0.50 %
       – Otherwise           add 0.25 %
   ▸ Prints old vs. new rate for each loan
 */
UPDATE loans SET interest_rate = 8.0 WHERE loan_id = 101;
UPDATE loans SET interest_rate = 9.5 WHERE loan_id = 102;
UPDATE loans SET interest_rate = 10.0 WHERE loan_id = 103;
COMMIT;

DECLARE
    CURSOR c_loan IS
        SELECT loan_id, interest_rate
        FROM   loans
        FOR UPDATE;

    v_old   loans.interest_rate%TYPE;
    v_new   loans.interest_rate%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE(CHR(10)||
        '==== Updating Loan Interest Rates ====');

    FOR rec IN c_loan LOOP
        v_old := rec.interest_rate;
        IF v_old < 10 THEN
            v_new := v_old + 0.50;
        ELSE
            v_new := v_old + 0.25;
        END IF;

        UPDATE loans
        SET    interest_rate = v_new
        WHERE  CURRENT OF c_loan;

        DBMS_OUTPUT.PUT_LINE(
            'Loan '||rec.loan_id||
            ' : '||TO_CHAR(v_old,'FM9990.00')||'% → '||
            TO_CHAR(v_new,'FM9990.00')||'%'
        );
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('All loan rates updated.');
END;
/