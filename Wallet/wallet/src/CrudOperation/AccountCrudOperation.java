package src.CrudOperation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import src.DBConnection;
import src.Interface.CrudOperation;
import src.Model.Account;

public class AccountCrudOperation implements CrudOperation<Account> {
    private static Connection connection;

    public AccountCrudOperation() {
    }

    private static Connection getConnection() {
        return DBConnection.getConnection();
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM account");
             ResultSet resultSet = stmt.executeQuery()) {

            while (resultSet.next()) {
                int accountId = resultSet.getInt("account_id");
                String userName = resultSet.getString("user_name");
                double balance = resultSet.getDouble("balance");
                int currencyId = resultSet.getInt("currency_id");

                Account account = new Account(accountId, userName, balance, currencyId);
                accounts.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Log or print more detailed error information here
        }

        return accounts;
    }

    @Override
    public Account save(Account toSave) {
        try (Connection connection = getConnection()) {
            String query = "INSERT INTO account (user_name, balance, currency_id) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, toSave.getUserName());
                statement.setDouble(2, toSave.getBalance());
                statement.setInt(3, toSave.getCurrency());
                statement.executeUpdate();
                System.out.println("Account saved successfully!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toSave;
    }



    @Override
    public Account delete(Account toDelete) {
        try {
            Connection conn = getConnection();
            String query = "DELETE FROM account WHERE account_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, Math.toIntExact(toDelete.getAccountId()));
            stmt.executeUpdate();
            System.out.println("Account deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}
