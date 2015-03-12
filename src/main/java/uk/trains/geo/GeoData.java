package uk.trains.geo;

import java.util.List;

public interface GeoData {
    /**
     * @return city list. the most obvious implementation would use a database one,
     * for unit tests it might be just a plain-text file.
     */
    List<String> getAllCities();
}