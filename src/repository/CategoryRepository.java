package repository;

import model.CategoryAmount;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRepository {

    private static final String TABLE_CATEGORY = "category";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_AMOUNT = "amount";

    public void updateCategoryAmount(String categoryId, BigDecimal updatedAmount) throws SQLException {
        String updateQuery = String.format("UPDATE %s SET %s = ? WHERE %s = ?", TABLE_CATEGORY, COLUMN_AMOUNT, COLUMN_ID);

        try (Connection connection = PostgresqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setBigDecimal(1, updatedAmount);
            preparedStatement.setString(2, categoryId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Montant de la catégorie mis à jour avec succès.");
            } else {
                System.out.println("Aucune catégorie mise à jour. Vérifiez l'ID de la catégorie.");
            }

        } finally {
            PostgresqlConnection.closeConnection();
        }
    }

    public CategoryAmount findCategoryAmount(String categoryId) throws SQLException {
        String selectQuery = String.format("SELECT %s FROM %s WHERE %s = ?", COLUMN_AMOUNT, TABLE_CATEGORY, COLUMN_ID);

        try (Connection connection = PostgresqlConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

            preparedStatement.setString(1, categoryId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    BigDecimal amount = resultSet.getBigDecimal(COLUMN_AMOUNT);
                    return new CategoryAmount(categoryId, amount);
                }
            }

        } finally {
            PostgresqlConnection.closeConnection();
        }

        return null;
    }
}
