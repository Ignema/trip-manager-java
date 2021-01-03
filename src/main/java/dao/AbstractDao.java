package dao;

import config.Persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao {

    private Connection connection;

    AbstractDao(){
        this.connection = new Persistence().connect();
    }

    Statement getStatement(){
        try {
            return this.connection.createStatement();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
}
