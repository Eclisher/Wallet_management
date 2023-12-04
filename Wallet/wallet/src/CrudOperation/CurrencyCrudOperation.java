package src.CrudOperation;

import src.DBConnection;
import src.Interface.CrudOperation;
import src.Model.Currency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import java.sql.ResultSet;
import java.sql.SQLException;



import static src.DBConnection.getConnection;

public class CurrencyCrudOperation  implements CrudOperation <Currency> {
    private static Connection connection;

    public CurrencyCrudOperation() {
    }

    private static Connection getConnection() {
        return DBConnection.getConnection();
    }
    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        try {
            Connection connection = getConnection(); // Obtenir la connexion une seule fois

            String query = "SELECT * FROM currency";
            try (PreparedStatement statement = connection.prepareStatement(query);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    int currencyId = resultSet.getInt("currency_id");
                    String currencyName = resultSet.getString("currency_name");
                    Currency currency = new Currency((long) currencyId, currencyName);
                    currencies.add(currency);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currencies;
    }

    @Override
    public Currency save(Currency toSave) {
        try {
            Connection conn = getConnection();
            String query = "INSERT INTO currency (currency_name) VALUES (?)";
            try (PreparedStatement statement = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, toSave.getCurrencyName());
                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int currencyId = generatedKeys.getInt(1);
                            toSave.setCurrencyId((long) currencyId);
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
    public Currency delete(Currency toDelete) {
        try {
            Connection conn = getConnection();
            String query = "DELETE FROM currency WHERE currency_id = ?";
            try (PreparedStatement statement = conn.prepareStatement(query)) {
                statement.setInt(1, Math.toIntExact(toDelete.getCurrencyId()));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toDelete;
    }
}
