package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExchangeRate {
    private String id;
    private Currency sourceCurrency;
    private Currency destinationCurrency;
    private BigDecimal value;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public ExchangeRate(String id, Currency sourceCurrency, Currency destinationCurrency, BigDecimal value, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.sourceCurrency = sourceCurrency;
        this.destinationCurrency = destinationCurrency;
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getDestinationCurrency() {
        return destinationCurrency;
    }

    public void setDestinationCurrency(Currency destinationCurrency) {
        this.destinationCurrency = destinationCurrency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
