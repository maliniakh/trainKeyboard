package uk.trains.keyboard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class holding results for city prefix evaluation.
 */
public class Result {
    /**
     * possible matched destinations.
     */
    List<String> destinations;
    /**
     * possible next characters.
     */
    Set<Character> characters;

    /**
     * creates result with empty collections.
     */
    public Result() {
        destinations = new ArrayList<>();
        characters = new HashSet<>();
    }

    public Result(List<String> destinations, Set<Character> characters) {
        this.destinations = destinations;
        this.characters = characters;
    }

    @Override
    public String toString() {
        return "Result{" +
                "destinations=" + destinations +
                ", characters=" + characters +
                '}';
    }
}