package model;
import java.math.BigDecimal;
public class CategoryAmount {
    private String category;
    private BigDecimal amount;
    public CategoryAmount(String category, BigDecimal amount) {
        this.category = category;
        this.amount = amount;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CategoryAmount{" +
                "category='" + category + '\'' +
                ", amount=" + amount +
                '}';
    }
}