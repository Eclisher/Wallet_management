CREATE ROLE wallet_admin WITH LOGIN PASSWORD '123456';

CREATE DATABASE wallet_management;

\c wallet_management

CREATE TABLE IF NOT EXISTS currency (
                                        currency_id SERIAL PRIMARY KEY,
                                        currency_name VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO currency (currency_name) VALUES
                                         ('EUROS'),
                                         ('ARIARY'),
                                         ('DOLLAR');

CREATE TABLE IF NOT EXISTS account (
                                       account_id SERIAL PRIMARY KEY,
                                       user_name VARCHAR(255) NOT NULL,
                                       balance DECIMAL NOT NULL,
                                       currency_id INT REFERENCES currency(currency_id) ON DELETE CASCADE
);

INSERT INTO account (user_name, balance, currency_id) VALUES
                                                          ('Harizo ANDRIANAIVO', 1000.00, 1),
                                                          ('Ando RAMANANTSOA', 500.00, 2);


CREATE TABLE IF NOT EXISTS transaction (
                                           transaction_id SERIAL PRIMARY KEY,
                                           amount DECIMAL NOT NULL,
                                           account_source_id INT REFERENCES account(account_id) ON DELETE CASCADE,
                                           account_destination_id INT REFERENCES account(account_id) ON DELETE CASCADE
);

INSERT INTO transaction (amount, account_source_id, account_destination_id) VALUES
                                                                                (200.00, 1, 2),
                                                                                (50.00, 2, 1);


