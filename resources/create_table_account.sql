CREATE TABLE IF NOT EXISTS "account" (
    "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(255) NOT NULL,
    "type" "account_type" NOT NULL,
    "currency" VARCHAR(255) REFERENCES "currency"("id") NOT NULL
);

INSERT INTO "account"
SELECT 'account_id1', 'account_name1', 'CASH','currency_euro'
WHERE NOT EXISTS (
    SELECT 1 FROM "account"
    WHERE
        "id"='account_id1' AND "name"='account_name1' AND
        "type"='CASH' AND  "currency"='currency_euro'
);

INSERT INTO "account"
SELECT 'account_id2', 'account_name2', 'MOBILE_MONEY','currency_ariary'
WHERE NOT EXISTS (
    SELECT 1 FROM "account"
    WHERE
        "id"='account_id2' AND "name"='account_name2' AND
        "type"='MOBILE_MONEY' AND "currency"='currency_ariary'
);
INSERT INTO "account"
SELECT 'account_id3', 'account_name3','MOBILE_MONEY','currency_ariary'
WHERE NOT EXISTS (
    SELECT 1 FROM "account"
    WHERE
        "id"='account_id3' AND "name"='account_name3' AND
        "type"='MOBILE_MONEY' AND "currency"='currency_ariary'
);