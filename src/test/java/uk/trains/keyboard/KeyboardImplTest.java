package uk.trains.keyboard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import uk.trains.geo.GeoData;
import uk.trains.geo.GeoDataFileImpl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KeyboardImplTest {

    KeyboardImpl keyboard;
    GeoData geoData;

    @Before
    public void setup() {
        try {
            geoData = new GeoDataFileImpl(Paths.get(getClass().getResource("/cities").toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
        keyboard = new KeyboardImpl(geoData);
    }

    @Test
    public void testGetPossibleCharacter1GeneralCases() throws Exception {
        String prefix = "a";
        Result result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);;
        System.out.println(result + "\n");
        Assert.assertEquals(2, result.destinations.size());
        Assert.assertTrue(result.destinations.contains("Aberdeen"));
        Assert.assertTrue(result.destinations.contains("Armagh"));
        Assert.assertEquals(2, result.characters.size());
        Assert.assertTrue(result.characters.contains('b'));
        Assert.assertTrue(result.characters.contains('r'));

        prefix = "d";
        result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);;
        System.out.println(result + "\n");
        Assert.assertEquals(3, result.destinations.size());
        Assert.assertTrue(result.destinations.contains("Derby"));
        Assert.assertTrue(result.destinations.contains("Dundee"));
        Assert.assertTrue(result.destinations.contains("Durham"));
        Assert.assertEquals(2, result.characters.size());
        Assert.assertTrue(result.characters.contains('u'));
        Assert.assertTrue(result.characters.contains('e'));

        prefix = "S";
        result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);;
        System.out.println(result + "\n");
        Assert.assertTrue(result.destinations.contains("Sheffield"));
        Assert.assertFalse(result.destinations.contains("Dundee"));
        Assert.assertTrue(result.characters.contains('u'));
        Assert.assertFalse(result.characters.contains('x'));
    }

    @Test
    public void testGetPossibleCharacter2FullCityNameProvided() {
        String prefix = "Bath";
        Result result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);;
        System.out.println(result + "\n");
        Assert.assertEquals(1, result.destinations.size());
        Assert.assertEquals("Bath", result.destinations.get(0));
        Assert.assertEquals(0, result.characters.size());

    }

    @Test
    public void testGetPossibleCharacter3NoMatch() {
        String prefix = "X";
        Result result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);;
        System.out.println(result + "\n");
        Assert.assertEquals(0, result.destinations.size());
        Assert.assertEquals(0, result.characters.size());

        prefix = "Sx";
        result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);
        System.out.println(result + "\n");
        Assert.assertEquals(0, result.destinations.size());
        Assert.assertEquals(0, result.characters.size());
    }

    @Test
    public void testGetPossibleCharacter4EmptyPrefix() {
        String prefix = "";
        Result result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);;
        System.out.println(result + "\n");
        Assert.assertEquals(66, result.destinations.size());
        Assert.assertEquals(19, result.characters.size());
    }

    @Test
    public void testGetPossibleCharacter5CaseInsensitivity() {
        String prefix = "SA";
        Result result = keyboard.getPossibleCharacters(prefix);
        System.out.println("prefix: " + prefix);;
        System.out.println(result + "\n");
        Assert.assertTrue(result.destinations.contains("Salford"));
        Assert.assertTrue(result.destinations.contains("Salisbury"));
    }
}