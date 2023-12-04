package src;

import src.CrudOperation.AccountCrudOperation;
import src.CrudOperation.CurrencyCrudOperation;
import src.CrudOperation.TransactionCrudOperation;
import src.Model.Account;
import src.Model.Currency;
import src.Model.Transaction;

import java.util.List;
import java.util.logging.Logger;
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        CurrencyCrudOperation currencyCrudOperation = new CurrencyCrudOperation();

        // Find all Currency
        List<Currency> currencies = currencyCrudOperation.findAll();
        logger.info("All Currency: " + currencies);

//        // Save a new Currency
//        Currency newCurrency = new Currency(3, "DOLLAR");
//        Currency savedCurrency = currencyCrudOperation.save(newCurrency);
//        logger.info("Saved Currency: " + savedCurrency);
////
//        // Delete the Currency by currencyId
//        long currencyIdToDelete = savedCurrency.getCurrencyId();
//        currencyCrudOperation.delete(new Currency(4, null));
//        // Find all Currency after delete
//        List<Currency> currenciesAfterDelete = currencyCrudOperation.findAll();
//        logger.info("All Currency after delete: " + currenciesAfterDelete);

        AccountCrudOperation accountCrudOperation = new AccountCrudOperation();

        // Find all Account
        List<Account> accounts = accountCrudOperation.findAll();
        logger.info("All Accounts: " + accounts);
//
//        // Save a new Account
//        Account newAccount = new Account(3, "HEI MADAGASCAR", 1000.0, 1);
//        Account savedAccount = accountCrudOperation.save(newAccount);
//        logger.info("Saved Account: " + savedAccount);
//
//        // Delete the Account by accountId
//        int accountIdToDelete = savedAccount.getAccountId();
//        accountCrudOperation.delete(new Account(4, null,0.0, 0));
////
////        // Find all Account after delete
////        List<Account> accountsAfterDelete = accountCrudOperation.findAll();
////        logger.info("All Accounts after delete: " + accountsAfterDelete);
//
        TransactionCrudOperation transactionCrudOperation = new TransactionCrudOperation();

        //Find All
        List <Transaction> transactions = transactionCrudOperation.findAll();
        logger.info("All Transaction: " + transactions);
//
//        //Save a new Transaction
//        Transaction newTransaction = new Transaction(4, 5000.0, 1,3);
//        Transaction savedTransaction = transactionCrudOperation.save(newTransaction);
//        logger.info("Saved Transaction" + savedTransaction);
//
//        //Delete the Transaction by transactionId
//        int transactionIdDelete = Math.toIntExact(savedTransaction.getTransactionId());
//        transactionCrudOperation.delete(new Transaction(4, 5000.0, 1, 3));
    }
}
