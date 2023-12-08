SELECT 'CREATE DATABASE hei_wallet'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'hei_wallet')\gexec

\c hei_wallet ;