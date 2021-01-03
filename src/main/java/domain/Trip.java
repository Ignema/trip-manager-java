package domain;

import util.Instance;

public class Trip {
    private static final Instance instance = new Instance();
    private int id;
    private Place departure;
    private Place destination;
    private double price;

    public Trip(Place departure, Place destination, double price) {
        this.id = instance.getNext();
        this.departure = departure;
        this.destination = destination;
        this.price = price;
    }

    public Trip(int id, Place departure, Place destination, double price) {
        this.id = id;
        this.departure = departure;
        this.destination = destination;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Place getDeparture() {
        return departure;
    }

    public void setDeparture(Place departure) {
        this.departure = departure;
    }

    public Place getDestination() {
        return destination;
    }

    public void setDestination(Place destination) {
        this.destination = destination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
