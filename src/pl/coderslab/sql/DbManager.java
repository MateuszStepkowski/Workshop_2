package pl.coderslab.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/workshop_2?useSSL=false&characterEncoding=UTF8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    private static DbManager instance;
    private Connection connection;
    private DbManager(){}

    public static DbManager getInstance(){
        if(instance==null){
            instance = new DbManager();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        if(connection==null || connection.isClosed()){
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        }
        return connection;
    }
}
