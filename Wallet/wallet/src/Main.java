package src;

import org.postgresql.largeobject.BlobOutputStream;
import src.CrudOperation.AccountCrudOperation;
import src.CrudOperation.CurrencyCrudOperation;
import src.CrudOperation.TransactionCrudOperation;
import src.Model.Account;
import src.Model.Currency;

import java.nio.channels.AcceptPendingException;
import java.util.List;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public Main() {
    }
    public static void main(String[] args) {
        AccountCrudOperation var1 = new AccountCrudOperation();
        CurrencyCrudOperation var2 = new CurrencyCrudOperation();
        TransactionCrudOperation var3 = new TransactionCrudOperation();

        //Find All the Account
        List var4 = var1.findAll();
        logger.info("All Accounts: " + var4);

        //Find all Transaction
        List var6 = var2.findAll();
        logger.info("All Transaction: " + var6);

        //Find all Currency
        List var5 = var3.findAll();
        logger.info("All Currency: " + var5);

    }
}
