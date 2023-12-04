package src.Model;

public class Currency {
    private Long currencyId;
    private String currencyName;

    public Currency(Long currencyId, String currencyName) {
        this.currencyId = currencyId;
        this.currencyName = currencyName;
    }

    public Long getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Long currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currencyId=" + currencyId +
                ", currencyName='" + currencyName + '\'' +
                '}';
    }
}
