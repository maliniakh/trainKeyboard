package uk.trains.keyboard;

import uk.trains.geo.GeoData;

import java.util.*;

public class KeyboardImpl implements Keyboard {

    private Node root;

    private GeoData geoData;

    /**
     * Some DI framework should be probably used.
     *
     * @param geoData
     */
    public KeyboardImpl(GeoData geoData) {
        this.geoData = geoData;
        initPrefixTree();
    }

    /**
     * initiates the prefix tree.
     */
    private void initPrefixTree() {
        List<String> cities = geoData.getAllCities();

        // init root
        root = new Node("", cities);
        /**
         * i denotes substring index in substring(0,i)
         */
        for (int i = 1; ; i++) {
            // all prefixes for given i, set makes them unique
            // prefix two destinations map for current iteration
            Map<String, List<String>> prefix2destMap = new HashMap<>();
            for (String city : cities) {
                if (i > city.length()) {
                    continue;
                }

                String prefix = city.substring(0, i).toLowerCase();
                // add prefix - destination mapping
                List<String> destinations = prefix2destMap.get(prefix);
                if (destinations == null) {
                    destinations = new ArrayList<>();
                    prefix2destMap.put(prefix, destinations);
                }
                destinations.add(city);
            }

            // i exceeded all cities' length, initialization is done
            if (prefix2destMap.size() == 0) {
                return;
            }

            // add prefixes along with destinations to tree
            for (Map.Entry<String, List<String>> entry : prefix2destMap.entrySet()) {
                String prefix = entry.getKey();
                Node node = getNode(prefix);
                if (node == null) {
                    // if node is null then find node one level up and add new add as its children
                    node = new Node(prefix);
                    Node parent = getNode(prefix.substring(0, prefix.length() - 1));
                    parent.char2ChildrenMap.put(prefix.charAt(prefix.length() - 1), node);
                }

                node.destinations = entry.getValue();
            }

        }
    }

    @Override
    public Result getPossibleCharacters(String prefix) {
        prefix = prefix.toLowerCase();
        Node node = getNode(prefix);
        if (node == null) {
            return new Result();
        } else {
            return new Result(node.destinations, node.char2ChildrenMap.keySet());
        }

    }

    private Node getNode(String prefix) {
        Node currentNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            Node node = currentNode.char2ChildrenMap.get(c);
            if (node == null) {
                return null;
            }
            currentNode = node;
        }

        return currentNode;
    }

}

class Node {
    /**
     * city name prefix.
     */
    String prefix;
    /**
     * possible matched destinations.
     */
    List<String> destinations;
    /**
     * node's children, mapped by possible next characters.
     */
    Map<Character, Node> char2ChildrenMap = new HashMap<>();

    Node(String prefix) {
        this.prefix = prefix;
    }

    Node(String prefix, List<String> destinations) {
        this.prefix = prefix;
        this.destinations = destinations;
    }
}

