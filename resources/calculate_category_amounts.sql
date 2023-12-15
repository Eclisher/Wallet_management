-- 2.b
CREATE OR REPLACE FUNCTION calculate_category_amounts(
    account_id VARCHAR(255),
    start_datetime TIMESTAMP,
    end_datetime TIMESTAMP
)
    RETURNS TABLE (
                      category_name VARCHAR(255),
                      total_amount DECIMAL(18,5)
                  ) AS $$
BEGIN
    RETURN QUERY
        SELECT
            c.name AS category_name,
            COALESCE(SUM(t.amount), 0) AS total_amount
        FROM category c
                 LEFT JOIN transaction t ON c.id = t.category_id
            AND t.account = account_id
            AND t.transaction_datetime BETWEEN start_datetime AND end_datetime
        GROUP BY c.name;

    RETURN;
END;
$$ LANGUAGE plpgsql;

-- Exemple d'appel de la fonction
SELECT * FROM calculate_category_amounts('account_id1', '2023-01-01', '2023-12-31');

