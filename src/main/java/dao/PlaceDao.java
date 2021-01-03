package dao;

import domain.Place;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlaceDao extends AbstractDao {

    public PlaceDao(){
        super();
    }

    public int createPlace(Place place){
        Statement statement = getStatement();
        try {
            statement.executeUpdate("INSERT INTO Place(_id, name) VALUES (" + place.getId() + ", " + "'" + place.getName() + "'" + ");");
            return place.getId();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return -1;
        }
    }

    public Place findPlaceById(Long id){
        Statement statement = getStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Place WHERE _id = " + id + ";");
            resultSet.next();
            return new Place(Math.toIntExact(id), resultSet.getString(2));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public Boolean updatePlace(Place p){
        Statement statement = getStatement();
        try {
            statement.executeUpdate("UPDATE Place SET name = " + "'" + p.getName() + "'" + " WHERE _id = " + p.getId() + ";");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    // Removing by ID is better than by the whole place. And also easier.
    // They are unique after all.
    public Boolean removePlace(Long id){
        Statement statement = getStatement();
        try {
            statement.executeUpdate("DELETE FROM Place WHERE _id = " + id + ";");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
