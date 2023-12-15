
CREATE TABLE IF NOT EXISTS "transfer_history" (
                                                  "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
                                                  "debit_transaction_id" VARCHAR(255) REFERENCES transaction("id") NOT NULL,
                                                  "credit_transaction_id" VARCHAR(255) REFERENCES transaction("id") NOT NULL,
                                                  "transfer_date" TIMESTAMP NOT NULL
);

-- Exemple d'utilisation
INSERT INTO "transfer_history" ("debit_transaction_id", "credit_transaction_id", "transfer_date")
VALUES
    ('transaction_id1', 'transaction_id2', '2023-12-06 12:30:00');