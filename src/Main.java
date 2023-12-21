import mapper.DataMapper;
import model.*;
import repository.*;
import service.TransactionService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static repository.BalanceCalculator.calculateBalanceWithWeightedAverage;

public class Main {
    public static void main(String[] args) throws SQLException {
        CurrencyRepository currencyRepository = new CurrencyRepository();
        TransactionRepository transactionRepository = new TransactionRepository();
        BalanceRepository balanceRepository = new BalanceRepository();
        AccountRepository accountRepository = new AccountRepository();
//
///*
//        currencyRepository.findAll(null).forEach(System.out::println);
//        balanceRepository.findAll(null).forEach(System.out::println);
//        transactionRepository.findAll(null).forEach(System.out::println);
//        accountRepository.findAll(null).forEach(System.out::println);
//*/
//
//        Account account = accountRepository.doTransaction(new Transaction(
//                null,
//                "first transaction",
//                BigDecimal.valueOf(1000),
//                null,
//                TransactionType.DEBIT
//        ), "account_id1");
//
//        System.out.println("Account ID: " + account.getId());
//        System.out.println("Account Name: " + account.getName());
//        System.out.println(account);
//
//// Ajoutez d'autres propriétés que vous souhaitez afficher
//       // 5.a
//        try (Connection connection = PostgresqlConnection.getConnection()) {
//            String accountId = "account_id1";
//            Timestamp calculationDate = Timestamp.valueOf("2023-01-01 00:00:00");
//
//            double balance = calculateBalanceWithWeightedAverage(connection, accountId, calculationDate);
//            System.out.println("Balance with weighted average: " + balance);
//
//        } catch (SQLException e) {
//            throw  new RuntimeException();
//        } finally {
//            PostgresqlConnection.closeConnection();
//        }
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
//            throw  new RuntimeException()();
//        } finally {
//            PostgresqlConnection.closeConnection();
//        }

//        try {
//            // Créer une nouvelle transaction
//            Transaction transaction = new Transaction(
//                    null,
//                    "Sample Transaction",
//                    BigDecimal.valueOf(500),
//                    LocalDateTime.now(),
//                    TransactionType.CREDIT
//            );
//
//            // ID de compte pour lequel la transaction doit être effectuée
//            String accountId = "account_id1";
//
//            // Effectuer la transaction et récupérer le compte mis à jour
//            Account updatedAccount = accountRepository.doTransaction(transaction, accountId);
//
//            // Afficher les détails du compte mis à jour
//            System.out.println("Updated Account ID: " + updatedAccount.getId());
//            System.out.println("Updated Account Name: " + updatedAccount.getName());
//            System.out.println("Updated Account Balance: " + updatedAccount.getBalance().getAmount());
//
//            // Exemple d'appel de la fonction calculateCategoryAmounts
//            LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 1, 0, 0);
//            LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
//
//            List<CategoryAmount> categoryAmounts = accountRepository.calculateCategoryAmounts(accountId, startDateTime, endDateTime);
//
//            // Afficher les montants de catégorie calculés
//            for (CategoryAmount categoryAmount : categoryAmounts) {
//                System.out.println("Category: " + categoryAmount.getCategory() + ", Amount: " + categoryAmount.getAmount());
//            }
//
//        } catch (SQLException e) {
//            throw  new RuntimeException();
//        }

// 2)
//        try {
//            // Créer une nouvelle transaction
//            Transaction transaction = new Transaction(
//                    null,
//                    "Sample Transaction",
//                    BigDecimal.valueOf(500),
//                    LocalDateTime.now(),
//                    TransactionType.CREDIT
//            );
//
//            // ID de compte pour lequel la transaction doit être effectuée
//            String accountId = "account_id1";
//
//            // Effectuer la transaction et récupérer le compte mis à jour
//            Account updatedAccount = accountRepository.doTransaction(transaction, accountId);
//
//            // Afficher les détails du compte mis à jour
//            System.out.println("Updated Account ID: " + updatedAccount.getId());
//            System.out.println("Updated Account Name: " + updatedAccount.getName());
//            System.out.println("Updated Account Balance: " + updatedAccount.getBalance().getAmount());
//
//            // Exemple d'appel de la fonction calculateCategoryAmounts
//            LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 1, 0, 0);
//            LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
//
//            List<CategoryAmount> categoryAmounts = accountRepository.calculateCategoryAmounts(accountId, startDateTime, endDateTime);
//
//            // Afficher les montants de catégorie calculés
//            System.out.println("Category Amounts:");
//            for (CategoryAmount categoryAmount : categoryAmounts) {
//                System.out.println("Category: " + categoryAmount.getCategory() + ", Amount: " + categoryAmount.getAmount());
//            }
//
//        } catch (SQLException e) {
//            // Gérer les exceptions spécifiques si nécessaire
//            throw  new RuntimeException();
//        }
        // question 3
        // Créer une instance de DataMapper

//            e.printStackTrace();
//        }

// 2)
//        try {
//            // Créer une nouvelle transaction
//            Transaction transaction = new Transaction(
//                    null,
//                    "Sample Transaction",
//                    BigDecimal.valueOf(500),
//                    LocalDateTime.now(),
//                    TransactionType.CREDIT
//            );
//
//            // ID de compte pour lequel la transaction doit être effectuée
//            String accountId = "account_id1";
//
//            // Effectuer la transaction et récupérer le compte mis à jour
//            Account updatedAccount = accountRepository.doTransaction(transaction, accountId);
//
//            // Afficher les détails du compte mis à jour
//            System.out.println("Updated Account ID: " + updatedAccount.getId());
//            System.out.println("Updated Account Name: " + updatedAccount.getName());
//            System.out.println("Updated Account Balance: " + updatedAccount.getBalance().getAmount());
//
//            // Exemple d'appel de la fonction calculateCategoryAmounts
//            LocalDateTime startDateTime = LocalDateTime.of(2023, 1, 1, 0, 0);
//            LocalDateTime endDateTime = LocalDateTime.of(2023, 12, 31, 23, 59);
//
//            List<CategoryAmount> categoryAmounts = accountRepository.calculateCategoryAmounts(accountId, startDateTime, endDateTime);
//
//            // Afficher les montants de catégorie calculés
//            System.out.println("Category Amounts:");
//            for (CategoryAmount categoryAmount : categoryAmounts) {
//                System.out.println("Category: " + categoryAmount.getCategory() + ", Amount: " + categoryAmount.getAmount());
//            }
//
//        } catch (SQLException e) {
//            // Gérer les exceptions spécifiques si nécessaire
//            e.printStackTrace();
//        }
        // question 3
//        // Créer une instance de DataMapper
//        DataMapper dataMapper = new DataMapper();
//
//        // Définir les paramètres pour la fonction SQL (question 2)
//        String accountId = "account_id1";
//        LocalDate startDate = LocalDate.of(2023, 1, 1);
//        LocalDate endDate = LocalDate.of(2023, 12, 31);
//
//        // Appeler la méthode de mapping des montants de catégorie
//        Map<String, BigDecimal> categoryAmounts = dataMapper.mapCategoryAmounts(accountId, startDate, endDate);
//
//        // Afficher les montants de catégorie
//        System.out.println("Category Amounts:");
//        dataMapper.printCategoryAmounts(categoryAmounts);

        // 4)
        // Créez une instance de TransactionService en lui passant l'instance de TransactionRepository
        TransactionService transactionService = new TransactionService(transactionRepository);
        // Supposons que vous voulez tester avec un autre compte et des dates différentes
        String anotherAccountId = "account_id2";
        LocalDateTime anotherStartDatetime = LocalDateTime.of(2023, 12, 10, 0, 0);
        LocalDateTime anotherEndDatetime = LocalDateTime.of(2023, 12, 20, 23, 59);


        try {
            // Appel de la méthode pour obtenir le résumé des transactions par catégorie
            List<CategoryAmount> categoryAmounts = transactionService.getTransactionSummary(anotherAccountId, anotherStartDatetime, anotherEndDatetime);

            // Affichage des résultats
            System.out.println("Category Summary for Another Account:");
            for (CategoryAmount categoryAmount : categoryAmounts) {
                System.out.println("Category: " + categoryAmount.getCategory() + ", Amount: " + categoryAmount.getAmount());
            }
        } catch (SQLException e) {
            // Gérer l'exception de manière appropriée dans votre application
            e.printStackTrace();
        }
    }
}