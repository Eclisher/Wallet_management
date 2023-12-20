package repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

public class Pair {
    private String word;
    private boolean quote;

    public Pair(String word, boolean quote) {
        this.word = word;
        this.quote = quote;
    }
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
    }
    public boolean isQuote() {
        return quote;
    }
    public void setQuote(boolean quote) {
        this.quote = quote;
    }
}