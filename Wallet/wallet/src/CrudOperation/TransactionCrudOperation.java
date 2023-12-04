package src.CrudOperation;

import src.Interface.CrudOperation;
import src.Model.Transaction;
import src.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionCrudOperation  implements CrudOperation<Transaction> {
    private static Connection connection;

    public TransactionCrudOperation() {
    }

    private static Connection getConnection() {
        return DBConnection.getConnection();
    }
    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();

        try {
            Connection conn = getConnection();
            String query = "SELECT * FROM transaction";
            try (PreparedStatement statement = conn.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int transactionId = resultSet.getInt("transaction_id");
                    double amount = resultSet.getDouble("amount");
                    int accountSourceId = resultSet.getInt("account_source_id");
                    int accountDestinationId = resultSet.getInt("account_destination_id");

                    Transaction transaction = new Transaction(transactionId, amount, accountSourceId, accountDestinationId);
                    transactions.add(transaction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    @Override
    public Transaction save(Transaction toSave) {
        try {
            Connection conn = getConnection();
            String query = "INSERT INTO transaction (amount, account_source_id, account_destination_id) VALUES (?, ?, ?)";
            try (PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setDouble(1, toSave.getAmount());
                statement.setInt(2, toSave.getAccountSource());
                statement.setInt(3, toSave.getAccountDestination());
                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int transactionId = generatedKeys.getInt(1);
                            toSave.setTransactionId((long) transactionId);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        try {
            Connection conn = getConnection();
            String query = "DELETE FROM transaction WHERE transaction_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, Math.toIntExact(toDelete.getTransactionId()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}
