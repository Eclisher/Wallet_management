package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BalanceCalculator {
    public static double calculateBalanceWithWeightedAverage(Connection connection, String accountId, Timestamp calculationDate) {
        double totalValue = 0;
        double totalWeight = 0;

        try {
            String query = "SELECT t.amount, t.transaction_datetime, cv.value, cv.date_effect " +
                    "FROM transaction t " +
                    "JOIN currency_value cv ON t.account = cv.source_currency AND cv.destination_currency = 'currency_ariary' " +
                    "WHERE t.account = ? AND t.transaction_datetime <= ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, accountId);
                preparedStatement.setTimestamp(2, calculationDate);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        double amount = resultSet.getDouble("amount");
                        Timestamp transactionDatetime = resultSet.getTimestamp("transaction_datetime");
                        double value = resultSet.getDouble("value");
                        Timestamp dateEffect = resultSet.getTimestamp("date_effect");

                        totalValue += amount * value;
                        totalWeight += amount * (calculationDate.getTime() - transactionDatetime.getTime());
                    }
                }
            }

            if (totalWeight == 0) {
                return 0; // Avoid division by zero
            }

            double weightedAverage = totalValue / totalWeight;
            return weightedAverage;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Handle errors appropriately
        }
    }



    public static double calculateBalance(Connection connection, String accountId, Timestamp calculationDate, CalculationType calculationType) {
        List<Double> values = new ArrayList<>();

        try {
            String query = "SELECT t.amount, t.transaction_datetime, cv.value, cv.date_effect " +
                    "FROM transaction t " +
                    "JOIN currency_value cv ON t.account = cv.source_currency AND cv.destination_currency = 'currency_ariary' " +
                    "WHERE t.account = ? AND t.transaction_datetime <= ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, accountId);
                preparedStatement.setTimestamp(2, calculationDate);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        double amount = resultSet.getDouble("amount");
                        Timestamp transactionDatetime = resultSet.getTimestamp("transaction_datetime");
                        double value = resultSet.getDouble("value");
                        Timestamp dateEffect = resultSet.getTimestamp("date_effect");

                        values.add(amount * value);
                    }
                }
            }

            if (values.isEmpty()) {
                return 0; // No transactions found
            }

            double result;

            switch (calculationType) {
                case WEIGHTED_AVERAGE:
                    double totalValue = values.stream().mapToDouble(Double::doubleValue).sum();
                    result = totalValue / values.size();
                    break;
                case MINIMUM:
                    result = Collections.min(values);
                    break;
                case MAXIMUM:
                    result = Collections.max(values);
                    break;
                case MEDIAN:
                    Collections.sort(values);
                    int middle = values.size() / 2;
                    if (values.size() % 2 == 0) {
                        result = (values.get(middle - 1) + values.get(middle)) / 2.0;
                    } else {
                        result = values.get(middle);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid calculation type");
            }

            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // Handle errors appropriately
        }
    }

    public enum CalculationType {
        WEIGHTED_AVERAGE,
        MINIMUM,
        MAXIMUM,
        MEDIAN
    }
}
