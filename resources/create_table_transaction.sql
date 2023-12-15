CREATE TABLE IF NOT EXISTS "transaction" (
    "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
    "transaction_datetime" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "type" "transaction_type" NOT NULL,
    "label" VARCHAR(255) NOT NULL,
    "amount" DECIMAL(18,5) NOT NULL CHECK("amount" > 0),
    "account" VARCHAR(255) REFERENCES "account"("id") NOT NULL
);


INSERT INTO "transaction"
SELECT 'transaction_id1', '2023-01-01','DEBIT','transaction_label1', 100, 'account_id1'
WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE
        "id"='transaction_id1' AND "type"='DEBIT' AND "transaction_datetime"='2023-01-01' AND
        "label"='transaction_label1' AND "amount"=100 AND "account"='account_id1'
);

INSERT INTO "transaction"
SELECT 'transaction_id2', '2023-01-01','CREDIT','transaction_label2', 10, 'account_id2'
WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE
            "id"='transaction_id2' AND "type"='CREDIT' AND "transaction_datetime"='2023-01-01' AND
            "label"='transaction_label2' AND "amount"=10 AND "account"='account_id2'
);
INSERT INTO "transaction"
SELECT 'transaction_id3', '2023-01-01','CREDIT','transaction_label3', 1000, 'account_id3'
WHERE NOT EXISTS (
    SELECT 1 FROM "transaction"
    WHERE
            "id"='transaction_id3' AND "type"='CREDIT' AND "transaction_datetime"='2023-01-01' AND
            "label"='transaction_label3' AND "amount"=1000 AND "account"='account_id3'
);
