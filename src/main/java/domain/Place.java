package domain;

import util.Instance;

import java.io.Serializable;

public class Place implements Serializable {

    private static final Instance instance = new Instance();

    private int id;

    private String name;

    public Place(String name) {
        this.id = instance.getNext();
        this.name = name;
    }

    public Place(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
