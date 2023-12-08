package repository;

import lombok.AllArgsConstructor;
import model.Transaction;
import model.TransactionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class TransactionRepository implements BasicRepository<Transaction>{
    public final static String
        LBL_LABEL = "label",
        AMOUNT_LABEL= "amount",
        DATETIME_LABEL= "transaction_datetime",
        ACCOUNT_LABEL= "account",
        TYPE_LABEL= "type",
        TABLE_NAME="transaction";
    private final static AccountRepository accountRepository = new AccountRepository();
    private static Transaction createInstance(ResultSet resultSet) throws SQLException {
        return new Transaction(
            resultSet.getString(Query.ID_LABEL),
            resultSet.getString(LBL_LABEL),
            resultSet.getBigDecimal(AMOUNT_LABEL),
            resultSet.getTimestamp(DATETIME_LABEL).toLocalDateTime(),
            TransactionType.valueOf(resultSet.getString(TYPE_LABEL))
        );
    }

    @Override
    public List<Transaction> findAll(Map<String, Pair> filters) throws SQLException {
        List<Transaction> results = new ArrayList<>();
        ResultSet resultSet = Query.selectAll(TABLE_NAME, filters, null);
        while(resultSet.next()){
            results.add(createInstance(resultSet));
        }
        return results;
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave, String meta) {
        List<Transaction> result = new ArrayList<>();
        toSave.forEach(el-> {
            try {
                result.add(save(el, meta));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });
        return result;
    }

    @Override
    public Transaction save(Transaction toSave, String idAccount) throws SQLException {
        LocalDateTime datetime = toSave.getTransactionDatetime();
        Map<String,Pair> values = Map.of(
            Query.ID_LABEL, new Pair(toSave.getId(), true),
            LBL_LABEL, new Pair(toSave.getLabel(), true),
            DATETIME_LABEL, new Pair( datetime != null ?  datetime.toString() : null, true),
            TYPE_LABEL,new Pair(toSave.getType().toString(),true),
            ACCOUNT_LABEL, new Pair(idAccount, true),
            AMOUNT_LABEL, new Pair(toSave.getAmount().toString(), false)
        );

        String id = Query.saveOrUpdate(TABLE_NAME, values);

        if(id != null)
            toSave.setId(id);
        return toSave;
    }
}
