package util;

import dao.PlaceDao;
import dao.TripDao;
import domain.Place;
import domain.Trip;

import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Handler {

    final private PlaceDao placeDao;

    final private TripDao tripDao;

    final private Properties properties;

    public Handler(PlaceDao placeDao, TripDao tripDao) {
        this.placeDao = placeDao;
        this.tripDao = tripDao;
        this.properties = new Properties();
        try {
            properties.load(Handler.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showDialog(String lang){
        String dialog = "Welcome aboard!\n" +
                "\n" +
                "What do you want to do?\n" +
                "1 - Add a place\n" +
                "2 - Find a place\n" +
                "3 - Edit a place\n" +
                "4 - Remove a place\n" +
                "5 - Add a trip\n" +
                "6 - Find a trip\n" +
                "7 - Remove a trip\n" +
                "8 - Quit";
        switch(lang) {
            case "en":
                break;
            case "fr":
                dialog = "Bienvenue à bord!\n" +
                        "\n" +
                        "Qu'est-ce que vous voulez faire?\n" +
                        "1 - Ajouter un lieu\n" +
                        "2 - Trouver un lieu\n" +
                        "3 - Modifier un lieu\n" +
                        "4 - Supprimer un lieu\n" +
                        "5 - Ajouter un voyage\n" +
                        "6 - Trouver un voyage\n" +
                        "7 - Supprimer un voyage\n" +
                        "8 - Quitter";
                break;
            default:
                System.out.println("The specified language is not supported. Using English instead...\n");
        }
        System.out.println("\n");
        System.out.println(dialog);
        System.out.println("\n<ANSWER?>");
    }

    public String scanInput(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void processInput(String input){
        switch (Integer.parseInt(input)) {
            case 1:
                System.out.println("\n<NAME?>");
                Place add = new Place(scanInput());
                placeDao.createPlace(add);
                System.out.println("\n<SUCCESS> " + add.getName() + " [ID-" + add.getId() +"]");
                break;
            case 2:
                System.out.println("\n<ID?>");
                System.out.println("\n>> " + placeDao.findPlaceById(Long.parseLong(scanInput())).getName());
                break;
            case 3:
                System.out.println("\n<ID?>");
                int updateId = Integer.parseInt(scanInput());
                System.out.println("\n<NAME?>");
                Place update = new Place(updateId, scanInput());
                placeDao.updatePlace(update);
                System.out.println("\n<SUCCESS> " + update.getName() + " [ID-" + update.getId() +"]");
                break;
            case 4:
                System.out.println("\n<ID?>");
                System.out.println("\n" + placeDao.removePlace(Long.parseLong(scanInput())));
                break;
            case 5:
                System.out.println("\n<DEPARTURE?>");
                Place depart = new Place(scanInput());
                placeDao.createPlace(depart);
                System.out.println("\n<DESTINATION?>");
                Place destination = new Place(scanInput());
                placeDao.createPlace(destination);
                System.out.println("\n<PRICE?>");
                double price = Double.parseDouble(scanInput());
                Trip build = new Trip(depart, destination, price);
                tripDao.createTrip(build);
                System.out.println("\n<SUCCESS> " + build.getDeparture().getName() + " ----> " + build.getDestination().getName() + " [" + build.getPrice() + "$] " + " [ID-" + build.getId() +"]");
                break;
            case 6:
                System.out.println("\n<ID?>");
                Trip search = tripDao.findTripById(Long.parseLong(scanInput()));
                System.out.println("\n>> " + search.getDeparture().getName() + " ----> " + search.getDestination().getName() + " [" + search.getPrice() + "$] " + " [ID-" + search.getId() +"]");
                break;
            case 7:
                System.out.println("\n<ID?>");
                System.out.println("\n" + tripDao.removeTrip(Long.parseLong(scanInput())));
                break;
            case 8:
                System.exit(0);
                break;
        }
        program();
    }

    public void program(){
        showDialog(properties.getProperty("lang"));
        processInput(scanInput());
    }
}
