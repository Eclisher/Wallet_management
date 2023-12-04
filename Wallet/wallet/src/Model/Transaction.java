package src.Model;

public class Transaction {
    private Long transactionId;
    private double amount;
    private int accountSource;
    private int accountDestination;

    public Transaction(int transactionId, double amount, int accountSource, int accountDestination) {
        this.transactionId = (long) transactionId;
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

    public int getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(int accountSource) {
        this.accountSource = accountSource;
    }

    public int getAccountDestination() {
        return accountDestination;
    }

    public void setAccountDestination(int accountDestination) {
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

