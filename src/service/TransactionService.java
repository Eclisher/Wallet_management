package service;

import jdk.jfr.Category;
import model.CategoryAmount;
import model.Transaction;
import repository.AccountRepository;
import repository.TransactionRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.math.BigDecimal;
import java.util.Map;

public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public List<CategoryAmount> getTransactionSummary(String accountId, LocalDateTime startDatetime, LocalDateTime endDatetime) throws SQLException {
        List<Transaction> transactions = TransactionRepository.findTransactionsByDateRange(accountId, startDatetime, endDatetime);
        Map<String, BigDecimal> categoryAmounts = new HashMap<>();

        // Initialize category amounts to 0
        for (Transaction transaction : transactions) {
            String category = transaction.getType().toString(); // Remplacez cela par la vraie catégorie de votre transaction
            BigDecimal amount = transaction.getAmount();

            // Update the category amount
            categoryAmounts.put(category, categoryAmounts.getOrDefault(category, BigDecimal.ZERO).add(amount));
        }

        // Create a list of CategoryAmount objects
        List<CategoryAmount> result = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : categoryAmounts.entrySet()) {
            result.add(new CategoryAmount(entry.getKey(), entry.getValue()));
        }

        // Debugging information
        System.out.println("Number of transactions retrieved: " + transactions.size());
        System.out.println("Category Summary: " + result);

        return result;
    }

}


