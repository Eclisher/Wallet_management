package src;

import src.CrudOperation.CurrencyCrudOperation;
import src.Model.Currency;
import java.util.List;
import java.util.logging.Logger;
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        CurrencyCrudOperation currencyCrudOperation = new CurrencyCrudOperation();

        // Find all Currency
        List<Currency> currencies = currencyCrudOperation.findAll();
        logger.info("All Currency: " + currencies);

        // Save a new Currency
        Currency newCurrency = new Currency(3, "DOLLAR");
        Currency savedCurrency = currencyCrudOperation.save(newCurrency);
        logger.info("Saved Currency: " + savedCurrency);
//
//        // Delete the Currency by currencyId
//        long currencyIdToDelete = savedCurrency.getCurrencyId();
//        currencyCrudOperation.delete(new Currency(4, null));
//        // Find all Currency after delete
//        List<Currency> currenciesAfterDelete = currencyCrudOperation.findAll();
//        logger.info("All Currency after delete: " + currenciesAfterDelete);


    }
}
