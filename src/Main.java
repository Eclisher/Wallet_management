import model.*;
import repository.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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

        System.out.println(accountRepository.doTransaction(new Transaction(
            null,
            "first transaction",
            BigDecimal.valueOf(1000),
            null,
            TransactionType.DEBIT
        ), "account_id1"));
    }
}