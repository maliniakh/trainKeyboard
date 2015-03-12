package uk.trains.geo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GeoDataFileImpl implements GeoData {

    private List<String> cities = new ArrayList<>();

    public GeoDataFileImpl(Path citiesPath) throws IOException {
        cities = Files.readAllLines(citiesPath, Charset.defaultCharset());
    }

    @Override
    public List<String> getAllCities() {
        return cities;
    }
}