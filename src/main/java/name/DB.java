package name;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String password = "1234";

    public static Connection connect(){

        Connection connection = null;
        try{
            connection = DriverManager.getConnection(URL,USERNAME,password);
            System.out.println("Успешно подключились!");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
