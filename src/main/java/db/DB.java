package db;

import java.sql.*;

public class DB {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/EPAM_JAVA";
    static final String USER = "postgres";
    static final String PASS = "0402";

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    public DB(){

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            if(connection != null)
            {
                System.out.println("You successfully connected to database now");

                String sql = "insert into Logger(type, info, date) VALUES(?, ?, current_timestamp)";
                preparedStatement = connection.prepareStatement(sql);
            }
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
    }

    public void insert(String type, String info)
    {
        try {
            preparedStatement.setString(1, type);
            preparedStatement.setString(2, info);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert Failed");
            e.printStackTrace();
        }
    }
}
