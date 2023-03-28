package name;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
       DbHomeWord dbHomeWord = new DbHomeWord();
      // dbHomeWord.createTable();
       //dbHomeWord.addCounty("Bishkek",122);
      //  dbHomeWord.addCity("Bishkek",12,1);
      //  dbHomeWord.addCityMer("Orozbay",115,1);
    //    dbHomeWord.printAll();

      //  System.out.println(dbHomeWord.setList());

        dbHomeWord.deleteCity(1);
    }


    public static void createTable() {
        String SQL = "CREATE TABLE student(" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "age INTEGER);";
        try (Connection connection = DB.connect();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(SQL);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getCount() {
        String SQL = "SELECT count(*) FROM student";
        int count = 0;
        try (Connection conn = DB.connect();
             Statement stmt = conn.createStatement()) {
             ResultSet rs = stmt.executeQuery(SQL);
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public static void addStudent(String name, int age){
        String SQL = "INSERT INTO student (name, age)\n" +
                "VALUES (?, ?)";
        try (Connection conn = DB.connect(); //DB класс аты
             PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1,name); //name переменный
            preparedStatement.setInt(2,age); //age переменный аргумента
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
