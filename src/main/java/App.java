import dao.PlaceDao;
import dao.TripDao;
import util.Handler;

public class App {

    public static void main(String[] args) {
        final PlaceDao placeDao = new PlaceDao();;
        final TripDao tripDao =  new TripDao(placeDao);
        final Handler handler  = new Handler(placeDao, tripDao);

        handler.program();
    }
}