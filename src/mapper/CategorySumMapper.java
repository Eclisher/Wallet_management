package mapper;

public class CategorySumMapper {
  public List<TransactionSummary> calculateCategorySum(int accountID, LocalDateTime startDate, LocalDateTime endDate) {
    List<Transaction> transactions = transactionApiRestClient.getTransactionsByAccountID(accountID);
    List<Category> categories = categoryApiRestClient.getAllCategories();
    Map<Integer, BigDecimal> categorySumMap = new HashMap<>();

    for (Transaction transaction : transactions) {
        if (transaction.getTransactionType() == TransactionType.ENTRY &&
                transaction.getTransactionDate().isAfter(startDate) &&
                transaction.getTransactionDate().isBefore(endDate)) {
            int categoryId = transaction.getCategoryID();
            BigDecimal amount = transaction.getAmount();
            categorySumMap.put(categoryId, categorySumMap.getOrDefault(categoryId, BigDecimal.ZERO).add(amount));
        }
    }

    List<TransactionSummary> transactionSummaries = new ArrayList<>();

    for (Category category : categories) {
        int categoryId = category.getCategoryID();
        BigDecimal sumAmount = categorySumMap.getOrDefault(categoryId, BigDecimal.ZERO);
        TransactionSummary transactionSummary = new TransactionSummary();
        transactionSummary.setCategory(category.getCategoryName());
        transactionSummary.setSumAmount(sumAmount);
        transactionSummaries.add(transactionSummary);
    }

    return transactionSummaries;
  }
}
