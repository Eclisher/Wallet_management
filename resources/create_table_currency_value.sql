CREATE TABLE IF NOT EXISTS "currency_value" (
                                                "id" VARCHAR(255) PRIMARY KEY DEFAULT uuid_generate_v4(),
                                                "source_currency" VARCHAR(255) REFERENCES currency("id") NOT NULL,
                                                "destination_currency" VARCHAR(255) REFERENCES currency("id") NOT NULL,
                                                "value" DECIMAL(18,5) NOT NULL,
                                                "date_effect" TIMESTAMP NOT NULL
);

-- Exemple d'enregistrements
INSERT INTO "currency_value" ("source_currency", "destination_currency", "value", "date_effect")
VALUES
    ('currency_euro', 'currency_ariary', 4500, '2023-12-06 12:00:00'),
    ('currency_euro', 'currency_ariary', 4600, '2023-12-06 6:00:00');
