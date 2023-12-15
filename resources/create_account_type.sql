DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'account_type') THEN
        CREATE TYPE "account_type" AS ENUM ('CASH', 'MOBILE_MONEY', 'BANK');
    END IF;
END $$;