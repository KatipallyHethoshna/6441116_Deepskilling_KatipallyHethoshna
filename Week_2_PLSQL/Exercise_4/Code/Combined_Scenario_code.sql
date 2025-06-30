/* Scenario 1 – CalculateAge*/
CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER
IS
    v_years NUMBER;
BEGIN
    v_years := FLOOR( MONTHS_BETWEEN(TRUNC(SYSDATE), TRUNC(p_dob)) / 12 );
    RETURN v_years;
END CalculateAge;
/
SELECT CalculateAge(DATE '1995-06-29') AS age_years FROM dual;

/*Scenario 2 – CalculateMonthlyInstallment*/
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_principal     IN NUMBER,   -- loan amount
    p_annual_rate   IN NUMBER,   -- e.g. 8  means 8 %
    p_years         IN NUMBER    -- loan term in years
) RETURN NUMBER
IS
    v_month_rate NUMBER := p_annual_rate / 1200;  -- annual% ➜ monthly fraction
    v_months     NUMBER := p_years * 12;
    v_emi        NUMBER;
BEGIN
    IF v_month_rate = 0 THEN                       -- zero‑interest loan
        v_emi := p_principal / v_months;
    ELSE
        v_emi := p_principal
                 * v_month_rate
                 * POWER(1 + v_month_rate, v_months)
                 / ( POWER(1 + v_month_rate, v_months) - 1 );
    END IF;

    RETURN v_emi;
END CalculateMonthlyInstallment;
/
SELECT ROUND(
         CalculateMonthlyInstallment(500000, 8, 5), 2
       ) AS monthly_emi
FROM   dual;

/*Scenario 3 – HasSufficientBalance*/
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_no IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN
IS
    v_balance NUMBER;
BEGIN
    SELECT balance
      INTO v_balance
      FROM accounts
     WHERE ACCT_ID = p_account_no;

    RETURN v_balance >= p_amount;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN FALSE;                               -- account missing
    WHEN OTHERS THEN
        log_error('HasSufficientBalance', SQLCODE, SQLERRM);
        RETURN FALSE;
END HasSufficientBalance;
/

SET SERVEROUTPUT ON
DECLARE
    v_age    NUMBER;
    v_emi    NUMBER;
    v_ok     BOOLEAN;
BEGIN
    v_age := CalculateAge(DATE '1995-06-29');
    DBMS_OUTPUT.PUT_LINE('Age          : ' || v_age || ' years');

    v_emi := CalculateMonthlyInstallment(500000, 8, 5);
    DBMS_OUTPUT.PUT_LINE('Monthly EMI  : ' || TO_CHAR(v_emi, '999G999D00'));

    v_ok := HasSufficientBalance(101, 1000);  -- change acct / amt as needed
    IF v_ok THEN
        DBMS_OUTPUT.PUT_LINE('Has balance? : TRUE');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Has balance? : FALSE');
    END IF;
END;
/