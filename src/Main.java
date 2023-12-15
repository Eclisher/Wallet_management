import model.*;
import repository.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import static repository.BalanceCalculator.calculateBalanceWithWeightedAverage;

public class Main {
    public static void main(String[] args) throws SQLException {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        BalanceRepository balanceRepository = new BalanceRepository();
        AccountRepository accountRepository = new AccountRepository();

/*
        currencyRepository.findAll(null).forEach(System.out::println);
        balanceRepository.findAll(null).forEach(System.out::println);
        transactionRepository.findAll(null).forEach(System.out::println);
        accountRepository.findAll(null).forEach(System.out::println);
*/

        Account account = accountRepository.doTransaction(new Transaction(
                null,
                "first transaction",
                BigDecimal.valueOf(1000),
                null,
                TransactionType.DEBIT
        ), "account_id1");

        System.out.println("Account ID: " + account.getId());
        System.out.println("Account Name: " + account.getName());
        System.out.println(account);

// Ajoutez d'autres propriétés que vous souhaitez afficher
       // 5.a
        try (Connection connection = PostgresqlConnection.getConnection()) {
            String accountId = "account_id1";
            Timestamp calculationDate = Timestamp.valueOf("2023-01-01 00:00:00");

            double balance = calculateBalanceWithWeightedAverage(connection, accountId, calculationDate);
            System.out.println("Balance with weighted average: " + balance);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PostgresqlConnection.closeConnection();
        }
        // 5.b
//        try (Connection connection = PostgresqlConnection.getConnection()) {
//            String accountId = "account_id2";
//            Timestamp calculationDate = Timestamp.valueOf("2023-12-06 12:00:00");
//
//            // Exemple avec la moyenne pondérée
//            double weightedAverageBalance = BalanceCalculator.calculateBalance(connection, accountId, calculationDate, BalanceCalculator.CalculationType.WEIGHTED_AVERAGE);
//            System.out.println("Balance with weighted average: " + weightedAverageBalance);
//
//            // Exemple avec la valeur minimale
//            double minBalance = BalanceCalculator.calculateBalance(connection, accountId, calculationDate, BalanceCalculator.CalculationType.MINIMUM);
//            System.out.println("Minimum balance: " + minBalance);
//
//            // Exemple avec la valeur maximale
//            double maxBalance = BalanceCalculator.calculateBalance(connection, accountId, calculationDate, BalanceCalculator.CalculationType.MAXIMUM);
//            System.out.println("Maximum balance: " + maxBalance);
//
//            // Exemple avec la médiane
//            double medianBalance = BalanceCalculator.calculateBalance(connection, accountId, calculationDate, BalanceCalculator.CalculationType.MEDIAN);
//            System.out.println("Median balance: " + medianBalance);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            PostgresqlConnection.closeConnection();
//        }
    }
}