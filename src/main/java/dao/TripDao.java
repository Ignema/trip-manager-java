package dao;

import domain.Trip;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TripDao extends AbstractDao {

    private final PlaceDao placeDao;

    public TripDao(PlaceDao placeDao){
        super();
        this.placeDao = placeDao;
    }

    public int createTrip(Trip t){
        Statement statement = getStatement();
        try {
            statement.executeUpdate("INSERT INTO Trip(_id, depart_id, dest_id, price) VALUES (" + t.getId() + ", " + t.getDeparture().getId() + ", " + t.getDestination().getId() + ", " + t.getPrice() + ");");
            return t.getId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }

    public Trip findTripById(Long id){
        Statement statement = getStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Trip WHERE _id = " + id + ";");
            resultSet.next();
            return new Trip(Math.toIntExact(id), placeDao.findPlaceById( Long.parseLong(resultSet.getString(2))), placeDao.findPlaceById( Long.parseLong(resultSet.getString(3))), Double.parseDouble(resultSet.getString(4)));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    // Removing by ID is better than by the whole place. And also easier.
    // They are unique after all.
    public Boolean removeTrip(Long id){
        Statement statement = getStatement();
        try {
            statement.executeUpdate("DELETE FROM Trip WHERE _id = " + id + ";");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
