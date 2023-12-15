package repository;

import lombok.AllArgsConstructor; import model.Currency;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class CurrencyRepository implements BasicRepository<Currency>{
    public final static String NAME_LABEL = "name";
    public final static String CODE_LABEL = "code";
    public final static String TABLE_NAME = "currency";

    private static Currency createInstance(ResultSet resultSet) throws SQLException {
        return new Currency(
            resultSet.getString(Query.ID_LABEL),
            resultSet.getString(NAME_LABEL),
            resultSet.getString(CODE_LABEL)
        );
    }

    @Override
    public List<Currency> findAll(Map<String, Pair> filters) throws SQLException {
        List<Currency> results = new ArrayList<>();
        ResultSet resultSet = Query.selectAll(TABLE_NAME, filters, null);
        while(resultSet.next()){
            results.add(createInstance(resultSet));
        }
        return results;
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave, String meta) {
        List<Currency> result = new ArrayList<>();
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
    public Currency save(Currency toSave, String meta) throws SQLException {
        Map<String,Pair> values = Map.of(
            Query.ID_LABEL, new Pair(toSave.getId(), true),
            NAME_LABEL, new Pair(toSave.getName(), true),
            CODE_LABEL, new Pair(toSave.getCode(),true)
        );

        String id = Query.saveOrUpdate(TABLE_NAME, values);

        if(id != null)
            toSave.setId(id);
        return toSave;
    }
}
