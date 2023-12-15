

### Getting Started
 
- Set up environment variables as follows

```shell
DB_PASSWORD= #your password 
DB_URL=jdbc:postgresql://localhost:5432/hei_wallet
DB_USERNAME=#your postgresql username
```

- Create the database using the [migration files](resources/). This directory contains SQL files following the naming convention:
    - [create_database_hei_wallet.sql](resources/create_database_hei_wallet.sql)
    - [create_role_prog_admin.sql](resources/create_role_prog_admin.sql)
    - [create_extension_uuid.sql](resources/create_extension_uuid.sql)
    - [create_table_currency.sql](resources/create_table_currency.sql)
    - [create_account_type.sql](resources/create_account_type.sql)
    - [create_table_account.sql](resources/create_table_account.sql)
    - [create_transaction_type.sql](resources/create_transaction_type.sql)
    - [create_table_transaction.sql](resources/create_table_transaction.sql)

```
code by : std 22050 : Ramanantsoa Ando N'irina (andoKami)
          std 22007 : Andrianaivo haingo Harizo (eclisher)
```
# wallet
