DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'transaction_type') THEN
        CREATE TYPE "transaction_type" AS ENUM ( 'CREDIT', 'DEBIT' );
    END IF;
END $$;