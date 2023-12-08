package src.CrudOperation;

import src.DBConnection;
import src.Interface.CrudOperation;
import src.Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountCrudOperation implements CrudOperation<Account> {

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM account");
             ResultSet resultSet = statement.executeQuery()) {
        

            while (resultSet.next()) {
                /*a éviter */
                int accountId = resultSet.getInt("account_id");
                String userName = resultSet.getString("user_name");
                double balance = resultSet.getDouble("balance");
                int currencyId = resultSet.getInt("currency_id");

                Account account = new Account(accountId, userName, balance, currencyId);
                accounts.add(account);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            
            
            /*pas utile */

        }       
    return accounts;
    }

    @Override
    public Account save(Account toSave) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO account (user_name, balance, currency_id) VALUES (?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, toSave.getUserName());
            statement.setDouble(2, toSave.getBalance());
            statement.setInt(3, toSave.getCurrency());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int accountId = generatedKeys.getInt(1);
                        toSave.setAccountId(accountId);
                    }
                }
            }

            System.out.println("Account saved successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toSave;
    }

    @Override
    public Account delete(Account toDelete) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM account WHERE account_id = ?")) {

            statement.setInt(1, toDelete.getAccountId());
            statement.executeUpdate();

            System.out.println("Account deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }

    public Account findById(int accountId) {
        Account account = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM account WHERE account_id = ?");
        ) {
            statement.setInt(1, accountId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userName = resultSet.getString("user_name");
                    double balance = resultSet.getDouble("balance");
                    int currencyId = resultSet.getInt("currency_id");

                    account = new Account(accountId, userName, balance, currencyId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'erreur de manière appropriée (par exemple, journalisation)
        }

        return account;
    }
}
