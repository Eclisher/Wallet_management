DO
$create_role_prog_admin$
    BEGIN
        IF EXISTS (
            SELECT FROM pg_catalog.pg_roles WHERE  rolname = 'prog_admin') THEN
            RAISE NOTICE 'Role "prog_admin" already exists. Skipping.';
        ELSE
            CREATE ROLE prog_admin LOGIN PASSWORD '123456';
        END IF;
    END
$create_role_prog_admin$;

GRANT ALL PRIVILEGES ON DATABASE hei_wallet TO prog_admin;
ALTER DEFAULT PRIVILEGES FOR ROLE prog_admin
    GRANT ALL PRIVILEGES ON TABLES TO prog_admin;
ALTER DEFAULT PRIVILEGES FOR ROLE prog_admin
    GRANT ALL PRIVILEGES ON SEQUENCES TO prog_admin;
GRANT ALL PRIVILEGES ON SCHEMA public TO prog_admin;
\c hei_wallet prog_admin