package storage;

import java.sql.*;

public class DBManager {
    private Connection connection;
    public DBManager(Connection connection)
    {
        this.connection = connection;
    }
    private ResultSet executeQuery(String request) throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery(request);
        return resultSet;
    }
    private int executeUpdate(String request) throws SQLException {
        Statement statement = this.connection.createStatement();
        int changed = statement.executeUpdate(request);
        return changed;
    }
    private void add(City city) throws SQLException {
        String query = "INSERT INTO cities_list" +
                "(id, name, coordinate_x, coordinate_y, creation_date, area, population, meters_above_sea_level, capital, carCode, government, governor)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = this.connection.prepareStatement(query);
        ps.setString(1,Integer.valueOf(city.getId()).toString());
        //остальные установления
        int changed = ps.executeUpdate();
        if(changed!=1) throw new SQLException("неизвестная ошибка при взаимодействии с базой данных");
    }
}
