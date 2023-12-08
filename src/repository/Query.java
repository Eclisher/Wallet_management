package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Query {
    public final static String ID_LABEL = "id";
    private final static Connection connection = PostgresqlConnection.getConnection();
    public static String toSqlName(String text){
        return "\"" + text +  "\"";
    }
    public static String addQuote(Pair pair){return pair.isQuote() ? "'" + pair.getWord() + "'" : pair.getWord();}
    public static String createKeyValue(Map<String, Pair> values) {
        return values.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + addQuote(entry.getValue()))
                .collect(Collectors.joining(", "));
    }

    public static ResultSet selectAll(String tableName, Map<String, Pair> filters, String suffixSQL) throws SQLException {
        String query = "SELECT * FROM " + toSqlName(tableName);
        if(filters != null && !filters.isEmpty())
            query += " WHERE " + createKeyValue(filters);
        if(suffixSQL != null){
            query += suffixSQL;
        }
        return connection.createStatement().executeQuery(query);
    }

    public static String saveOrUpdate(String tableName, Map<String, Pair> values) throws SQLException {
        String query;
        values = values.entrySet().stream().filter(el->el.getValue().getWord() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if(values.containsKey("id"))
            query = "UPDATE " + toSqlName(tableName) + " SET " + createKeyValue(values) + " WHERE \"id\"="+ addQuote(values.get("id"));
        else{
            String valuesQuery = values.keySet().stream().map(Query::toSqlName).collect(Collectors.joining(","));
            query = "INSERT INTO " + toSqlName(tableName) + "(" + valuesQuery +
                    ") VALUES ( " + values.values().stream()
                    .map(Query::addQuote)
                    .collect(Collectors.joining(",")) + ")";
        }
        PreparedStatement statement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        statement.executeUpdate();

        ResultSet idStatement = statement.getGeneratedKeys();
        if(idStatement.next())
            return idStatement.getString(1);
        return null;
    }
}