package uk.trains.keyboard;

public interface Keyboard {
    /**
     * @param prefix
     * @return cities that much provided prefix and possible next letters.
     */
    Result getPossibleCharacters(String prefix);
}
