package src.CrudOperation;

import src.Interface.CrudOperation;
import src.Model.Currency;
import src.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyCrudOperation implements CrudOperation<Currency> {

    @Override
    public List<Currency> findAll() {
        List<Currency> currencies = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM currency");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int currencyId = resultSet.getInt("currency_id");
                String currencyName = resultSet.getString("currency_name");
                Currency currency = new Currency(currencyId, currencyName);
                currencies.add(currency);
            }
        } catch (SQLException e) {
            // Gérer l'erreur de manière appropriée, par exemple, logger ou relancer une exception spécifique.
            e.printStackTrace();
        }
        return currencies;
    }

    @Override
    public Currency save(Currency toSave) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO currency (currency_name) VALUES (?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, toSave.getCurrencyName());
            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int currencyId = generatedKeys.getInt(1);
                        toSave.setCurrencyId(currencyId);
                    }
                }
            }
            System.out.println("Currency Saved Succeffuly");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return toSave;
    }

    @Override
    public Currency delete(Currency toDelete) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM currency WHERE currency_id = ?")) {

            statement.setLong(1, toDelete.getCurrencyId());
            statement.executeUpdate();
            System.out.println("Delete Succeffuly");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDelete;
    }
}
