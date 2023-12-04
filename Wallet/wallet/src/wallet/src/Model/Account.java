package src.wallet.src.Model;

public class Account {
    private Long accountId;
    private String userName;
    private double balance;
    private Currency currency;

    public Account(Long accountId, String userName, double balance, Currency currency) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = balance;
        this.currency = currency;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userName='" + userName + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                '}';
    }
}
