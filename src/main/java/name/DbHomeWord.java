package name;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHomeWord {
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




    public static void createTable() {
        String SQL = "CREATE TABLE country(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "age INTEGER);" +
                "CREATE TABLE city(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "age INTEGER," +
                "country_id  int references country (id) not null);"+
                "CREATE TABLE cityMer(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "age INTEGER," +
                "city_id  int references city (id) not null);";
        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getCount() {
        String SQL = "SELECT count(*) FROM student";
        int count = 0;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static void addCounty(String name, int age){
        String SQL = "INSERT INTO country (name, age)" +
                "VALUES (?, ?)";
        try (Connection conn = connect(); //DB класс аты
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1,name); //name переменный
            preparedStatement.setInt(2,age); //age переменный аргумента
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public static void addCity(String name, int age, int country_id){
        String SQL = "INSERT INTO city (name, age, country_id)" +
                "VALUES (?, ?, ?)";
        try (Connection conn = connect(); //DB класс аты
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1,name); //name переменный
            preparedStatement.setInt(2,age); //age переменный аргумента
            preparedStatement.setInt(3,country_id); //age переменный аргумента
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void addCityMer(String name, int age, int city_id){
        String SQL = "INSERT INTO cityMer (name, age, city_id)" +
                "VALUES (?, ?, ?)";
        try (Connection conn = connect(); //DB класс аты
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1,name); //name переменный
            preparedStatement.setInt(2,age); //age переменный аргумента
            preparedStatement.setInt(3,city_id); //age переменный аргумента
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public static void printAll() {
        String SQL = "SELECT * FROM city";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " "
                        + rs.getString("name") + " "
                        + rs.getInt("age"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<CityAll> setList(){
        String SQL = "SELECT * FROM city";
        List<CityAll> cityAlls = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                CityAll cityAll = new CityAll();
                cityAll.setId(rs.getInt("id"));
                cityAll.setAge(rs.getInt("age"));
                cityAll.setName(rs.getString("name"));
                cityAlls.add(cityAll);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return cityAlls;

    }

    public static void deleteCity (int id){
        String SQL = "DELETE FROM student WHERE id = ?";
        try(Connection conn = connect();
        PreparedStatement preparedStatement = conn.prepareStatement(SQL)){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
