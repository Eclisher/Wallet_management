-- 2.a
CREATE OR REPLACE FUNCTION calculate_balance_changes(
    account_id VARCHAR(255),
    start_datetime TIMESTAMP,
    end_datetime TIMESTAMP
)
    RETURNS DECIMAL(18,5) AS $$
DECLARE
    total_changes DECIMAL(18,5);
BEGIN
    -- Calculer la somme des entrées (crédits)
    SELECT COALESCE(SUM(amount), 0)
    INTO total_changes
    FROM transaction
    WHERE account = account_id
      AND type = 'CREDIT'
      AND transaction_datetime BETWEEN start_datetime AND end_datetime;

    -- Soustraire la somme des sorties (débits)
    SELECT COALESCE(SUM(amount), 0)
    INTO total_changes
    FROM transaction
    WHERE account = account_id
      AND type = 'DEBIT'
      AND transaction_datetime BETWEEN start_datetime AND end_datetime;

    RETURN total_changes;
END;
$$ LANGUAGE plpgsql;

-- Exemple d'appel de la fonction
SELECT calculate_balance_changes('account_id1', '2023-01-01', '2023-12-31');
