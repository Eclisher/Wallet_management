package repository;

import lombok.AllArgsConstructor; import model.Balance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class BalanceRepository implements BasicRepository<Balance>{
    public final static String AMOUNT_LABEL = "balance";
    public final static String CREATION_DATETIME="creation_datetime";
    public final static String ACCOUNT_LABEL= "account";
    public final static String TABLE_NAME = "balance_history";

    private static Balance createInstance(ResultSet resultSet) throws SQLException {
        return new Balance(
            resultSet.getString(Query.ID_LABEL),
            resultSet.getBigDecimal(AMOUNT_LABEL),
            resultSet.getTimestamp(CREATION_DATETIME).toLocalDateTime()
        );
    }


    @Override
    public List<Balance> findAll(Map<String, Pair> filters) throws SQLException {
        List<Balance> results = new ArrayList<>();
        ResultSet resultSet = Query.selectAll(TABLE_NAME, filters," ORDER BY \"" + CREATION_DATETIME + "\" DESC");
        while(resultSet.next()){
            results.add(createInstance(resultSet));
        }
        return results;
    }

    @Override
    public List<Balance> saveAll(List<Balance> toSave, String meta) {
        List<Balance> result = new ArrayList<>();
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
    public Balance save(Balance toSave, String idAccount) throws SQLException {
        LocalDateTime dateTime = toSave.getCreationDatetime();
        Map<String,Pair> values = Map.of(
            Query.ID_LABEL, new Pair(toSave.getId(), true),
            AMOUNT_LABEL, new Pair(toSave.getAmount().toString(), false),
            CREATION_DATETIME, new Pair(dateTime != null ? dateTime.toString() : null,true),
            ACCOUNT_LABEL, new Pair(idAccount,true)
        );

        String id = Query.saveOrUpdate(TABLE_NAME, values);

        if(id != null)
            toSave.setId(id);
        return toSave;
    }
}
