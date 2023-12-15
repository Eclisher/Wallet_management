package service;

public class TransactionService {
  private TransactionCrudOperations transactionCrudOperations;
  private CategoryCrudOperations categoryCrudOperations;

  public TransactionService(TransactionCrudOperations transactionCrudOperations,
                            CategoryCrudOperations categoryCrudOperations) {
    this.transactionCrudOperations = transactionCrudOperations;
    this.categoryCrudOperations = categoryCrudOperations;
  }

  public BigDecimal calculateTransactionSum(int accountID, LocalDateTime startDate, LocalDateTime endDate) {
    List<Transaction> transactions = transactionCrudOperations.findByAccountID(accountID);
    BigDecimal sum = BigDecimal.ZERO;

    for (Transaction transaction : transactions) {
      LocalDateTime transactionDate = transaction.getTransactionDate();
      if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate)) {
        if (transaction.getTransactionType() == TransactionType.ENTRY) {
          sum = sum.add(transaction.getAmount());
        } else if (transaction.getTransactionType() == TransactionType.WITHDRAWAL) {
          sum = sum.subtract(transaction.getAmount());
        }
      }
    }

    return sum;
  }

  public List<TransactionSummary> calculateCategorySum(int accountID, LocalDateTime startDate, LocalDateTime endDate) {
    List<Category> categories = categoryCrudOperations.findAll();
    List<TransactionSummary> transactionSummaries = new ArrayList<>();

    for (Category category : categories) {
      BigDecimal categorySum = BigDecimal.ZERO;
      List<Transaction> transactions = transactionCrudOperations.findByCategoryIDAndAccountID(
          category.getCategoryID(), accountID);

      for (Transaction transaction : transactions) {
        LocalDateTime transactionDate = transaction.getTransactionDate();
        if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate)) {
          categorySum = categorySum.add(transaction.getAmount());
        }
      }

      TransactionSummary transactionSummary = new TransactionSummary();
      transactionSummary.setCategory(category.getCategoryName());
      transactionSummary.setSumAmount(categorySum);
      transactionSummaries.add(transactionSummary);
    }

    return transactionSummaries;
  }
}
