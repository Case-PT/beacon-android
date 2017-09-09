package li.brianv.domain;

public class Location {
    private final double lattitude, longitude;

    private Location(double lattitude, double longitude) {
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public static Location forLocation(double lattitude, double longitude) {
        return new Location(lattitude, longitude);
    }
}
