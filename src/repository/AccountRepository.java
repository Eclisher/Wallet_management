package repository;

import lombok.AllArgsConstructor;
import model.*;
import java.sql.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AccountRepository implements BasicRepository<Account> {
    private final static String
            TABLE_NAME ="account",
            NAME_LABEL="name",
            TYPE_LABEL="type",
            CURRENCY_LABEL="currency";
    final private static BalanceRepository balanceRepository = new BalanceRepository();
    final private static TransactionRepository transactionRepository = new TransactionRepository();
    final private static CurrencyRepository currencyRepository = new CurrencyRepository();
    private static Account createInstance(ResultSet resultSet) throws SQLException {
        String accountId = resultSet.getString(Query.ID_LABEL);

        // Utilisez des filtres spécifiques pour les balances et transactions
        Map<String, Pair> balanceFilter = Map.of(BalanceRepository.ACCOUNT_LABEL, new Pair(accountId, true));
        Map<String, Pair> transactionFilter = Map.of(TransactionRepository.ACCOUNT_LABEL, new Pair(accountId, true));
        Map<String, Pair> currencyFilter = Map.of(Query.ID_LABEL, new Pair(resultSet.getString(CURRENCY_LABEL), true));

        List<Balance> balanceList = balanceRepository.findAll(balanceFilter);
        return new Account(
                accountId,
                resultSet.getString(NAME_LABEL),
                AccountType.valueOf(resultSet.getString(TYPE_LABEL)),
                currencyRepository.findAll(currencyFilter).get(0),
                !balanceList.isEmpty() ? balanceList.get(0) : new Balance("-", BigDecimal.valueOf(0), LocalDateTime.now()),
                transactionRepository.findAll(transactionFilter)
        );
    }
    @Override
    public List<Account> findAll(Map<String, Pair> filters) throws SQLException {
        List<Account> results = new ArrayList<>();
        ResultSet resultSet = Query.selectAll(TABLE_NAME, filters, null);
        while(resultSet.next()){
            results.add(createInstance(resultSet));
        }
        return results;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave, String meta) {
        List<Account> result = new ArrayList<>();
        toSave.forEach(el-> {
            try {
                result.add(save(el, meta));
            } catch (SQLException e) {
                throw  new RuntimeException();
                // Gérer l'exception de manière appropriée dans votre application
            }
        });
        return result;
    }
    @Override
    public Account save(Account toSave, String meta) throws SQLException {
        Map<String,Pair> values = Map.of(
                Query.ID_LABEL, new Pair(toSave.getId(), true),
                NAME_LABEL, new Pair(toSave.getName(), true),
                TYPE_LABEL, new Pair(toSave.getType().toString(),true),
                CURRENCY_LABEL, new Pair(toSave.getCurrency().getId(), true)
        );

        String id = Query.saveOrUpdate(TABLE_NAME, values);
        if(id != null)
            toSave.setId(id);
        return toSave;
    }
    public Account doTransaction(Transaction transaction, String accountId) throws SQLException {
        if (accountId != null) {
            Map<String, Pair> accountFilter = Map.of(Query.ID_LABEL, new Pair(accountId, true));
            List<Account> accounts = findAll(accountFilter);
            if (accounts.isEmpty())
                return null;
            Account target = accounts.get(0);
            if (
                    target.getType() != AccountType.BANK &&
                            transaction.getType() == TransactionType.DEBIT &&
                            target.getBalance().getAmount().compareTo(transaction.getAmount()) < 0
            ) {
                return target;
            }
            // Supprimez les valeurs générées
            transaction.setTransactionDatetime(null);
            transaction.setId(null);
            // Persistez la nouvelle transaction
            transactionRepository.save(transaction, target.getId());
            // Persistez le nouveau solde
            BigDecimal amountToAdd = transaction.getType() == TransactionType.CREDIT ? transaction.getAmount() : transaction.getAmount().negate();
            BigDecimal newBalance = target.getBalance().getAmount().add(amountToAdd);
            balanceRepository.save(new Balance(
                    null,
                    newBalance,
                    null
            ), target.getId());
            return findAll(accountFilter).get(0);
        }
        return null;
    }
    public List<CategoryAmount> calculateCategoryAmounts(String accountId, LocalDateTime startDatetime, LocalDateTime endDatetime) {
        List<CategoryAmount> categoryAmounts = new ArrayList<>();

        try (Connection connection = PostgresqlConnection.getConnection()) {
            String query = "SELECT * FROM calculate_category_amounts(?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, accountId);
                preparedStatement.setTimestamp(2, Timestamp.valueOf(startDatetime));
                preparedStatement.setTimestamp(3, Timestamp.valueOf(endDatetime));
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    // Mapper les résultats de la requête aux catégories et montants
                    while (resultSet.next()) {
                        String categoryName = resultSet.getString("category_name");
                        BigDecimal totalAmount = resultSet.getBigDecimal("total_amount");
                        CategoryAmount categoryAmount = new CategoryAmount(categoryName, totalAmount);
                        categoryAmounts.add(categoryAmount);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(); // Gérer les exceptions de manière appropriée dans votre application
        }
        return categoryAmounts;
    }
}
