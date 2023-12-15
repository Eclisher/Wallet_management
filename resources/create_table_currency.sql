CREATE TABLE IF NOT EXISTS "currency" (
    "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
    "name" VARCHAR(255) NOT NULL,
    "code" VARCHAR(50) NOT NULL
);

INSERT INTO "currency"
SELECT 'currency_dollar', 'Dollar US', 'USD'
WHERE NOT EXISTS (
    SELECT 1 FROM "currency"
    WHERE
        "id"='currency_dollar' AND "name"='Dollar US' AND "code"='USD'
);

INSERT INTO "currency"
SELECT 'currency_euro', 'Euro', 'EUR'
WHERE NOT EXISTS (
    SELECT 1 FROM "currency"
    WHERE
        "id"='currency_euro' AND "name"='Euro' AND "code"='EUR'
);

INSERT INTO "currency"
SELECT 'currency_ariary', 'Ariary', 'MGA'
WHERE NOT EXISTS (
    SELECT 1 FROM "currency"
    WHERE
        "id"='currency_ariary' AND "name"='Ariary' AND "code"='MGA'
);
