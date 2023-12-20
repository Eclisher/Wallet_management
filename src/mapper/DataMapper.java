package mapper;

import model.CategoryAmount;
import repository.AccountRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataMapper {

    private final AccountRepository accountRepository;

    public DataMapper() {
        this.accountRepository = new AccountRepository();
    }

    public Map<String, BigDecimal> mapCategoryAmounts(String accountId, LocalDate startDate, LocalDate endDate) {
        Map<String, BigDecimal> categoryAmounts = new HashMap<>();

        List<CategoryAmount> categoryAmountList = accountRepository.calculateCategoryAmounts(accountId, startDate.atStartOfDay(), endDate.atStartOfDay());

        for (CategoryAmount categoryAmount : categoryAmountList) {
            categoryAmounts.put(categoryAmount.getCategory(), categoryAmount.getAmount());
        }

        return categoryAmounts;
    }

    public void printCategoryAmounts(Map<String, BigDecimal> categoryAmounts) {
        for (Map.Entry<String, BigDecimal> entry : categoryAmounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
