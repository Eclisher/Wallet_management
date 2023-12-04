package src.wallet.src.Model;

public class Transaction {
    private Long transactionId;
    private double amount;
    private Account accountSource;
    private Account accountDestination;

    public Transaction(Long transactionId, double amount, Account accountSource, Account accountDestination) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.accountSource = accountSource;
        this.accountDestination = accountDestination;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(Account accountSource) {
        this.accountSource = accountSource;
    }

    public Account getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(Account accountDestination) {
        this.accountDestination = accountDestination;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", accountSource=" + accountSource +
                ", accountDestination=" + accountDestination +
                '}';
    }
}

