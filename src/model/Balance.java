package model;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class Balance {
    private String id;
    private BigDecimal amount;
    private LocalDateTime creationDatetime;
    public Balance(String id, BigDecimal amount, LocalDateTime creationDatetime) {
        this.id = id;
        this.amount = amount;
        this.creationDatetime = creationDatetime;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public LocalDateTime getCreationDatetime() {
        return creationDatetime;
    }
    public void setCreationDatetime(LocalDateTime creationDatetime) {
        this.creationDatetime = creationDatetime;
    }
}
