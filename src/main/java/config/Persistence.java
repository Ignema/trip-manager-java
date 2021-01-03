package config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Persistence {
    Properties properties;
    Boolean isClear = false;

    public Persistence(){
        this.properties = new Properties();
        try {
            properties.load(Persistence.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public Connection connect(){
        try {
            Class.forName(properties.getProperty("jdbcPath"));
            String url = "jdbc:mysql://localhost:"+ properties.getProperty("port") + "/" + properties.getProperty("dbName");
            Connection connection = DriverManager.getConnection(url, properties.getProperty("username"), properties.getProperty("password"));
            if (!isClear){
                isClear = true;
                return restart(connection);
            } else{
                Statement statement = connection.createStatement();
                executeFile(statement, properties.getProperty("SQLDirectory") + "V1__PlaceTable.sql");
                executeFile(statement, properties.getProperty("SQLDirectory") + "V1__TripTable.sql");
                return connection;
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection restart(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP DATABASE voyage");
            statement.executeUpdate("CREATE DATABASE voyage");
            return connect();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    private void executeFile(Statement statement, String fileName) {
        try (
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder stringBuilder = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                stringBuilder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            statement.executeUpdate(stringBuilder.toString());
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}