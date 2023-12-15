package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionCategorization {

    private static final String TABLE_TRANSACTION = "\"transaction\"";
    private static final String COLUMN_CATEGORY_ID = "\"category_id\"";
    private static final String COLUMN_ID = "\"id\"";

    public static void associerTransactionACategorie(String transactionId, String categoryId) {
        String updateQuery = String.format("UPDATE %s SET %s = ? WHERE %s = ?", TABLE_TRANSACTION, COLUMN_CATEGORY_ID, COLUMN_ID);

        try (Connection connection = PostgresqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, categoryId);
            preparedStatement.setString(2, transactionId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Transaction associée à la catégorie avec succès.");
            } else {
                System.out.println("Aucune transaction mise à jour. Vérifiez l'ID de transaction.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PostgresqlConnection.closeConnection();
        }
    }
}
